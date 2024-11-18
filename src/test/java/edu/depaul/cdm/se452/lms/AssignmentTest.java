package edu.depaul.cdm.se452.lms;

import edu.depaul.cdm.se452.assessment.*;
import edu.depaul.cdm.se452.course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AssignmentTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    private Assignment assignment;
    private Assignment updatedAssignment;
    private Long assignmentId;

    @BeforeEach
    public void setUp() {
        // Initialize the mock objects
        MockitoAnnotations.openMocks(this);

        // Create a new Assignment for testing
        assignmentId = 1L;
        Course course = new Course();  // Set the course details as needed
        course.setCourseId(1L);
        course.setName("Test Course");

        assignment = new Assignment();
        assignment.setId(assignmentId);
        assignment.setTitle("Test Assignment");
        assignment.setDescription("Test Description");
        assignment.setDueDate(new Timestamp(System.currentTimeMillis()));
        assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setCourse(course);

        updatedAssignment = new Assignment();
        updatedAssignment.setTitle("Updated Title");
        updatedAssignment.setDescription("Updated Description");
        updatedAssignment.setDueDate(new Timestamp(System.currentTimeMillis() + 100000));
    }

    @Test
    public void testGetAllAssignments() {
        List<Assignment> mockAssignments = List.of(assignment);
        when(assignmentRepository.findAll()).thenReturn(mockAssignments);

        List<Assignment> assignments = assignmentService.getAllAssignments();

        assertNotNull(assignments);
        assertEquals(1, assignments.size());
        assertEquals(assignment.getId(), assignments.get(0).getId());
    }

    @Test
    public void testGetAssignmentById_Found() {
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));

        Optional<Assignment> foundAssignment = assignmentService.getAssignmentById(assignmentId);

        assertTrue(foundAssignment.isPresent());
        assertEquals(assignmentId, foundAssignment.get().getId());
    }

    @Test
    public void testGetAssignmentById_NotFound() {
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());

        Optional<Assignment> foundAssignment = assignmentService.getAssignmentById(assignmentId);

        assertFalse(foundAssignment.isPresent());
    }

    @Test
    public void testCreateAssignment() {
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        Assignment savedAssignment = assignmentService.createAssignment(assignment);

        assertNotNull(savedAssignment);
        assertEquals(assignment.getId(), savedAssignment.getId());
        assertEquals(assignment.getTitle(), savedAssignment.getTitle());
    }

    @Test
    public void testCreateAssignment_ExistingId() {
        assignment.setId(assignmentId);
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assignmentService.createAssignment(assignment);
        });

        assertEquals("Assignment with ID " + assignmentId + " already exists.", exception.getMessage());
    }

    @Test
    public void testUploadFile() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("testfile.txt");
        when(file.getContentType()).thenReturn("text/plain");
        when(file.getBytes()).thenReturn(new byte[0]);
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));

        assignmentService.uploadFile(assignmentId, file);

        verify(assignmentRepository, times(1)).save(assignment);
        assertEquals("testfile.txt", assignment.getFileName());
        assertEquals("text/plain", assignment.getFileType());
    }

    @Test
    public void testUploadFile_AssignmentNotFound() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("testfile.txt");
        when(file.getContentType()).thenReturn("text/plain");
        when(file.getBytes()).thenReturn(new byte[0]);
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            assignmentService.uploadFile(assignmentId, file);
        });

        assertEquals("Assignment not found with ID: " + assignmentId, exception.getMessage());
    }

    @Test
    public void testUpdateAssignment() {
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));
        when(assignmentRepository.save(any(Assignment.class))).thenReturn(assignment);

        Assignment updated = assignmentService.updateAssignment(assignmentId, updatedAssignment);

        assertNotNull(updated);
        assertEquals(updatedAssignment.getTitle(), updated.getTitle());
        assertEquals(updatedAssignment.getDescription(), updated.getDescription());
        assertEquals(updatedAssignment.getDueDate(), updated.getDueDate());
    }

    @Test
    public void testUpdateAssignment_NotFound() {
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            assignmentService.updateAssignment(assignmentId, updatedAssignment);
        });

        assertEquals("Assignment not found with id " + assignmentId, exception.getMessage());
    }

    @Test
    public void testDeleteAssignment() {
        when(assignmentRepository.existsById(assignmentId)).thenReturn(true);

        assignmentService.deleteAssignment(assignmentId);

        verify(assignmentRepository, times(1)).deleteById(assignmentId);
    }

    @Test
    public void testDeleteAssignment_NotFound() {
        when(assignmentRepository.existsById(assignmentId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assignmentService.deleteAssignment(assignmentId);
        });

        assertEquals("Assignment not found with ID: " + assignmentId, exception.getMessage());
    }

    @Test
    public void testGetAssignmentsByCourseName() {
        List<Assignment> mockAssignments = List.of(assignment);
        when(assignmentRepository.findAssignmentsByCourseName("Test Course")).thenReturn(mockAssignments);

        List<AssignmentDTO> assignments = assignmentService.getAssignmentsByCourseName("Test Course");

        assertNotNull(assignments);
        assertEquals(1, assignments.size());
        assertEquals("Test Assignment", assignments.get(0).getTitle());
    }

    @Test
    public void testGetAssignmentsByCourseId() {
        List<Assignment> mockAssignments = List.of(assignment);
        when(assignmentRepository.findByCourse_CourseId(1L)).thenReturn(mockAssignments);

        List<AssignmentDTO> assignments = assignmentService.getAssignmentsByCourseId(1L);

        assertNotNull(assignments);
        assertEquals(1, assignments.size());
        assertEquals("Test Assignment", assignments.get(0).getTitle());
    }

    @Test
    public void testSearchAssignmentsByTitle() {
        List<Assignment> mockAssignments = List.of(assignment);
        when(assignmentRepository.findByTitleContainingIgnoreCase("Test")).thenReturn(mockAssignments);

        List<AssignmentDTO> assignments = assignmentService.searchAssignmentsByTitle("Test");

        assertNotNull(assignments);
        assertEquals(1, assignments.size());
        assertEquals("Test Assignment", assignments.get(0).getTitle());
    }
}

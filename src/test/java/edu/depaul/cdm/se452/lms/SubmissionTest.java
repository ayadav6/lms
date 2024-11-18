package edu.depaul.cdm.se452.lms;

import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import edu.depaul.cdm.se452.assessment.Assignment;
import edu.depaul.cdm.se452.assessment.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SubmissionTest {

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private SubmissionService submissionService;

    private SubmissionDTO submissionDTO;
    private User student;
    private Assignment assignment;
    private MultipartFile file;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up mock objects
        student = new User();
        student.setId(1L);
        student.setUserName("John Doe");

        assignment = new Assignment();
        assignment.setId(1L);
        assignment.setTitle("Assignment 1");

        submissionDTO = new SubmissionDTO();
        submissionDTO.setStudentId(1L);
        submissionDTO.setAssignmentId(1L);
        submissionDTO.setComment("Good job");

        file = mock(MultipartFile.class);
    }

    @Test
    void testCreateSubmissionWithFile() throws IOException {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(file.getOriginalFilename()).thenReturn("file.txt");
        when(file.getBytes()).thenReturn("file content".getBytes());

        Submission savedSubmission = new Submission();
        savedSubmission.setId(1L);
        savedSubmission.setStudent(student);
        savedSubmission.setAssignment(assignment);
        savedSubmission.setComment("Done assg");
        savedSubmission.setFileName("file.txt");
        savedSubmission.setFileData("file content".getBytes());
        savedSubmission.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
        savedSubmission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        when(submissionRepository.save(any(Submission.class))).thenReturn(savedSubmission);

        // Act
        SubmissionDTO result = submissionService.createSubmissionWithFile(submissionDTO, file);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("file.txt", result.getFileName());
        assertEquals("Done assg", result.getComment());
        verify(submissionRepository, times(1)).save(any(Submission.class));
    }

    @Test
    void testGetSubmissionByStudentAndAssignment() {
        // Arrange
        Submission submission = new Submission();
        submission.setId(1L);
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setComment("Good job");
        submission.setFileName("file.txt");

        when(submissionRepository.findByStudentIdAndAssignmentId(1L, 1L))
                .thenReturn(List.of(submission));

        // Act
        SubmissionDTO result = submissionService.getSubmissionByStudentAndAssignment(1L, 1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("file.txt", result.getFileName());
        assertEquals("Good job", result.getComment());
    }

    @Test
    void testDeleteSubmission() {
        // Arrange
        when(submissionRepository.existsById(1L)).thenReturn(true);

        // Act
        submissionService.deleteSubmission(1L);

        // Assert
        verify(submissionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSubmission_NotFound() {
        // Arrange
        when(submissionRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            submissionService.deleteSubmission(1L);
        });
        assertEquals("Submission not found with ID 1", exception.getMessage());
    }

    @Test
    void testGetSubmissionById() {
        // Arrange
        Submission submission = new Submission();
        submission.setId(1L);
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setComment("Good job");
        submission.setFileName("file.txt");

        when(submissionRepository.findById(1L)).thenReturn(Optional.of(submission));

        // Act
        Submission result = submissionService.getSubmissionById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("file.txt", result.getFileName());
    }

    @Test
    void testGetSubmissionById_NotFound() {
        // Arrange
        when(submissionRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            submissionService.getSubmissionById(1L);
        });
        assertEquals("Submission not found", exception.getMessage());
    }

    @Test
    void testLoadFileAsResource() {
        // Arrange
        Submission submission = new Submission();
        submission.setId(1L);
        submission.setFileData("file content".getBytes());

        when(submissionRepository.findById(1L)).thenReturn(Optional.of(submission));

        // Act
        Resource resource = submissionService.loadFileAsResource(1L);

        // Assert
        assertNotNull(resource);
        assertEquals("file content", new String(((ByteArrayResource) resource).getByteArray()));
    }

    @Test
    void testGetAllSubmissionsByAssignmentId() {
        // Arrange
        Submission submission1 = new Submission();
        submission1.setId(1L);
        submission1.setStudent(student);
        submission1.setAssignment(assignment);
        submission1.setComment("Good job");
        submission1.setFileName("file1.txt");

        Submission submission2 = new Submission();
        submission2.setId(2L);
        submission2.setStudent(student);
        submission2.setAssignment(assignment);
        submission2.setComment("Well done");
        submission2.setFileName("file2.txt");

        when(submissionRepository.findByAssignmentId(1L))
                .thenReturn(List.of(submission1, submission2));

        // Act
        List<SubmissionDTO> result = submissionService.getAllSubmissionsByAssignmentId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("file1.txt", result.get(0).getFileName());
        assertEquals("file2.txt", result.get(1).getFileName());
    }
}

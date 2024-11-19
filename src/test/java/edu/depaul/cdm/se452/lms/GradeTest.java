package edu.depaul.cdm.se452.lms;

import edu.depaul.cdm.se452.assessment.*;

import edu.depaul.cdm.se452.user.User;
import edu.depaul.cdm.se452.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GradeTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GradeService gradeService;

    private GradeDTO gradeDTO;
    private Grade grade;
    private Submission submission;
    private User instructor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create mock data
        gradeDTO = new GradeDTO();
        gradeDTO.setGradeValue((double) 90);
        gradeDTO.setFeedback("Good work");
        gradeDTO.setSubmissionId(1L);
        gradeDTO.setInstructorId(1L);

        submission = new Submission();
        submission.setId(1L);

        instructor = new User();
        instructor.setId(1L);
        instructor.setUserName("Instructor");

        grade = new Grade();
        grade.setId(1L);
        grade.setGradeValue((double) 90);
        grade.setFeedback("Good work");
        grade.setGradedAt(new Timestamp(System.currentTimeMillis()));
        grade.setSubmission(submission);
        grade.setInstructor(instructor);
    }

    @Test
    public void testCreateGrade() {
        when(submissionRepository.findById(gradeDTO.getSubmissionId())).thenReturn(Optional.of(submission));
        when(userRepository.findById(gradeDTO.getInstructorId())).thenReturn(Optional.of(instructor));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        GradeDTO createdGrade = gradeService.createGrade(gradeDTO);

        assertNotNull(createdGrade);
        assertEquals(90, createdGrade.getGradeValue());
        assertEquals("Good work", createdGrade.getFeedback());
        verify(submissionRepository, times(1)).save(any(Submission.class));
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    public void testCreateGradeSubmissionNotFound() {
        when(submissionRepository.findById(gradeDTO.getSubmissionId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> gradeService.createGrade(gradeDTO));
        assertEquals("Submission not found", exception.getMessage());
    }

    @Test
    public void testCreateGradeInstructorNotFound() {
        when(submissionRepository.findById(gradeDTO.getSubmissionId())).thenReturn(Optional.of(submission));
        when(userRepository.findById(gradeDTO.getInstructorId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> gradeService.createGrade(gradeDTO));
        assertEquals("Instructor not found", exception.getMessage());
    }

    @Test
    public void testGetGradeById() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        GradeDTO foundGrade = gradeService.getGradeById(1L);

        assertNotNull(foundGrade);
        assertEquals(1L, foundGrade.getId());
        assertEquals(90, foundGrade.getGradeValue());
        assertEquals("Good work", foundGrade.getFeedback());
    }

    @Test
    public void testGetGradeByIdNotFound() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> gradeService.getGradeById(1L));
        assertEquals("Grade not found", exception.getMessage());
    }

    @Test
    public void testGetGradesBySubmissionId() {
        when(gradeRepository.findBySubmissionId(1L)).thenReturn(Arrays.asList(grade));

        List<GradeDTO> grades = gradeService.getGradesBySubmissionId(1L);

        assertNotNull(grades);
        assertEquals(1, grades.size());
        assertEquals(90, grades.get(0).getGradeValue());
    }

    @Test
    public void testGetGradesBySubmissionIdNotFound() {
        when(gradeRepository.findBySubmissionId(1L)).thenReturn(Arrays.asList());

        List<GradeDTO> grades = gradeService.getGradesBySubmissionId(1L);

        assertTrue(grades.isEmpty());
    }

    @Test
    public void testGetAllGrades() {
        when(gradeRepository.findAll()).thenReturn(Arrays.asList(grade));

        List<GradeDTO> allGrades = gradeService.getAllGrades();

        assertNotNull(allGrades);
        assertEquals(1, allGrades.size());
        assertEquals(90, allGrades.get(0).getGradeValue());
    }

    @Test
    public void testUpdateGrade() {
        GradeDTO updateDTO = new GradeDTO();
        updateDTO.setGradeValue((double) 95);
        updateDTO.setFeedback("Excellent work");

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        GradeDTO updatedGrade = gradeService.updateGrade(1L, updateDTO);

        assertNotNull(updatedGrade);
        assertEquals(90, updatedGrade.getGradeValue());
        assertEquals("Excellent work", updatedGrade.getFeedback());
        verify(submissionRepository, times(1)).save(any(Submission.class));
    }

    @Test
    public void testUpdateGradeNotFound() {
        GradeDTO updateDTO = new GradeDTO();
        updateDTO.setGradeValue((double) 95);

        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> gradeService.updateGrade(1L, updateDTO));
        assertEquals("Grade not found", exception.getMessage());
    }

    @Test
    public void testDeleteGrade() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        gradeService.deleteGrade(1L);

        verify(gradeRepository, times(1)).deleteById(1L);
        verify(submissionRepository, times(1)).save(any(Submission.class));
    }

    @Test
    public void testDeleteGradeNotFound() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> gradeService.deleteGrade(1L));
        assertEquals("Grade not found", exception.getMessage());
    }
}

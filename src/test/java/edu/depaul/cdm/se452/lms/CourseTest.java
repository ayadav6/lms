package edu.depaul.cdm.se452.lms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.depaul.cdm.se452.course.Course;
import edu.depaul.cdm.se452.course.CourseRepository;
import edu.depaul.cdm.se452.course.CourseService;
import edu.depaul.cdm.se452.user.UserRepository;

class CourseTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;
    private Course updatedCourseDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        course = new Course();
        course.setCourseId(1L);
        course.setName("Original Course Name");
        course.setStatus(Course.Status.ACTIVE);

        updatedCourseDetails = new Course();
        updatedCourseDetails.setName("Updated Course Name");
        updatedCourseDetails.setStatus(Course.Status.INACTIVE);
    }

    @Test
    void saveCourse_ShouldReturnSavedCourse() {
        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = courseService.saveCourse(course);

        assertEquals(course.getName(), savedCourse.getName());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void getAllCourses_ShouldReturnListOfCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course));

        List<Course> courses = courseService.getAllCourses();

        assertEquals(1, courses.size());
        assertEquals(course.getName(), courses.get(0).getName());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void getCourseById_ShouldReturnCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Optional<Course> foundCourse = courseService.getCourseById(1L);

        assertTrue(foundCourse.isPresent());
        assertEquals(course.getName(), foundCourse.get().getName());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void deleteCourse_ShouldThrowException_WhenCourseDoesNotExist() {
        when(courseRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseService.deleteCourse(1L);
        });

        assertEquals("Course not found with ID: 1", exception.getMessage());
    }

    @Test
    void updateCourse_ShouldUpdateCourseDetails_WhenProvided() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);

        Course updatedCourse = courseService.updateCourse(1L, updatedCourseDetails);

        assertEquals("Updated Course Name", updatedCourse.getName());
        assertEquals(Course.Status.INACTIVE, updatedCourse.getStatus());
        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void updateCourse_ShouldThrowException_WhenCourseNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseService.updateCourse(1L, updatedCourseDetails);
        });

        assertEquals("Course not found with ID: 1", exception.getMessage());
        verify(courseRepository, times(1)).findById(1L);
        verify(courseRepository, never()).save(any(Course.class));
    }
}

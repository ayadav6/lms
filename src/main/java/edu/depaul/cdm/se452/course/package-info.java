package edu.depaul.cdm.se452.course;
/*
 * @author : Jayalakshmi
 * 
 * Milestone 3:
 * File: course:
 * 1. Entity Class:
 * - Created `Course` entity with fields like `courseId`, `name`, `startDate`,
 * `endDate`, `status` and 'Instructor'.
 * - Used Lombok's `@Data` annotation to automatically generate getters,
 * setters, and other methods.
 * - Implemented a many-to-one relationship to link courses with their
 * instructors.
 *
 * 2. Repository Layer:
 * - Created a repository (`CourseRepository`) for CRUD operations on the
 * `Course` entity.
 * 
 * 3. Service Layer:
 * - Developed a service class (`CourseService`) to handle business logic
 * related to courses.
 * - Implemented methods for saving courses and managing course records.
 *
 * 4. Controller Layer:
 * - Created a REST controller (`CourseController`) to expose endpoints for
 * course management operations.
 * - Provides APIs for creating courses, retrieving courses, and deleting
 * courses.
 * - Verified all controller endpoints using Postman to ensure functionality and
 * proper responses.
 * 
 * 5. Test Cases:
 * - Developed unit tests for the controller methods to ensure correctness and
 * reliability.
 * - Tests cover various scenarios, including successful updates and edge cases.
 *
 * 6. Database Setup:
 * - Configured H2 database for in-memory storage and testing.
 *
 * 7. Interaction with Other Modules:
 * - Interacts with the `User Management` module to associate users
 * (instructors) with courses.
 *
 */
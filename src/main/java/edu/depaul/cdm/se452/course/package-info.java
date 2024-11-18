package edu.depaul.cdm.se452.course;

/*
 * @author : Jayalakshmi
 * 
 * Milestone : Final
 * File: Course Module
 * 
 * 1. Entity Class:
 * - Created `Course` entity with fields like `courseId`, `name`, `startDate`,
 * `endDate`, `status`, and `instructor`.
 * - Used Lombok's `@Data` annotation to automatically generate getters,
 * setters, and other methods.
 * - Implemented a many-to-one relationship to link courses with their
 * instructors in SQL database (H2).
 * - Created a `CourseMongo` entity for MongoDB integration to store additional
 * course-related data such as `description` and `materials`.
 * 
 * 2. Repository Layer:
 * - Developed `CourseRepository` for CRUD operations on the `Course` entity
 * (H2).
 * - Created `CourseMongoRepository` for interacting with the NoSQL MongoDB
 * database.
 * - Implemented methods to find courses by instructor ID and by partial name
 * match in both repositories.
 * 
 * 3. Service Layer:
 * - Extended `CourseService` to handle business logic for both SQL and NoSQL
 * components.
 * - Added methods to handle course data storage and retrieval from both H2 and
 * MongoDB.
 * - Integrated validations for course fields such as date constraints and
 * required inputs (e.g., name, instructor).
 * 
 * 4. Controller Layer:
 * - Enhanced `CourseController` to handle web requests using Thymeleaf for UI
 * rendering.
 * - Provided endpoints for managing courses:
 * - Create new courses via an HTML form.
 * - View all courses on a web page.
 * - Add new functionality for course description and materials using MongoDB.
 * - Included both client-side (HTML/JS) and server-side validations to ensure
 * data integrity.
 * 
 * 5. Thymeleaf Integration:
 * - Developed dynamic UI pages using Thymeleaf templates for course
 * management:
 * - A form to add new courses with fields for name, start date, end date,
 * status, and instructor.
 * - Dropdown for course `status` populated dynamically.
 * - Added error messages and validation feedback directly on the UI.
 * - Configured Thymeleaf views to display validation errors on the client
 * side.
 * 
 * 6. Validation:
 * - Implemented server-side validations in the `Course` entity and service
 * layer for:
 * - Non-empty and valid fields (e.g., course name, start and end dates).
 * - Logical constraints like ensuring the end date is not before the start
 * date.
 * - Enabled client-side validations using HTML5 attributes (e.g., `required`,
 * `pattern`) and JavaScript.
 * - Ensured a seamless experience with error messages on form submission.
 * 
 * 7. Test Cases:
 * - Developed unit tests for both SQL and NoSQL operations in service and
 * controller layers.
 * - Verified Thymeleaf-based forms and views using integration tests.
 * - Validated error handling for edge cases like missing instructor details.
 * 
 * 8. Database Setup:
 * - Configured H2 database for SQL-based course storage and testing.
 * - Integrated MongoDB to manage non-relational course data such as
 * descriptions and materials.
 * - Verified data consistency between H2 and MongoDB through service-layer
 * integration tests.
 * 
 * 9. Interaction with Other Modules:
 * - Interacts with the `User Management` module to associate users
 * (instructors) with courses.
 * - Ensures data consistency between user roles and their associated courses.
 * 
 */

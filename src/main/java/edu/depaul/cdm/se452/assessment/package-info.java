package edu.depaul.cdm.se452.assessment;
/*
 * @author : Sweta Yellayya Samala
 * 
 * MODULE: Assessment and Grading
 * Provides the classes and interfaces for managing assessments and grading in the Learning Management System (LMS)
 * 
 * 1. Entity Classes:
 * Key components include:
 *  --Assignment: for managing assignments. 
 *  --Submission: for handling student submissions.
 *  --Grade: for recording grades given by instructors.
 *  --Student review: Facilitates student reviews for each assignment
 * 
 * 2. Repository Layer:
 *  --Created repositories ('AssignmentRepository', 'SubmissionRepository', 'GradeRepository' 'StudentReviewRepository')
 * 
 * 3. Dual Database Setup: 
 *  Configured H2 database and Mongo DB. Added its dependencies. 
 *  
 * 4. controllers:
 * This package contains REST controllers for handling HTTP requests related to the Assessment module as below:
 * – AssignmentController
 * – SubmissionController
 * – GradeController
 * – StudentReviewController
 * 
 * 5. services:
 * This package contains the service layer for the Assessment module, which includes business logic and interactions with repositories for: 
 * – Assignments: creation, retrieval, and management
 * – Submissions: handling of submission files and data
 * – Grades: grading logic
 * – Student Review: Students Feedback
 * 
 * 6. DTO:
 * This package contains Data Transfer Objects (DTOs) for the Assessment module. DTOs provide a simplified and secure way to transfer data between different layers:
 * – AssignmentDTO
 * – SubmissionDTO
 * – GradeDTO
 * DTOs help in encapsulating and transferring only required fields.
 * 
 * 7. NoSQL:
 * This package interacts with MongoDB to store and manage the reviews in a NoSQL database, providing a flexible and scalable way of handling student feedback
 * for assignments and peer reviews. 
 * – MongoDB is used to store student reviews, including:
 * – Assignment ID, Student ID, Review Text, Rating (1-5), Tags and Timestamp.
 * 
 * 8. Test cases:
 * Developed Test cases for all the modules and are passing. 
 *      –AssignmentTest
 *      –SubmissionTest
 *      –GradeTest
 *      –ReviewTest
 * 
 * 9.Thymeleaf UI Integration:
 * A web page is created using **Thymeleaf**, **Spring Boot**, and **MongoDB** to allow students to:- Add reviews.
 *    - Added dependency and Configured Thymeleaf settings in `application.properties` for development.

 *    - Created Thymeleaf templates:
 *       - `assignment.html` to display all assignments with all details.
 *       - `reviews.html` to add reviews for particular Assignment Title
 *       - `thank-you.html` to display thank you page when review is added successfully
 *   
 * 
 * 10.Interaction with Other modules:
 * - Interacts with 'User'
 * - Interacys with 'Course'
 * 
 *
 */

 

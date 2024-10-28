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
 * 
 * 
 * 2. Repository Layer:
 *  --Created repositories ('AssignmentRepository', 'SubmissionRepository', 'GradeRepository')
 * 
 * 
 * 3. Database Setup:
 * 
 * 4. Interaction with Other Modules:
 * This interacts with below modules to provide a cohesive learning experience.:
 *  --User module
 *  --Course module
 * 
 * 5. controllers:
 * This package contains REST controllers for handling HTTP requests related to the Assessment module as below:
 * – AssignmentController
 * – SubmissionController
 * – GradeController
 * 
 * 6. services:
 * This package contains the service layer for the Assessment module, which includes business logic and interactions with repositories for: 
 * – Assignments: creation, retrieval, and management
 * – Submissions: handling of submission files and data
 * – Grades: grading logic
 * 
 * 7. DTO:
 * This package contains Data Transfer Objects (DTOs) for the Assessment module. DTOs provide a simplified and secure way to transfer data between different layers:
 * – AssignmentDTO
 * – SubmissionDTO
 * – GradeDTO
 * DTOs help in encapsulating and transferring only required fields.
 * 
 * 
 *
 */

 

/*
 * @author : Ashish Yadav
 * 
 * Milestone 2:
 * File: user:
 * 1. Entity Class:
 *    - Created `User` entity with fields like `userName`, `password`, `role`, `email`, and `createdAt`.
 *    - Used Lombok's `@Data` annotation to automatically generate getters, setters, and other methods.
 *    - Implemented a one-to-many relationship to link users with courses they are associated with.
 *
 * 2. Repository Layer:
 *    - Created a repository (`UserRepository`) for CRUD operations on the `User` entity.
 *
 * 3. Database Setup:
 *    - Configured H2 database for in-memory storage and testing.
 *
 * 4. Interaction with Other Modules:
 *    - Interacts with the `Course Management` module to manage courses assigned to users.
 *    - Connects with the `Enrollment Management` module to track user enrollments in courses.
 *
 */




package edu.depaul.cdm.se452.user;
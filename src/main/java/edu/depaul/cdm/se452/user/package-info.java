/*
 * @author : Ashish Yadav
 * 
 * Final project:
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
 * 5. Implemented Service Layer
 *    -Created service class for User
 *    -Added login method for sign in. Will add hashed based credentials for login in the final submission
 *
 * 6.Implemented Controller class
 *    -Added api's for getAllUsers,getUserById,createUser,deleteUser,getStudentsByCourseId,loginUser
 *
 * 7.Rebased all the diverged branches and helped in solving conflicts to teammates.
 *
 * 8.Solved cyclic dependency between user and course module.
 *
 * 9. Created UI for crud operations page,signup,login,home page.
 *
 * 10.Created a login functionality using validation thought in class.
 *
 * 11.Helped teammates with their issues while merging and designing their feature.
 *
 * 12.Implemented test class for User module.
 *
 */




package edu.depaul.cdm.se452.user;
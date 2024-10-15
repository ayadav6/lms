/*
 * @author : Dhairya Patel
 * 
 * Milestone 2:
 * File: discussionforum:
 * 1. Entity Classes:
 *    - Created `Discussion`, `Comment`, and `Reply` entities with fields like `content`, `createdBy`, and `createdAt`.
 *    - Used Lombok's `@Data` annotation to automatically generate getters, setters, and other methods.
 *    - Used Table per Class concept, where all entities inherit common fields from the `Post` class.
 *
 * 2. Repository Layer:
 *    - Created repositories (`DiscussionRepository`, `CommentRepository`, `ReplyRepository`).
 *
 * 3. Database Setup:
 *    - Configured H2 database.
 *
 * 4. Version Update:
 *    - Updated the project version to `0.2`
 *
 * 5. Testing:
 *    - Used the H2 database for development and tested the persistence of or our project 'lms'
 *
 * 6. Relationships with Other Components:
 *    - The `Discussion`, `Comment`, and `Reply` entities are closely related to the User and Course components:
 *       - Discussions and comments are linked to courses through the `Course` entity.
 *       - Users (instructors or students) can create discussions, post comments, and replies through the `User` entity.
 *       - The `createdBy` field in `Post` refers to the User entity, establishing the relationship between discussions/comments and users.
 *       - The `Discussion` entity is linked to a specific course via the `course_id` field.
 *
 *
 */

package edu.depaul.cdm.se452.discussionforum;

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
 *
 *
 */

package edu.depaul.cdm.se452.discussionforum;

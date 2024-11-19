/*
 * @author : Dhairya Patel
 *
 * Final Submission:
 * File: discussionforum:
 *
 * 1. Service Layer:
 *    - Implemented service classes (`DiscussionService`, `CommentService`, `ReplyService`) to handle logic for each entity.
 *    - Extended service methods to support both H2 (SQL) and MongoDB (NoSQL) databases:
 *       - Added flags to determine the database type (`useMongo`).
 *       - Implemented methods to interact with both `JpaRepository` and `MongoRepository`.
 *    - Ensured CRUD operations are seamlessly integrated across both databases.
 *
 * 2. Controller Layer:
 *    - Enhanced `DiscussionController`, `CommentController`, and `ReplyController` to handle HTTP requests:
 *       - Added flexibility to switch between H2 and MongoDB based on the `useMongo` flag.
 *       - Integrated with updated service layer methods.
 *    - Configured controllers to handle input validation and response formatting.
 *
 * 3. DTO Classes:
 *    - Refactored Data Transfer Objects (`DiscussionDTO`, `CommentDTO`, `ReplyDTO`):
 *       - Updated data types to ensure compatibility with both SQL and NoSQL (e.g., handling `String` IDs for MongoDB).
 *       - Improved validation annotations for robust data handling.
 *
 * 4. Thymeleaf UI Integration:
 *    - Updated Thymeleaf templates to reflect changes in the application:
 *       - Dynamically render data from both SQL and NoSQL sources.
 *       - Enhanced error messages and user feedback mechanisms.
 *       - Templates updated: `discussions.html`, `discussion-details.html`, `discussion-form.html`, `comments.html`, `reply-form.html`.
 *
 * 5. Dual Database Integration:
 *    - Integrated MongoDB alongside H2:
 *       - Added `MongoRepository` interfaces (`DiscussionMongoRepository`, `CommentMongoRepository`, etc.).
 *       - Configured `LmsApplication` to support dual repositories using `@EnableJpaRepositories` and `@EnableMongoRepositories`.
 *       - Updated application properties to include MongoDB URI and configuration.
 *
 * 6. Testing:
 *    - Developed unit tests for dual-database architecture:
 *       - Refactored `DiscussionControllerTest`, `CommentControllerTest`, and `ReplyControllerTest` to mock both SQL and NoSQL repositories.
 *       - Ensured test coverage for all CRUD operations across H2 and MongoDB.
 *
 * 7. REST API Extension:
 *    - Implemented REST API.
 *
 * 8. Project Updates:
 *    - Updated the project version to `0.3`.
 *    - Improved code structure and organization for readability and maintainability.
 *    - Enhanced documentation and inline comments for future development and debugging.
 *
 */

package edu.depaul.cdm.se452.discussionforum;

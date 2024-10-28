/*
 * @author : Dhairya Patel
 *
 * Milestone 3:
 * File: discussionforum:
 *
 * 1. Service Layer:
 *    - Implemented service classes (`DiscussionService`, `CommentService`, `ReplyService`) to handle business logic for each entity.
 *    - Added methods to retrieve, create, and manage `Discussion`, `Comment`, and `Reply` objects.
 *    - Each service interacts with the repository layer to perform CRUD operations.
 *
 * 2. Controller Layer:
 *    - Created `DiscussionController` with mappings to handle HTTP requests for discussions:
 *       - `listDiscussions` to display all discussions.
 *       - `viewDiscussion` to show a specific discussion along with comments.
 *       - `newDiscussionForm` and `createDiscussion` to handle discussion creation.
 *    - Set up model attributes to pass data from the backend to Thymeleaf templates.
 *
 * 3. DTO Classes:
 *    - Developed Data Transfer Objects (`DiscussionDTO`, `CommentDTO`, `ReplyDTO`) to streamline data transfer between frontend and backend.
 *
 * 4. Thymeleaf UI Integration:
 *    - Added Thymeleaf dependency in `pom.xml`
 *
 *    - Configured Thymeleaf settings in `application.properties` for development.

 *    - Created Thymeleaf templates:
 *       - `discussions.html` to list all discussions.
 *       - `discussion-details.html` to show discussion details and comments.
 *       - `discussion-form.html` to create a new discussion.
 *
 * 5. Testing:
 *    - Developed unit tests for `DiscussionController`, `CommentController`, `ReplyController`.
 *
 * 6. Version Update:
 *    - Updated the project version to `0.3`.
 *
 *
 *
 */

package edu.depaul.cdm.se452.discussionforum;

/*
 * @author : Shaun
 * 
 * Milestone 2:
 * File: notification:
 * 1. Entity Classes:
 *    - Created `Notification`, `AssignmentNotification`, and `GradingNotification` entities with fields like `message`, `createdDate`, and `isRead`.
 *    - Used Lombok's `@Data` annotation to automatically generate getters, setters, and other methods.
 *    - Implemented relationships to link notifications to users, assignments, and grades.
 * 
 * 2. Repository Layer:
 *    - Created repositories (`NotificationRepository`, `AssignmentNotificationRepository`, `GradingNotificationRepository`).
 *
 * 3. Database Setup:
 *    - Configured H2 database.
 *
 * 4. Interaction with Other Modules:
 *    - Interacts with the `User Management` module to fetch user details for notifications.
 *    - Connects with the `Assignment Management` module to generate notifications related to assignments.
 *    - Works with the `Grading` module to notify users of grades and feedback.
 *
 */
package edu.depaul.cdm.se452.notification;

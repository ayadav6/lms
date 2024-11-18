/**
 * @author: Shaun Jiji
 *
 *
 * File: Notification Module
 *
 * Overview:
 * The Notification Module provides robust real-time and scheduled notifications to enhance 
 * the user experience within the Learning Management System (LMS). Originally implemented 
 * using SQL, the module was transitioned to NoSQL after Milestone 3 for its flexibility and 
 * better alignment with the unstructured nature of notification data. The SQL implementation 
 * remains intact, allowing the module to be reconfigured back to SQL if necessary for future 
 * system requirements.
 *
 * Key Components and Features:
 * 
 * 1. **Entity Classes:**
 *    - Created `Notification`, `AssignmentNotification`, and `GradingNotification` entities with fields 
 *      such as `message`, `createdDate`, and `isRead`.
 *    - Leveraged Lombok's `@Data` annotation to automatically generate getters, setters, and other 
 *      utility methods.
 *    - Implemented relationships to link notifications with users, assignments, and grades.
 *    - After transitioning to NoSQL, created `NotificationDocument` to represent notifications in 
 *      MongoDB.
 *
 * 2. **Repository Layer:**
 *    - Initially designed repositories (`NotificationRepository`, `AssignmentNotificationRepository`, 
 *      `GradingNotificationRepository`) for SQL integration.
 *    - Transitioned to a NoSQL approach by creating `NotificationNoSQLRepository` using MongoDB 
 *      after Milestone 3 for better flexibility and scalability.
 *
 * 3. **Database Management:**
 *    - Originally integrated with an H2 database for initial development and testing.
 *    - Transitioned to MongoDB to leverage NoSQL's dynamic schema capabilities, ensuring optimal 
 *      handling of unstructured and varied notification data.
 *    - Maintained SQL table structures, ensuring that the module can be reconfigured to use SQL 
 *      in the future if necessary.
 *
 * 4. **Interaction with Other Modules:**
 *    - Integrates with the `User Management` module to retrieve user details for targeted notifications.
 *    - Connects with the `Assignment Management` module to generate notifications for new assignments 
 *      and deadlines.
 *    - Collaborates with the `Grading` module to notify users of grade updates and feedback.
 *
 * 5. **Real-Time Notifications with WebSocket:**
 *    - Implemented WebSocket functionality to enable instant delivery of notifications.
 *    - Provides real-time alerts for critical events, such as assignment postings and grade updates.
 *    - Ensures efficient, bidirectional communication between the server and clients.
 *
 * 6. **Thymeleaf-Based UI Integration:**
 *    - Developed a dynamic, user-friendly interface using Thymeleaf for displaying notifications.
 *    - Categorized notifications by type for clarity and ease of access.
 *    - Designed to align with the LMS's overall UI framework for a seamless user experience.
 *
 * 7. **Testing and Reliability:**
 *    - Developed unit tests for `NotificationController`, `NotificationWebSocketController`, and 
 *      `NotificationServiceImpl`.
 *    - Performed manual WebSocket testing using tools like WebSocket King and Postman to ensure 
 *      real-time communication functionality.
 *
 * Future Considerations:
 * - While the module now operates with MongoDB, the preserved SQL table structures offer the flexibility 
 *   to revert to a SQL-based implementation if required.
 * - This adaptability ensures the notification system can evolve alongside the LMS's architecture and 
 *   scalability needs.
 *
 */
package edu.depaul.cdm.se452.notification;

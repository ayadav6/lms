SE452-Project-Learning Management System.

Project Overview
This project is a Learning Management System (LMS) built using the Spring Framework. It includes various modules to manage users, courses, assessments, discussions, and notifications, providing a comprehensive platform for managing educational content and interactions between instructors and students.

Modules

1. User Management Module
   Features:
   User Roles: Supports two types of users – Students and Instructors.
   Registration & Authentication: Users can register, log in, and log out using secure credentials.
   Role-Based Access: Instructors and students have different access levels, restricting students from administrative tasks such as course creation.
   Profile Management: Users can update their profile information (e.g., email, password).

2. Course Management Module
   Features:
   Course Creation: Instructors can create and manage courses.
   Course Enrollment: Students can enroll in courses.
   Course Overview: Students and instructors can view enrolled or created courses.

3. Assessment and Grading Module
   Features:
   Assignment Creation: Instructors can create and assign tasks with deadlines.
   Assignment Submission: Students can submit assignments online.
   Grading: Instructors can grade assignments and provide feedback.
   Late Submissions: Tracks whether submissions are made after the deadline and applies penalties if necessary.

4. Discussion Forum Module
   Features:
   Topic Creation: Instructors or students can create discussion threads for a course.
   Commenting: Users can participate in discussions by commenting on topics.
   Replying: Allows threaded discussions for focused conversations.

5. Notification Module
   Features:
   Assignment Notifications: Students receive notifications when new assignments are posted.
   Grading Notifications: Students are notified when an assignment is graded or when grades are updated, providing real-time feedback.
   Real-Time Notifications: Utilizes WebSocket technology for instant delivery of notifications, enabling efficient, bidirectional communication between the server and clients.

Project Setup Prerequisites

1. Java 21
2. Maven
3. SQL/NoSQL

Code Branches and Workflow

1. master: Stable, production-ready code.
2. dev: Active development branch where features are merged.
3. Each team member works on feature-specific branches.

Merging Workflow

1. Develop your feature on a separate branch.
2. Ensure your code passes unit tests.
3. Merge into master after peer review.
4. Weekly team meetings to resolve merge conflicts.

Meeting Journal
We will be using microsoft teams for weekly meetings and code reviews.

Milestones

Milestone 1: Initial project setup and prototype. Focus on setting up GitHub, project structure, database, and skeleton code.

Milestone 2: Complete the persistence layer with database integration and functionality.

### Milestone 3:

Scope: Finalize all features and non-persistence layers, such as frontend and logic.

#### Deliverable:

- Each team member has implemented the Service Layer, Controller, DTO, and Unit Tests for their respective modules, which include:
  - User Management
  - Course Management
  - Assessment and Grading
  - Discussion Forum
  - Notification
- Additionally, we configured the H2 database for the project. All tables are now generated and configured, allowing smooth testing and development.

#### Modules and Functionalities Completed:

Each module now has a fully implemented non-persistence layer with:

- Service Layer
- Controller Layer
- DTO (Data Transfer Object)
- Unit Testing

Each team member was responsible for implementing the above layers for their respective module.

#### Database Configuration:

The project uses an H2 database for the following reasons:

- Ease of Setup: H2 is lightweight, making it ideal for development and testing.
- In-Memory Option: For testing, we utilize the in-memory option, which speeds up test execution and reduces setup time.
- Automatic Table Generation: Tables for all modules (e.g., User, Course, Assignment, Discussion, Notification) are generated automatically through the JPA annotations and H2 configurations.

#### Decisions Made:

- Service Layer Logic: Each team member developed the logic necessary for their respective modules to function independently, improving modularity.
- Branching Strategy: Each team member worked on their module in a separate branch to avoid conflicts and facilitate independent testing. Merging into the main branch was done once each feature was completed and reviewed.

#### Lessons Learned:

- From now onwards we have decided to use separate branches for each feature and merging after all the conflicts have been resolved.
- While H2 is ideal for development and testing due to its speed and simplicity, we plan to transition to a more robust database for production deployment to ensure better data persistence and scalability.

Final Submission: Complete and polished LMS with all features implemented, tested, and documented.

Development Tools and Standards

1. IDE: IntelliJ or VS Code.
2. Coding Standards: Follow the Google Java Style Guide.
3. Version Control: Weekly code merges to resolve conflicts and sync changes.

Team Members & Responsibilities

| Name     | GitHub Username        | Feature                |
| -------- | ---------------------- | ---------------------- |
| Member 1 | ayadav6                | User Management        |
| Member 2 | JayalakshmiJayaprakash | Course Management      |
| Member 3 | ssamala1-code          | Assessment and Grading |
| Member 4 | Dhairya4256            | Discussion Forum       |
| Member 5 | shaunjiji              | Notification           |

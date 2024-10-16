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
Assignment Notifications: Students receive notifications when new assignments are posted or when deadlines are approaching.
Grading Notifications: Students are notified when an assignment has been graded.

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

Milestone 2:

Scope: Complete the persistence layer with database integration and functionality.

Persistence Layer:
- The persistence layer is implemented using a H2 database.
- We have used the Table per Class strategy.

Decisions Made:

- Decided to use separate branches for each feature and merging after all the conflicts have been resolved.
- Decided to use Lombok annotations like @Data.
- We decided to store data in H2 database for easy development and testing.
- Decided to implement Table per Class concept.

Lessons Learned:

- From now onwards we have decided to use separate branches for each feature and merging after all the conflicts have been resolved.
- Lombok annotations like @Data saved development time by eliminating the need for manual getter/setter generation.
- The Table per Class strategy reduces redundancy by storing common information in each table only once, keeping the database cleaner and more organized. Each class (e.g., Discussion, Comment, Reply) has its own table but shares common fields like content and created by.
- H2 Database: It was useful for fast development and testing, but we plan to switch to a more reliable database for the final version of the project when it is deployed.



Milestone 3: Finalize all features and non-persistence layers, such as frontend and logic.

Final Submission: Complete and polished LMS with all features implemented, tested, and documented.

Development Tools and Standards
1. IDE: IntelliJ or VS Code.
2. Coding Standards: Follow the Google Java Style Guide.
3. Version Control: Weekly code merges to resolve conflicts and sync changes.

Team Members & Responsibilities

| Name      | GitHub Username             | Feature                  | 
|-----------|-----------------------------|--------------------------|
| Member 1  | ayadav6                     | User Management          |
| Member 2  | JayalakshmiJayaprakash      | Course Management        |
| Member 3  | ssamala1-code               | Assessment and Grading   |
| Member 4  | Dhairya4256                 | Discussion Forum         |
| Member 5  | shaunjiji                   | Notification             |



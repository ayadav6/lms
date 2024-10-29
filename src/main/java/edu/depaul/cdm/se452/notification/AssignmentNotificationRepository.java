package edu.depaul.cdm.se452.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentNotificationRepository extends JpaRepository<AssignmentNotification, Long> {
}

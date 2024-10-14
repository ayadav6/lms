package edu.depaul.cdm.se452.discussion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}
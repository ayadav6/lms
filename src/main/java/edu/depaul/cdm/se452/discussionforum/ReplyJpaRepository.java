package edu.depaul.cdm.se452.discussionforum;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyJpaRepository extends JpaRepository<ReplyJpa, Long> {
    List<ReplyJpa> findByComment_Id(Long commentId); // Use Long as the type
}

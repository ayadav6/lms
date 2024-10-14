package edu.depaul.cdm.se452.discussion;

import jakarta.persistence.*;
import lombok.Data;
// Only keep imports that are used


@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends Post {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;
}

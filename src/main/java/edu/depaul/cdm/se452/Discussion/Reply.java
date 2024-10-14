package edu.depaul.cdm.se452.discussion;

import jakarta.persistence.*;
import lombok.Data;
// Only keep relevant imports


@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Reply extends Post {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
}
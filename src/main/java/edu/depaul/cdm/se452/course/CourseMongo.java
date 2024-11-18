package edu.depaul.cdm.se452.course;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "courses")
public class CourseMongo {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> materials;
    private Long instructorId;
}

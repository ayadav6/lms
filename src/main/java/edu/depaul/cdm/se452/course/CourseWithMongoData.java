package edu.depaul.cdm.se452.course;

import lombok.Data;

import java.util.List;

@Data
public class CourseWithMongoData {
    private Course course; // SQL course data
    private String description; // MongoDB description
    private List<String> materials; // MongoDB materials
}

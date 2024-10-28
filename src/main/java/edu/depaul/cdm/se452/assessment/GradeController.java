package edu.depaul.cdm.se452.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@Tag(name = "Grades", description = "Everything about Grades")
@Log4j2
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    @Operation(summary = "Create a new grade ")
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
        log.traceEntry("Entering createGrade with gradeDTO: {}", gradeDTO);
        GradeDTO createdGrade = gradeService.createGrade(gradeDTO);
        log.info("Grade created with ID: {}", createdGrade.getId());
        log.traceExit("Exiting createGrade with created grade: {}", createdGrade);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a grade by ID ")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long id) {
        log.traceEntry("Entering getGrade with ID: {}", id);
        GradeDTO gradeDTO = gradeService.getGradeById(id);
        log.info("Retrieved grade with ID: {}", id);
        log.traceExit("Exiting getGrade with gradeDTO: {}", gradeDTO);
        return ResponseEntity.ok(gradeDTO);
    }

    @GetMapping
    @Operation(summary = "Get all grades")
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        log.traceEntry("Entering getAllGrades");
        List<GradeDTO> grades = gradeService.getAllGrades();
        log.info("Retrieved all grades, total count: {}", grades.size());
        log.traceExit("Exiting getAllGrades with grade list");
        return ResponseEntity.ok(grades);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing grade ")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @RequestBody GradeDTO gradeDTO) {
        log.traceEntry("Entering updateGrade with ID: {} and gradeDTO: {}", id, gradeDTO);
        GradeDTO updatedGrade = gradeService.updateGrade(id, gradeDTO);
        log.info("Grade with ID: {} updated successfully", id);
        log.traceExit("Exiting updateGrade with updated gradeDTO: {}", updatedGrade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a particular grade")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        log.traceEntry("Entering deleteGrade with ID: {}", id);
        gradeService.deleteGrade(id);
        log.info("Grade with ID: {} deleted successfully", id);
        log.traceExit("Exiting deleteGrade");
        return ResponseEntity.noContent().build();
    }
}

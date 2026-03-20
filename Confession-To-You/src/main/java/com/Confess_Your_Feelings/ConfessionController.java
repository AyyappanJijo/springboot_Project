package com.Confess_Your_Feelings;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confessions")
public class ConfessionController {

    @Autowired
    private ConfessionService confessionService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ConfessionDTO>> addConfession(
            @Valid @RequestBody ConfessionDTO dto) {
        ConfessionDTO saved = confessionService.saveConfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Confession added successfully", saved));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ConfessionDTO>>> getAllConfessions() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Fetched all confessions", confessionService.getAllConfessions()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConfessionDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Confession found", confessionService.getConfessionById(id)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ConfessionDTO>> updateConfession(
            @PathVariable Long id, @Valid @RequestBody ConfessionDTO dto) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Confession updated", confessionService.updateConfession(id, dto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteConfession(@PathVariable Long id) {
        confessionService.deleteConfession(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Confession deleted successfully", null));
    }
}
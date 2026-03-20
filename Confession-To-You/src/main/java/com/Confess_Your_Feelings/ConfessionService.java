package com.Confess_Your_Feelings;

import java.util.List;

public interface ConfessionService {
    ConfessionDTO saveConfession(ConfessionDTO dto);
    List<ConfessionDTO> getAllConfessions();
    ConfessionDTO getConfessionById(Long id);
    ConfessionDTO updateConfession(Long id, ConfessionDTO dto);
    void deleteConfession(Long id);
}
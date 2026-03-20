package com.Confess_Your_Feelings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfessionServiceImpl implements ConfessionService {

    @Autowired
    private ConfessionRepository confessionRepository;

    private ConfessionDTO toDTO(Confession confession) {
        ConfessionDTO dto = new ConfessionDTO();
        dto.setId(confession.getId());
        dto.setSenderName(confession.getSenderName());
        dto.setReceiverName(confession.getReceiverName());
        dto.setMessage(confession.getMessage());
        dto.setMood(confession.getMood());
        return dto;
    }

    private Confession toEntity(ConfessionDTO dto) {
        Confession confession = new Confession();
        confession.setSenderName(dto.getSenderName());
        confession.setReceiverName(dto.getReceiverName());
        confession.setMessage(dto.getMessage());
        confession.setMood(dto.getMood());
        return confession;
    }

    @Override
    public ConfessionDTO saveConfession(ConfessionDTO dto) {
        return toDTO(confessionRepository.save(toEntity(dto)));
    }

    @Override
    public List<ConfessionDTO> getAllConfessions() {
        return confessionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConfessionDTO getConfessionById(Long id) {
        Confession confession = confessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Confession not found with ID: " + id));
        return toDTO(confession);
    }

    @Override
    public ConfessionDTO updateConfession(Long id, ConfessionDTO dto) {
        Confession existing = confessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Confession not found with ID: " + id));
        existing.setSenderName(dto.getSenderName());
        existing.setReceiverName(dto.getReceiverName());
        existing.setMessage(dto.getMessage());
        existing.setMood(dto.getMood());
        return toDTO(confessionRepository.save(existing));
    }

    @Override
    public void deleteConfession(Long id) {
        Confession confession = confessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Confession not found with ID: " + id));
        confessionRepository.delete(confession);
    }
}
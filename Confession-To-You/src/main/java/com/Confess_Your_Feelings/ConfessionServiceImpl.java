package com.Confess_Your_Feelings;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfessionServiceImpl implements ConfessionService {

    @Autowired
    private ConfessionRepository confessionRepository;

    @Override
    public Confession saveConfession(Confession confession) {
        return confessionRepository.save(confession);
    }

    @Override
    public List<Confession> getAllConfessions() {
        return confessionRepository.findAll();
    }

    @Override
    public Confession getConfessionById(Long id) {
        // Find confession by ID, or throw an exception if not found
        return confessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confession not found with ID: " + id));
    }

    @Override
    public Confession updateConfession(Long id, Confession confessionDetails) {
        // Step 1: Get existing confession
        Confession existingConfession = getConfessionById(id);

        // Step 2: Update the fields
        existingConfession.setSenderName(confessionDetails.getSenderName());
        existingConfession.setReceiverName(confessionDetails.getReceiverName());
        existingConfession.setMessage(confessionDetails.getMessage());
        existingConfession.setMood(confessionDetails.getMood());

        // Step 3: Save and return updated object
        return confessionRepository.save(existingConfession);
    }

    @Override
    public void deleteConfession(Long id) {
        // Step 1: Check if the record exists
        Confession confession = getConfessionById(id);

        // Step 2: Delete the record
        confessionRepository.delete(confession);
    }
}

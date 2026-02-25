package com.Confess_Your_Feelings;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/confessions")
public class ConfessionController {

    @Autowired
    private ConfessionService confessionService;

    
    @PostMapping("/add")
    public Confession addConfession(@RequestBody Confession confession) {
        return confessionService.saveConfession(confession);
    }
  

    
    @GetMapping("/all")
    public List<Confession> getAllConfessions() {
        return confessionService.getAllConfessions();
    }

    
    @GetMapping("/{id}")
    public Confession getConfessionById(@PathVariable Long id) {
        return confessionService.getConfessionById(id);
    }

    
    @PutMapping("/update/{id}")
    public Confession updateConfession(@PathVariable Long id, @RequestBody Confession confessionDetails) {
        return confessionService.updateConfession(id, confessionDetails);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteConfession(@PathVariable Long id) {
        confessionService.deleteConfession(id);
        return "Confession with ID " + id + " deleted successfully.";
    }
}


package com.Infy.Interns_Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Infy.Interns_Management.Dto.InternDTO;
import com.Infy.Interns_Management.Entity.Intern;
import com.Infy.Interns_Management.Service.InternService;


@RestController
@RequestMapping("/api/interns")
public class InternController {
	
	@Autowired
	private InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    @PostMapping(value = "/upload", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadExcel(@RequestParam("file") MultipartFile file) {

        internService.uploadInternsFromExcel(file);
        return "Excel uploaded successfully!";
    }


    @PostMapping
    public Intern addIntern(@RequestBody InternDTO dto) {
        return internService.addIntern(dto);
    }

    @GetMapping
    public List<Intern> getAllInterns() {
        return internService.getAllInterns();
    }

    @DeleteMapping("/{internId}")
    public String deleteIntern(@PathVariable String internId) {
        internService.deleteIntern(internId);
        return "Intern deleted successfully";
    }
    
    @PutMapping("/{internId}/assign-mentor/{mentorId}")
    public Intern assignMentor(@PathVariable String internId,
                               @PathVariable Long mentorId) {
        return internService.assignMentor(internId, mentorId);
    }
    
    @PutMapping("/{internId}")
    public Intern updateIntern(@PathVariable String internId,
                               @RequestBody InternDTO dto) {
        return internService.updateIntern(internId, dto);
    }

}

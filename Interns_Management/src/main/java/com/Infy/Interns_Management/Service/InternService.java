package com.Infy.Interns_Management.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Infy.Interns_Management.Dto.InternDTO;
import com.Infy.Interns_Management.Entity.Intern;

public interface InternService {
	
	void uploadInternsFromExcel(MultipartFile file);

    Intern addIntern(InternDTO dto);

    Intern updateIntern(String internId, InternDTO dto);

    void deleteIntern(String internId);

    List<Intern> getAllInterns();

    Intern getInternById(String internId);
    
    Intern assignMentor(String internId, Long mentorId);

}

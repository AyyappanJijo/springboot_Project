package com.Infy.Interns_Management.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Infy.Interns_Management.Dto.InternDTO;
import com.Infy.Interns_Management.Entity.Intern;
import com.Infy.Interns_Management.Entity.Mentor;
import com.Infy.Interns_Management.Exception.ResourceNotFoundException;
import com.Infy.Interns_Management.Repository.InternRepository;
import com.Infy.Interns_Management.Repository.MentorRepository;
import com.Infy.Interns_Management.Service.InternService;
import com.Infy.Interns_Management.Util.ExcelHelper;

@Service
public class InternServiceImpl implements InternService {
	
	@Autowired
	private InternRepository internRepository;
	@Autowired
    private MentorRepository mentorRepository;
	@Autowired
    private ExcelHelper excelHelper;

    public InternServiceImpl(InternRepository internRepository,
                             MentorRepository mentorRepository,
                             ExcelHelper excelHelper) {
        this.internRepository = internRepository;
        this.mentorRepository = mentorRepository;
        this.excelHelper = excelHelper;
    }

    @Override
    public void uploadInternsFromExcel(MultipartFile file) {

        try {
            List<Intern> interns = excelHelper.convertExcelToInternList(file.getInputStream());
            internRepository.saveAll(interns);
        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }

    @Override
    public Intern addIntern(InternDTO dto) {
        
        Mentor mentor = null;

        if (dto.getMentorId() != null) {
            mentor = mentorRepository.findById(dto.getMentorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Mentor not found"));
        }

        Intern intern = new Intern();
        intern.setInternId(dto.getInternId());
        intern.setInternName(dto.getInternName());
        intern.setEmail(dto.getEmail());
        intern.setPhoneNumber(dto.getPhoneNumber());
        intern.setCollege(dto.getCollege());
        intern.setDomain(dto.getDomain());
        intern.setInternshipStartDate(dto.getInternshipStartDate());
        intern.setInternshipEndDate(dto.getInternshipEndDate());
        intern.setStatus(dto.getStatus());
        intern.setMentor(mentor);

        return internRepository.save(intern);
    }

    @Override
    public List<Intern> getAllInterns() {
        return internRepository.findAll();
    }

    @Override
    public Intern getInternById(String internId) {
        return internRepository.findById(internId)
                .orElseThrow(() -> new ResourceNotFoundException("Intern not found"));
    }

    @Override
    public void deleteIntern(String internId) {
        internRepository.deleteById(internId);
    }

    @Override
    public Intern updateIntern(String internId, InternDTO dto) {
        Intern intern = getInternById(internId);

        intern.setInternName(dto.getInternName());
        intern.setEmail(dto.getEmail());
        intern.setPhoneNumber(dto.getPhoneNumber());
        intern.setCollege(dto.getCollege());
        intern.setDomain(dto.getDomain());
        intern.setStatus(dto.getStatus());

        return internRepository.save(intern);
    }
    
    @Override
    public Intern assignMentor(String internId, Long mentorId) {

        Intern intern = getInternById(internId);
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found"));

        intern.setMentor(mentor);
        return internRepository.save(intern);
    }


}

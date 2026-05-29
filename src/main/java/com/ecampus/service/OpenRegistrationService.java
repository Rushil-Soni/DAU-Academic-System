package com.ecampus.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecampus.model.RegistrationOpenFor;
import com.ecampus.repository.ProgramsRepository;
import com.ecampus.repository.RegistrationOpenForRepository;
import com.ecampus.repository.SchemeDetailsRepository;

@Service
public class OpenRegistrationService {

    @Autowired
    private SchemeDetailsRepository SchemeDetailsRepo;

    @Autowired
    private ProgramsRepository ProgramsRepo;

    @Autowired
    private RegistrationOpenForRepository RegistrationOpenForRepo;
    
    public List<Object[]> getBatchSpl(List<String> batches, Long termid, String registrationtype){
        return SchemeDetailsRepo.getBchSpl(batches, termid, registrationtype);
    }

    public String getUGPG(Long scheme_id){
        return ProgramsRepo.getUGPGByScheme(scheme_id);
    }

    public void saveRegistrationDate(LocalDateTime startDate, LocalDateTime endDate, Long termid, List<Long> bchids, String type){

        for(Long bchid : bchids){
            RegistrationOpenFor rof = RegistrationOpenForRepo.getRofByTrmBch(termid,bchid,type);
            if(rof != null){
                rof.setStartdate(startDate);
                rof.setEnddate(endDate);

                RegistrationOpenForRepo.save(rof);
            }
            else{
                rof = new RegistrationOpenFor();
                rof.setTermid(termid);
                rof.setBatchid(bchid);
                rof.setRegistrationtype(type);
                rof.setStartdate(startDate);
                rof.setEnddate(endDate);

                RegistrationOpenForRepo.save(rof);
            }
        }

    }

}

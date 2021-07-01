package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.HrCompany;
import com.dimata.logbookAPI.repository.HrCompanyRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class HrCompanyService {
    @Autowired
    private HrCompanyRepo hrCompanyRepo;

    public HrCompany create(HrCompany hrCompany){
        hrCompany.setCompanyId(GenerateOID.generateOID());
        hrCompany.setCompany(hrCompany.getCompany());
        hrCompany.setDescription(hrCompany.getDescription());
        hrCompany.setCompanyCode(GenerateOID.generateOID());
        return hrCompanyRepo.save(hrCompany);
    }

    public HrCompany update(HrCompany hrCompany){
        return hrCompanyRepo.save(hrCompany);
    }

    public void delete(Long id){
        hrCompanyRepo.deleteById(id);
    }

    public Optional<HrCompany> getDetailCompany (Long id){
        return hrCompanyRepo.findById(id);
    }

    public Iterable<HrCompany> findAll(){
        return hrCompanyRepo.findAll();
    }


}

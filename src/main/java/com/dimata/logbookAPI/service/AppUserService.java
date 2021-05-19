package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

//    inget harus sesuai dengan penulisan code seberapa dia panjanga charcternya
    public AppUser create(AppUser appUser){
        appUser.setUserId(GenerateOID.generateOID());
        appUser.setEmployeeId(123L);
        return appUserRepo.save(appUser);
    }

    public Iterable<AppUser> findAll(){
        return appUserRepo.findAll();
    }

}

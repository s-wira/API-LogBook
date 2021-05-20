package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.dto.ResponseData;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public List<AppUser> findByLoginId(String loginId){
        return appUserRepo.findByLoginId(loginId);
    }

    public void removeOne(Long id){
        appUserRepo.deleteById(id);
    }

    public AppUser update(AppUser appUser){
        return appUserRepo.save(appUser);
    }

    //fungsi Search User Berdasarkan ID
    public AppUser findUserId(Long id){
        Optional<AppUser> appUser = appUserRepo.findById(id);
        if (!appUser.isPresent()){
            return null;
        }
        return appUser.get();

    }



}

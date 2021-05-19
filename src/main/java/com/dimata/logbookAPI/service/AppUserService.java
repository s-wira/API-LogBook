package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    public AppUser create(AppUser appUser){
        return appUserRepo.save(appUser);
    }

    public Iterable<AppUser> findAll(){
        return appUserRepo.findAll();
    }

}

package com.dimata.logbookAPI.controller;


import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AppUser create (@RequestBody AppUser appUser){

        return appUserService.create(appUser);
    }

    @GetMapping
    public Iterable<AppUser> findAll(){
        return appUserService.findAll();
    }


}

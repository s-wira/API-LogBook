package com.dimata.logbookAPI.controller;


import com.dimata.logbookAPI.dto.ResponseData;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<AppUser>> create (@Valid @RequestBody AppUser appUser, Errors errors){
        ResponseData<AppUser> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(appUserService.create(appUser));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);
    }

//    FindAll Data User Pada Database
    @GetMapping
    public Iterable<AppUser> findAll(){
        return appUserService.findAll();
    }

//    Cari berdasarkan Username atau loginId
    @GetMapping("/{username}")
    public List<AppUser> findByLoginID (@PathVariable("username") String username){
        return appUserService.findByLoginId(username);
    }

//    Fungsi Update pada Controller dengan menyertakan Request ID
    @PutMapping
    public AppUser update (@RequestBody AppUser appUser){
        return appUserService.update(appUser);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") Long id){
        appUserService.removeOne(id);
    }

    @GetMapping("/{id}")
    public AppUser findByUserId(@PathVariable("id")Long id){
        return appUserService.findUserId(id);
    }




}

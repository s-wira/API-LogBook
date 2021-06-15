package com.dimata.logbookAPI.controller;


import com.dimata.logbookAPI.dto.LoginUserDTO;
import com.dimata.logbookAPI.dto.ResponseData;
import com.dimata.logbookAPI.dto.model.AppUserDTO;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.service.AppUserService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private ModelMapper modelMapper;

    //Tanpa menggunakan tambahan DTO dan belum model Mapper
//    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public ResponseEntity<ResponseData<AppUser>> create (@Valid @RequestBody AppUser appUser, Errors errors){
//        ResponseData<AppUser> responseData = new ResponseData<>();
//        if(errors.hasErrors()){
//            for(ObjectError error : errors.getAllErrors()){
//                responseData.getMessage().add(error.getDefaultMessage());
//            }
//            responseData.setStatus(false);
//            responseData.setPayload(null);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//        }
//        responseData.setStatus(true);
//        responseData.setPayload(appUserService.create(appUser));
//        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
//        return ResponseEntity.ok(responseData);
//    }

//    FindAll Data User Pada Database
    @GetMapping
    public Iterable<AppUser> findAll(){
        return appUserService.findAll();
    }

//    Cari berdasarkan Username atau loginId
    @GetMapping("findByUsername/{username}")
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


    //    Cuman Exmaple Untuk penggunaan DTO user model mapper
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<AppUser>> createUseDTO (@Valid @RequestBody AppUserDTO appUserDTO, Errors errors){
        ResponseData<AppUser> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        //deklarasikan awal untuk create user manual tanpa model mapper
//        AppUser appUser = new AppUser();
//        appUser.setLoginId(appUserDTO.getLoginId());
//        appUser.setFullName(appUserDTO.getFullName());
//        appUser.setEmail(appUserDTO.getEmail());
//        appUser.setPassword(appUserDTO.getPassword());

        // use model library mapper untuk sebuah data
        AppUser appUser = modelMapper.map(appUserDTO, AppUser.class);

        responseData.setStatus(true);
        responseData.setPayload(appUserService.create(appUser));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);
    }


//    @PostMapping("/login")
//    public ResponseEntity<ResponseData<AppUser>> loginUser (@RequestBody LoginUserDTO loginUserDTO){
//        ResponseData<AppUser> responseData = new ResponseData<>();
//        List<AppUser> login = appUserRepo.findByLoginIdAndPassword(loginUserDTO.getLoginId(),loginUserDTO.getPassword());
//        if (login.isEmpty()){
//            responseData.setMessage(Collections.singletonList("Username atau Password anda Salah!!"));
//            responseData.setPayload(null);
//            responseData.setStatus(false);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//        }
////        AppUser appUser = modelMapper.map(loginUserDTO, AppUser.class);
//
//        responseData.setStatus(true);
//        responseData.setMessage(Collections.singletonList("Anda Berhasil Login"));
//        responseData.setPayload(appUserService.login(loginUserDTO.getLoginId(),loginUserDTO.getPassword()));
//        return ResponseEntity.ok(responseData);
//
//
//    }

    @PostMapping("/login")
    public AppUser loginUser (@RequestBody LoginUserDTO loginUserDTO) {
        return appUserService.login(loginUserDTO.getLoginId(), loginUserDTO.getPassword());

    }

    @PostMapping("/findByUserId")
    public AppUser findByUserId (@RequestBody AppUserDTO appUserDTO) {
        return appUserService.findUserId(appUserDTO.getUserId());
    }


//    @PostMapping("/login")
//    public ResponseEntity<ResponseData<AppUser>> loginUser (@RequestBody LoginUserDTO loginUserDTO){
//        ResponseData<AppUser> responseData = new ResponseData<>();
//        List<AppUser> login = appUserRepo.findByLoginIdAndPassword(loginUserDTO.getLoginId(),loginUserDTO.getPassword());
//        if (login.isEmpty()){
//            responseData.setMessage(Collections.singletonList("Username atau Password anda Salah!!"));
//            responseData.setPayload(null);
//            responseData.setStatus(false);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//        }
////        AppUser appUser = modelMapper.map(loginUserDTO, AppUser.class);
//
//        responseData.setStatus(true);
//        responseData.setMessage(Collections.singletonList("Anda Berhasil Login"));
//        responseData.setPayload(appUserService.login(loginUserDTO.getLoginId(),loginUserDTO.getPassword()));
//        return ResponseEntity.ok(responseData);
//
//
//    }


}

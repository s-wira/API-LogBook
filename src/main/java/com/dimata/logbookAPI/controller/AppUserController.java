package com.dimata.logbookAPI.controller;


import com.dimata.logbookAPI.dto.LoginUserDTO;
import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.dto.model.AppUserDTO;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.HrCompany;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.repository.HrCompanyRepo;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private HrCompanyRepo hrCompanyRepo;

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

        Optional<HrCompany> hrCompany = hrCompanyRepo.findByCompanyCode(appUserDTO.getCompanyCode());
        if (!hrCompany.isPresent()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Company Code Anda Salah"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }


//        AppUser appUser = modelMapper.map(appUserDTO, AppUser.class);
        AppUser appUser = new AppUser();
        appUser.setLoginId(appUserDTO.getLoginId());
        appUser.setFullName(appUserDTO.getFullName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setCompanyId(hrCompany.get().getCompanyId());

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

    @PostMapping("add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<AppUser>> createUser (@Valid @RequestBody AppUserDTO appUserDTO, Errors errors){
        ResponseData<AppUser> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        AppUser appUser = new AppUser();
        appUser.setLoginId(appUserDTO.getLoginId());
        appUser.setFullName(appUserDTO.getFullName());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setCompanyId(appUserDTO.getCompanyId());
        appUser.setEmployeeId(appUserDTO.getEmployeeId());

        responseData.setStatus(true);

        responseData.setPayload(appUserService.create2(appUser));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<AppUser>> delete (@RequestBody AppUserDTO appUserDTO){
        ResponseData<AppUser> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Menghapus Data User "+ appUserDTO.getUserId()));
        appUserService.removeOne(appUserDTO.getUserId());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/list-user-admin")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<AppUser>> getListUserAdmin (){
        ResponseDataList<AppUser> responseDataList = new ResponseDataList<>();
        List<AppUser> admin = appUserRepo.findByEmployeeId(1L);
        if (admin.isEmpty()){
            responseDataList.setStatus(false);
            responseDataList.setPayload(null);
            responseDataList.setMessage(Collections.singletonList("Belum Ada Data Tiket yang Anda Tambahkan!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDataList);
        }
        responseDataList.setStatus(true);
        responseDataList.setMessage(Collections.singletonList("Berhasil Mengambil Data Tiket"));
        responseDataList.setPayload(admin);
        return ResponseEntity.ok(responseDataList);
    }

    @PostMapping("/list-user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<AppUser>> getListUser (@RequestBody AppUserDTO appUserDTO){
        ResponseDataList<AppUser> responseDataList = new ResponseDataList<>();
        List<AppUser> user = appUserRepo.findByEmployeeIdAndCompanyId(0L,appUserDTO.getCompanyId());
        if (user.isEmpty()){
            responseDataList.setStatus(false);
            responseDataList.setPayload(null);
            responseDataList.setMessage(Collections.singletonList("Belum Ada Data User yang Anda Tambahkan!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDataList);
        }
        responseDataList.setStatus(true);
        responseDataList.setMessage(Collections.singletonList("Berhasil Mengambil Data User"));
        responseDataList.setPayload(user);
        return ResponseEntity.ok(responseDataList);
    }
    // ini tutoral belajar 

}

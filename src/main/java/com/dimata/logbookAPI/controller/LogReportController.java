package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.*;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.service.AppUserService;
import com.dimata.logbookAPI.service.LogNotificationService;
import com.dimata.logbookAPI.service.LogReportService;
import com.dimata.logbookAPI.service.LogReportTypeService;
import com.dimata.logbookAPI.utility.GenerateOID;
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
@RequestMapping("/api/log-report")
public class LogReportController {

    @Autowired
    private LogReportTypeService logReportTypeService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LogReportService logReportService;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private LogNotificationService logNotificationService;


    @GetMapping("report-all")
    public Iterable<LogReportType> findAll(){
        return logReportTypeService.findAll();
    }

//    Get data User yang digunakan untuk add Tiket
    @GetMapping("app-user")
    public Iterable<AppUser> getDataAllUser(){
        return appUserService.findAll();
    }

    //
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<LogReport>> createLog(@RequestBody LogReportDTO logReportDTO){
        ResponseData<LogReport> responseData = new ResponseData<>();
        LogReport logReport = modelMapper.map(logReportDTO, LogReport.class);
        responseData.setStatus(true);
        responseData.setPayload(logReportService.create(logReport));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);

    }

    @GetMapping
    public Iterable<LogReport>findAllReport(){
        return logReportService.findAll();
    }

    @PostMapping("id-report-type")
    public LogReportType getIdType(@RequestBody LogReportType logReportType){
        return logReportTypeService.getIdReportType(logReportType.getTypeName());
    }

    @PostMapping("/category")
    public List<LogCategory> getLogCategory (@RequestBody LogCategory logCategory) {
        return logReportService.findLogCategory(logCategory.getRptTypeId());
    }

    @GetMapping("/chapter")
    public Iterable<LogPasalUmum> findAllPasal(){
        return logReportService.findAllLogPasal();
    }

//    @PostMapping("/count-status")
//    public Long countStatus (@RequestBody LogReportDTO logReportDTO){
//        return logReportService.countStatusRpt(logReportDTO.getStatusRpt());
//    }

    @GetMapping("/status-report/{report}")
    public Iterable<LogReport> findStatusReport(@PathVariable("report") String report){
        return logReportService.findStatusRpt(report);
    }


    /// =============== > Start <===============//

    //Get Data PIC User Untuk Add Tiket
    @GetMapping("/get-pic")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<AppUser>> getPicUser (){
        ResponseDataList<AppUser> responseDataList = new ResponseDataList<>();
        List<AppUser> picUser = appUserRepo.findByEmployeeId(1L);
        if (picUser.isEmpty()){
            responseDataList.setStatus(false);
            responseDataList.setPayload(null);
            responseDataList.setMessage(Collections.singletonList("Belum Ada Data Tiket yang Anda Tambahkan!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDataList);
        }
        responseDataList.setStatus(true);
        responseDataList.setMessage(Collections.singletonList("Berhasil Mengambil Data Tiket"));
        responseDataList.setPayload(picUser);
        return ResponseEntity.ok(responseDataList);
    }


    // Add tiket With Notification User
    @PostMapping("add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<LogReport>> addTiket (@Valid  @RequestBody LogReportDTO logReportDTO, Errors errors){
        ResponseData<LogReport> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        LogNotification logNotification = new LogNotification();
        LogReport logReport = new LogReport();

        Long reportId = GenerateOID.generateOID();

        Optional<AppUser> user = appUserRepo.findById(logReportDTO.getReportByUserId());

        logReport.setLogReportId(reportId);
        logReport.setLogDesc(logReportDTO.getLogDesc());
        logReport.setRptTypeId(logReportDTO.getRptTypeId());
        logReport.setPasalUmumId(logReportDTO.getPasalUmumId());
        logReport.setReportByUserId(logReportDTO.getReportByUserId());
        logReport.setPicUserId(logReportDTO.getPicUserId());
        logReport.setStatusRpt(logReportDTO.getStatusRpt());
        logReport.setCompanyId(user.get().getCompanyId());

        logNotification.setReportId(reportId);
        logNotification.setUserId(logReportDTO.getPicUserId());
        logNotification.setLogNotification("You Have Report by " + user.get().getFullName());
        logNotificationService.create(logNotification);

        responseData.setStatus(true);
        responseData.setPayload(logReportService.create(logReport));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);

    }




}

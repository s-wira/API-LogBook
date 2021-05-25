package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.ResponseData;
import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.model.LogReportType;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.repository.LogReportTypeRepo;
import com.dimata.logbookAPI.service.AppUserService;
import com.dimata.logbookAPI.service.LogReportService;
import com.dimata.logbookAPI.service.LogReportTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.logging.LogRecord;

@RestController
@RequestMapping("/api/logreport")
public class LogReportController {

    @Autowired
    private LogReportTypeService logReportTypeService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LogReportService logReportService;


    @GetMapping
    public Iterable<LogReportType> findAll(){
        return logReportTypeService.findAll();
    }

//    Get data User yang digunakan untuk add Tiket
    @GetMapping("app_user")
    public Iterable<AppUser> getDataAllUser(){
        return appUserService.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<LogReport>> createLog(@RequestBody LogReportDTO logReportDTO){
        ResponseData<LogReport> responseData = new ResponseData<>();

        LogReport logReport = modelMapper.map(logReportDTO, LogReport.class);
        responseData.setPayload(logReportService.create(logReport));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);

    }

    @GetMapping("log-report")
    public Iterable<LogReport>findAllReport(){
        return logReportService.findAll();
    }






}

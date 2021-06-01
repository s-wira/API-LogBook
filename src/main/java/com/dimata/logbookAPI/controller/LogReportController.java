package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.LoginUserDTO;
import com.dimata.logbookAPI.dto.ResponseData;
import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.model.*;
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
import java.util.List;
import java.util.logging.LogRecord;

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


    @GetMapping("report-all")
    public Iterable<LogReportType> findAll(){
        return logReportTypeService.findAll();
    }

//    Get data User yang digunakan untuk add Tiket
    @GetMapping("app-user")
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


}

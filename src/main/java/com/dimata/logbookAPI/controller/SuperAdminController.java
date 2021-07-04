package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.service.LogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/super-admin")
public class SuperAdminController {
    @Autowired
    private LogReportRepo logReportRepo;

    @Autowired
    private LogReportService logReportService;

    @PostMapping("/count/{status}")
    public Long countStatus (@PathVariable("status") String status, @RequestBody LogReport logReport){
        return logReportService.countStatusAdmin (status, logReport);
    }

    @PostMapping("/status-report/{report}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getDetailStatusMyTiket (@PathVariable("report") String report, @RequestBody LogReport logReport){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReports = logReportRepo.findByStatusRptAndPicUserId(report,logReport.getPicUserId());
        if (logReports.isEmpty()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Belum Ada Data Tiket yang di Tambahkan!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Mengambil Data Tiket"));
        responseData.setPayload(logReports);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/status/my-tiket/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getMyTiket (@PathVariable("id") Integer status, @RequestBody LogReportDTO userId){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReport = logReportRepo.findByStatusAndPicUserId(status,userId.getPicUserId());
        if (logReport.isEmpty()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Belum Ada Data Tiket yang Anda Tambahkan!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Mengambil Data Tiket"));
        responseData.setPayload(logReport);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/get/follow-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getFollowUp (@RequestBody LogReportDTO logReportDTO){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReport = logReportRepo.findByPicUserId(logReportDTO.getPicUserId());
        if (logReport.isEmpty()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Belum Ada Data Tiket yang Anda Tambahkan!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Mengambil Data Tiket"));
        responseData.setPayload(logReport);
        return ResponseEntity.ok(responseData);
    }





}

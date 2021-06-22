package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.service.LogReportService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private LogReportService logReportService;

    @Autowired
    private LogReportRepo logReportRepo;
    
    // My Tiket Data Yang Berhasil di Ambil Dalam Bentuk Arrya List Data Tiket Seorang tesebut
    @PostMapping("/status/my-tiket/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getMyTiket (@PathVariable("id") Integer status, @RequestBody LogReportDTO userId){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReport = logReportRepo.findByStatusAndReportByUserId(status,userId.getReportByUserId());
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

    // MyCompany Tiket Data Yang Berhasil di Ambil Dalam Bentuk Arrya List Data Tiket Seorang tesebut
    @PostMapping("/status/company-tiket/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getMyCompanyTiket (@PathVariable("id") Integer status, @RequestBody LogReportDTO userId){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReport = logReportRepo.findByStatusAndCompanyId(status,userId.getCompanyId());
        if (logReport.isEmpty()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Belum Ada Data Tiket yang Pada Company Anda!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Mengambil Data Tiket"));
        responseData.setPayload(logReport);
        return ResponseEntity.ok(responseData);

    }

    //==============> count status my tiket <==============
    @GetMapping("/count-status-my-tiket/{status}")
    public Long countStatus (@PathVariable("status") String status, @RequestBody LogReport logReport){
        return logReportService.countStatusRptMyTiket (status, logReport);
    }

    //==============> count status my company status <==============
    @GetMapping("/count-status-my-company/{status}")
    public Long countStatusMyCompany (@PathVariable("status") String status, @RequestBody LogReport logReport){
        return logReportService.countStatusRptMyCompany (status, logReport);
    }


    @PostMapping("/status-report/my-tiket/{report}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getDetailStatusMyTiket (@PathVariable("report") String report, @RequestBody LogReport logReport){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReports = logReportRepo.findByStatusRptAndReportByUserId(report,logReport.getReportByUserId());
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

    @PostMapping("/status-report/company-tiket/{report}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDataList<LogReport>> getDetailStatusCompanyTiket (@PathVariable("report") String report, @RequestBody LogReport logReport){
        ResponseDataList<LogReport> responseData = new ResponseDataList<>();
        List<LogReport> logReports = logReportRepo.findByStatusRptAndCompanyId(report,logReport.getCompanyId());
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




//    @GetMapping("/status-report/{report}")
//    public Iterable<LogReport> findStatusReport(@PathVariable("report") String report){
//        return logReportService.findStatusRpt(report);
//    }


    @PostMapping("/detail/tiket")
    public LogReport findByReportId (@RequestBody LogReport logReport){
        return logReportService.findByLogReportNumber(logReport.getLogNumber());
    }




}

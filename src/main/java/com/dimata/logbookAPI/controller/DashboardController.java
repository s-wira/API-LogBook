package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.service.LogReportService;
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

//    @GetMapping("/status/{id}")
//    public List<LogReport> findByStatus (@PathVariable("id") Integer status, @RequestBody AppUser appUser){
//        return logReportService.findAllStatus(status, appUser);
//    }


    // Data Yang Berhasil di Ambil Dalam Bentuk Arrya List Data Tiket Seorang tesebut
    @PostMapping("/status/{id}")
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



    @GetMapping("/status-report/{report}")
    public Iterable<LogReport> findStatusReport(@PathVariable("report") String report){
        return logReportService.findStatusRpt(report);
    }

    // user ID dari tiket tersebut
//    @PostMapping("/detail/tiket")
//    public LogReport findByReportId (@RequestBody LogReport logReport){
//        return logReportService.findLogReportId(logReport.getLogReportId());
//    }

    @GetMapping("/count-status/{status}")
    public Long countStatus (@PathVariable("status") String status){
        return logReportService.countStatusRpt(status);
    }


    @PostMapping("/detail/tiket")
    public LogReport findByReportId (@RequestBody LogReport logReport){
        return logReportService.findByLogReportNumber(logReport.getLogNumber());
    }




}

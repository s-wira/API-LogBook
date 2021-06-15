package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.service.LogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private LogReportService logReportService;

//    @GetMapping("/{id}")
//    public Long countInProgres(@PathVariable("id")Integer id){
//        return logReportService.countStatus(id);
//    }

    @GetMapping("/status/{id}")
    public List<LogReport> findByStatus (@PathVariable("id") Integer status){
        return logReportService.findAllStatus(status);
    }

    @GetMapping("/status-report/{report}")
    public Iterable<LogReport> findStatusReport(@PathVariable("report") String report){
        return logReportService.findStatusRpt(report);
    }

    @PostMapping("/detail/tiket")
    public LogReport findByReportId (@RequestBody LogReport logReport){
        return logReportService.findLogReportId(logReport.getLogReportId());
    }


}

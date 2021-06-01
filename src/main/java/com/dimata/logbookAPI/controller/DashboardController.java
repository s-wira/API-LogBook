package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.service.LogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

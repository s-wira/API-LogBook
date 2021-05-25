package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.service.LogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    LogReportService logReportService;

//    @GetMapping("/{id}")
//    public Integer countInProgres(@PathVariable("id")Integer id){
//        return logReportService.countStatus(id);
//    }



}

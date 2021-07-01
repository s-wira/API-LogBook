package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogFollowUp;
import com.dimata.logbookAPI.model.LogNotification;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogFollowUpRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogFollowUpService {

    @Autowired
    private LogFollowUpRepo logFollowUpRepo;

    @Autowired
    private LogReportRepo logReportRepo;

    @Autowired
    private LogNotificationService logNotificationService;

    @Autowired
    private AppUserService appUserService;


    public LogFollowUp create(LogFollowUp logFollowUp){
        LocalDate localDate = LocalDate.now();
        logFollowUp.setFollowUpId(GenerateOID.generateOID());
        logFollowUp.setStartDateTime(localDate);
        logFollowUp.setEndDateTime(localDate);

        Optional<LogReport> logReport = logReportRepo.findById(logFollowUp.getLogReportId());

        // Update Status Tiket Tersebut //
        LogReport data = new LogReport();
        data.setLogReportId(logFollowUp.getLogReportId());
        data.setStatus(logFollowUp.getFlwUpStatus());
        data.setLogNumber(logReport.get().getLogNumber());
        data.setReportDate(logReport.get().getReportDate());
        data.setRecordDate(logReport.get().getRecordDate());
        data.setLogDesc(logReport.get().getLogDesc());
        data.setPasalUmumId(logReport.get().getPasalUmumId());
        data.setReportByUserId(logReport.get().getReportByUserId());
        data.setPicUserId(logReport.get().getPicUserId());
        data.setDueDatetime(logReport.get().getDueDatetime());
        data.setPriority(logReport.get().getPriority());
        data.setStatusRpt(logReport.get().getStatusRpt());
        data.setCompanyId(logReport.get().getCompanyId());

        updateStatusTiket(data);

        AppUser user = appUserService.findUserId(logFollowUp.getFlwUpByUserId());

        LogNotification logNotification = new LogNotification();

        logNotification.setReportId(logFollowUp.getLogReportId());
        logNotification.setUserId(logReport.get().getReportByUserId());
        logNotification.setLogNotification("Your Report has been follow up by :  " + user.getFullName());
        logNotificationService.create(logNotification);


        return logFollowUpRepo.save(logFollowUp);
    }

    public List<LogFollowUp> getFollowUp (LogFollowUp logFollowUp){
        return logFollowUpRepo.findByLogReportId(logFollowUp.getLogReportId());
    }


    public LogReport updateStatusTiket (LogReport logReport){
        return logReportRepo.save(logReport);
    }





}

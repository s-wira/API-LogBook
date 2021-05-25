package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.repository.LogReportTypeRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogReportService {

    @Autowired
    private LogReportRepo logReportRepo;

    public LogReport create(LogReport logReport){
        LocalDateTime localDateTime = LocalDateTime.now();
        logReport.setReportDate(localDateTime);
        logReport.setRecordDate(localDateTime);
        logReport.setDueDatetime(localDateTime);
        logReport.setLogReportId(GenerateOID.generateOID());
        return logReportRepo.save(logReport);
    }

    public Iterable<LogReport> findAll(){
        return logReportRepo.findAll();
    }


}

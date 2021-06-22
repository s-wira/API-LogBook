package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.*;
import com.dimata.logbookAPI.repository.HrCompanyRepo;
import com.dimata.logbookAPI.repository.LogCategoryRepo;
import com.dimata.logbookAPI.repository.LogPasalUmumRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogReportService {

    @Autowired
    private LogReportRepo logReportRepo;

    @Autowired
    private LogCategoryRepo logCategoryRepo;

    @Autowired
    private LogPasalUmumRepo logPasalUmumRepo;

    @Autowired
    private HrCompanyRepo hrCompanyRepo;

    public LogReport create(LogReport logReport){
        LocalDateTime localDateTime = LocalDateTime.now();
        logReport.setLogReportId(GenerateOID.generateOID());
        logReport.setReportDate(localDateTime);
        logReport.setRecordDate(localDateTime);
        logReport.setDueDatetime(localDateTime);
        logReport.setPasalKhususId(0L);
        logReport.setPriority(0);
        logReport.setStatus(0);
        logReport.setCustomerId(null);
        logReport.setRecordByUserId(0L);
        return logReportRepo.save(logReport);
    }

    public Iterable<LogReport> findAll(){
        return logReportRepo.findAll();
    }


//    public long countStatus(Integer id){
//        return logReportRepo.countByStatus(id);
//    }

    public List<LogReport> findAllStatus(Integer status, AppUser appUser){

        return logReportRepo.findByStatus(status);
    }


    public List<LogCategory> findLogCategory(Long rptType){
        return logCategoryRepo.findByRptTypeId(rptType);
    }

    public Iterable<LogPasalUmum> findAllLogPasal (){
        return logPasalUmumRepo.findAll();
    }

    public Long countStatusRptMyTiket (String status, LogReport logReport){
        return logReportRepo.countByStatusRptAndReportByUserId(status, logReport.getReportByUserId());
    }

    public Long countStatusRptMyCompany (String status, LogReport logReport){
        return logReportRepo.countByStatusRptAndCompanyId(status, logReport.getCompanyId());
    }


    public List<LogReport> findStatusRpt (String report){
        return logReportRepo.findByStatusRpt(report);
    }

    public LogReport findLogReportId (Long id){
        Optional<LogReport> logReport = logReportRepo.findById(id);
        if (!logReport.isPresent()){
            return null;
        }
        return logReport.get();
    }


    public LogReport findByLogReportNumber (Integer number){
        return logReportRepo.findByLogNumber(number);
    }


}

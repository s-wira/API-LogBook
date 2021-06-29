package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LogReportRepo extends CrudRepository<LogReport,Long> {
    List<LogReport>findByStatus(Integer status);

    long countByStatusRpt (String status);

    long countByStatusRptAndReportByUserId (String status, Long userId);

    long countByStatusRptAndCompanyId (String status, Long companyId);


    List<LogReport> findByStatusRpt (String statusRpt);

    LogReport findByLogNumber (Integer logNumber);

    List<LogReport> findByStatusAndReportByUserId (Integer status,Long reportByUserId);

    List<LogReport> findByStatusAndCompanyId (Integer status, Long companyId);

    List<LogReport> findByStatusRptAndReportByUserId (String status, Long userId);

    List<LogReport> findByStatusRptAndCompanyId (String status, Long companyId);

    long countByStatusAndStatusRpt(Integer status,String report);

}

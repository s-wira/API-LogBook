package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogReportRepo extends CrudRepository<LogReport,Long> {
    List<LogReport>findByStatus(Integer status);
    long countByStatusRpt (String status);
    List<LogReport> findByStatusRpt (String statusRpt);


}

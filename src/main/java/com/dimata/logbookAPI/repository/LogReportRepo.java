package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.LogReport;
import org.springframework.data.repository.CrudRepository;

public interface LogReportRepo extends CrudRepository<LogReport,Long> {
}

package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.LogReportType;
import org.springframework.data.repository.CrudRepository;

public interface LogReportTypeRepo extends CrudRepository<LogReportType, Long> {
    LogReportType findByTypeName (String typeName);

}

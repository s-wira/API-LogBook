package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.LogFollowUp;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogFollowUpRepo extends CrudRepository<LogFollowUp, Long> {
    List<LogFollowUp> findByLogReportId (Long logReportId);
}

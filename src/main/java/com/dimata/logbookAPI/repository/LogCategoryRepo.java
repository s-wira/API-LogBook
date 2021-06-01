package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.LogCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogCategoryRepo extends CrudRepository<LogCategory, Long> {

    List<LogCategory> findByRptTypeId(Long rptTypeId);

}

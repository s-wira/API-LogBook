package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.HrCompany;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HrCompanyRepo extends CrudRepository<HrCompany, Long> {
    Optional<HrCompany> findByCompanyCode (String companyCode);
}

package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogReportType;
import com.dimata.logbookAPI.repository.AppUserRepo;
import com.dimata.logbookAPI.repository.LogReportTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogReportTypeService {

    @Autowired
    private LogReportTypeRepo logReportTypeRepo;

    public Iterable<LogReportType> findAll(){
        return logReportTypeRepo.findAll();
    }

    public LogReportType getIdReportType(String typeName){
        return logReportTypeRepo.findByTypeName(typeName);
    }


}

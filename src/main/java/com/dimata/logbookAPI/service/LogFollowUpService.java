package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.LogFollowUp;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogFollowUpRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class LogFollowUpService {

    @Autowired
    private LogFollowUpRepo logFollowUpRepo;

    public LogFollowUp create(LogFollowUp logFollowUp){
        LocalDate localDate = LocalDate.now();
        logFollowUp.setFollowUpId(GenerateOID.generateOID());
        logFollowUp.setStartDateTime(localDate);
        logFollowUp.setEndDateTime(localDate);



        return logFollowUpRepo.save(logFollowUp);
    }



}

package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.model.LogNotification;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogNotificationRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import com.dimata.logbookAPI.utility.GenerateOID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class LogNotificationService {

    @Autowired
    private LogNotificationRepo logNotificationRepo;


    public LogNotification create(LogNotification logNotification){
        LocalDateTime localDateTime = LocalDateTime.now();
        logNotification.setNotificationId(GenerateOID.generateOID());
        logNotification.setUserId(logNotification.getUserId());
        logNotification.setLogNotification(logNotification.getLogNotification());
        logNotification.setDate(localDateTime);
        return logNotificationRepo.save(logNotification);
    }


}

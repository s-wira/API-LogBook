package com.dimata.logbookAPI.service;

import com.dimata.logbookAPI.repository.LogNotificationRepo;
import com.dimata.logbookAPI.repository.LogReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogNotificationService {

    @Autowired
    private LogNotificationRepo logNotificationRepo;





}

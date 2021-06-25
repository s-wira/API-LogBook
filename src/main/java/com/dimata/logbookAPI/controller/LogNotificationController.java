package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogNotification;
import com.dimata.logbookAPI.repository.LogNotificationRepo;
import com.dimata.logbookAPI.service.LogNotificationService;
import com.dimata.logbookAPI.service.LogReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/log-notification")
public class LogNotificationController {

    @Autowired
    private LogNotificationService logNotificationService;

    @Autowired
    private LogNotificationRepo logNotificationRepo;

    @PostMapping("/get")
    public ResponseEntity<ResponseDataList<LogNotification>> getNotification (@RequestBody LogNotification logNotification){
        ResponseDataList<LogNotification> responseDataList = new ResponseDataList<>();
        List<LogNotification> logNotifications = logNotificationRepo.findByUserId(logNotification.getUserId());
        if(logNotifications.isEmpty()){
            responseDataList.setStatus(false);
            responseDataList.setPayload(null);
            responseDataList.setMessage(Collections.singletonList("Belum Ada Notification Masuk!"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDataList);
        }
        responseDataList.setStatus(true);
        responseDataList.setMessage(Collections.singletonList("Data Notification Berhasil diambil"));
        responseDataList.setPayload(logNotifications);
        return ResponseEntity.ok(responseDataList);

    }


}

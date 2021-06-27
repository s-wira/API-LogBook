package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.model.LogReportDTO;
import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.dto.response.ResponseDataList;
import com.dimata.logbookAPI.model.AppUser;
import com.dimata.logbookAPI.model.LogFollowUp;
import com.dimata.logbookAPI.model.LogReport;
import com.dimata.logbookAPI.repository.LogFollowUpRepo;
import com.dimata.logbookAPI.service.LogFollowUpService;
import com.dimata.logbookAPI.service.LogReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/log-follow-up")
public class LogFollowUpController {

    @Autowired
    private LogFollowUpService logFollowUpService;


    @PostMapping("add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<LogFollowUp>> addFollowUp (@RequestBody LogFollowUp logFollowUp){
        ResponseData<LogFollowUp> responseData = new ResponseData<>();

        LogFollowUp data = new LogFollowUp();
        data.setFlwNote(logFollowUp.getFlwNote());
        data.setLogReportId(logFollowUp.getLogReportId());
        data.setFlwUpByUserId(logFollowUp.getFlwUpByUserId());
        data.setFlwUpStatus(logFollowUp.getFlwUpStatus());
        data.setChkByUserId(logFollowUp.getChkByUserId());
        data.setSubmitByUserId(logFollowUp.getFlwUpByUserId());

        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        responseData.setPayload(logFollowUpService.create(data));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("get")
    public ResponseEntity<ResponseDataList<LogFollowUp>> getFollowUp (@RequestBody LogFollowUp logFollowUp){
        ResponseDataList<LogFollowUp> responseData = new ResponseDataList<>();

        responseData.setStatus(true);
        responseData.setMessage(Collections.singletonList("Berhasil Mengambil Data"));
        responseData.setPayload(logFollowUpService.getFollowUp(logFollowUp));
        return ResponseEntity.ok(responseData);
    }


}

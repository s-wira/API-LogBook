package com.dimata.logbookAPI.dto.model;

import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class LogReportDTO {

    private Long logReportId;
    private Integer logNumber;
    private String logDesc;
    private LocalDateTime reportDate;
    private LocalDateTime recordDate;
    private Integer status;
    private Long rptTypeId;
    private Long rptCategoryId;
    private Long pasalKhususId;
    private Long reportByUserId;
    private Long recordByUserId;
    private Long picUserId;
    private Long logLocationId;
    private LocalDateTime dueDatetime;
    private LocalDateTime realFinishDatetime;
    private Long customerId;
    private Integer priority;
    private Integer information;
    private String statusRpt;





}

package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "log_report")
public class LogReport {

    @Id
    private Long logReportId;
    private Integer logNumber;
    private String logDesc;
    private LocalDateTime reportDate;
    private LocalDateTime recordDate;
    private Integer status;
    private Long rptTypeId;
    private Long rptCategoryId;
    private Long pasalUmumId;
    private Long pasalKhususId;
    private Long reportByUserId;
    private Long recordByUserId;
    private Long picUserId;
    private Long logLocationId;
    private LocalDateTime dueDatetime;
    private LocalDateTime realFinishDatetime;
    private Long customerId;
    private Integer priority;
    private String statusRpt;
    private Long companyId;


}

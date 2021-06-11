package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "log_report_type")
public class LogReportType {
    @Id
    private Long rptTypeId;
    private String typeName;
    private String statusRpt;

}

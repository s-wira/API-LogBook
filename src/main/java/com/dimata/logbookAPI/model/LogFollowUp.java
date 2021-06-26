package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log_follow_up")
public class LogFollowUp {
    @Id
    private Long followUpId;
    private LocalDate startDateTime;
    private String flwNote;
    private Long logReportId;
    private Long flwUpByUserId;
    private Integer flwUpStatus;
    private LocalDate endDateTime;
    private Long chkByUserId;
    private Long submitByUserId;

}

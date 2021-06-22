package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log_notification")
public class LogNotification {

    @Id
    private Long notificationId;
    private Long reportId;
    private Long userId;
    private LocalDateTime date;
    private String logNotification;
    private Integer statusNotification;

}

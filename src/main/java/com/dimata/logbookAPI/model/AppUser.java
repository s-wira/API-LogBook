package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long userId;
    private String loginId;
    private String Password;
    private String fullName;
    private String Email;
    private String Description;
    private Timestamp regDate;
    private LocalDateTime updateDate;
    private Integer userStatus;
    private LocalDateTime lastLoginDate;
    private String lastLoginIp;
    private Integer userType;
    private Long employeeId;

}

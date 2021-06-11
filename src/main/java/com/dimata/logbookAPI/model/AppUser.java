package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log_user")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long userId;
    @NotEmpty(message = "Username Harus diisi")
    private String loginId;
    @NotEmpty(message = "Password Harus diisi")
    private String password;
    @NotEmpty(message = "Fullname Harus diisi")
    private String fullName;
    @NotEmpty(message = "Email Harus diisi")
    private String Email;
    private String description;
    private Timestamp regDate;
    private LocalDateTime updateDate;
    private Integer userStatus;
    private LocalDateTime lastLoginDate;
    private String lastLoginIp;
//    private Integer userType;
    private Long employeeId;

}

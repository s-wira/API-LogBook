package com.dimata.logbookAPI.dto.model;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class AppUserDTO {

    private Long userId;
    @NotEmpty(message = "Username Harus diisi")
    private String loginId;
    @NotEmpty(message = "Password Harus diisi")
    private String Password;
    @NotEmpty(message = "Fullname Harus diisi")
    private String fullName;
    @NotEmpty(message = "Email Harus diisi")
    @javax.validation.constraints.Email(message = "Input Email Dengan Benar")
    private String Email;
//    private String Description;
    private Timestamp regDate;
//    private LocalDateTime updateDate;
    private Integer userStatus;
//    private LocalDateTime lastLoginDate;
//    private String lastLoginIp;
    private Integer userType;
    private Long employeeId;
    private String companyCode;
    private Long companyId;

}

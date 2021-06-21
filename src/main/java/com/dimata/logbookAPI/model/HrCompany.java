package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "hr_company")
public class HrCompany {

    @Id
    private Long companyId;
    private String company;
    private String description;
    private String companyCode;

}

package com.dimata.logbookAPI.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "log_category")
public class LogCategory {
// ini tutorial belajar 
    @Id
    private Long rptCategoryId;
    private String categoryName;
    private Long rptTypeId;

}

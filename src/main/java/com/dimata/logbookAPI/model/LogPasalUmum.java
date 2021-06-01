package com.dimata.logbookAPI.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "log_pasal_umum")
public class LogPasalUmum {

    @Id
    private Long pasalUmumId;
    private String pasalUmum;

}

package com.dimata.logbookAPI.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDataList <T> {
    private boolean status;
    private List<String> message = new ArrayList<>();
    private List<T> payload = new ArrayList<>();
}

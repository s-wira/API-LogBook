package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.LogNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogNotificationRepo extends CrudRepository<LogNotification, Long> {
    List<LogNotification> findByUserId (Long userId);
}

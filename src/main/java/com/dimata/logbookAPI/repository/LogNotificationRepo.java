package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.LogNotification;
import org.springframework.data.repository.CrudRepository;

public interface LogNotificationRepo extends CrudRepository<LogNotification, Long> {

}

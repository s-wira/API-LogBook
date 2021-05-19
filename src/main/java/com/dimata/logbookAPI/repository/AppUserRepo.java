package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
}

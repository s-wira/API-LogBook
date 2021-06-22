package com.dimata.logbookAPI.repository;

import com.dimata.logbookAPI.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    List<AppUser> findByLoginId (String loginId);
//    List<AppUser> findByLoginIdAndPassword (String loginId, String password);
    AppUser findByLoginIdAndPassword (String loginId, String password);

    List<AppUser> findByEmployeeId (Long employeeid);

}

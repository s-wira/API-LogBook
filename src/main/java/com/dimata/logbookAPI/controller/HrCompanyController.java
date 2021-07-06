package com.dimata.logbookAPI.controller;

import com.dimata.logbookAPI.dto.response.ResponseData;
import com.dimata.logbookAPI.model.HrCompany;
import com.dimata.logbookAPI.repository.HrCompanyRepo;
import com.dimata.logbookAPI.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/super-admin/company")
public class HrCompanyController {
    @Autowired
    private HrCompanyService hrCompanyService;

    @Autowired
    private HrCompanyRepo hrCompanyRepo;

    @PostMapping("add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseData<HrCompany>> createCompany (@RequestBody HrCompany hrCompany){
        ResponseData<HrCompany> responseData = new ResponseData<>();

        Optional<HrCompany> data = hrCompanyRepo.findByCompany(hrCompany.getCompany());
        if (data.isPresent()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Company yang Anda Masukan Sudah Terdaftar"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(hrCompanyService.create(hrCompany));
        responseData.setMessage(Collections.singletonList("Berhasil Menyimpan Data"));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseData<HrCompany>> deleteCompany (@RequestBody HrCompany hrCompany){
        ResponseData<HrCompany> responseData = new ResponseData<>();

        Optional<HrCompany> data = hrCompanyRepo.findById(hrCompany.getCompanyId());
        if (!data.isPresent()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Company yang Anda Delete Tidak Terdaftar"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        hrCompanyService.delete(hrCompany.getCompanyId());
        responseData.setMessage(Collections.singletonList("Berhasil Menghapus Data"));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("update")
    public ResponseEntity<ResponseData<HrCompany>> updateCompany (@RequestBody HrCompany hrCompany){
        ResponseData<HrCompany> responseData = new ResponseData<>();

        Optional<HrCompany> dataNama = hrCompanyRepo.findByCompany(hrCompany.getCompany());

        if (dataNama.isPresent()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Company yang Anda Masukan Tidak Terdaftar"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Optional<HrCompany> data = hrCompanyRepo.findById(hrCompany.getCompanyId());
        HrCompany hrCompany1 = new HrCompany();
        hrCompany1.setCompanyId(data.get().getCompanyId());
        hrCompany1.setCompanyCode(data.get().getCompanyCode());
        hrCompany1.setCompany(hrCompany.getCompany());
        hrCompany1.setDescription(hrCompany.getDescription());


        responseData.setStatus(true);
        responseData.setPayload(hrCompanyService.update(hrCompany1));
        responseData.setMessage(Collections.singletonList("Berhasil Mengubah Data"));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("detail")
    public ResponseEntity<ResponseData<HrCompany>> detailCompany (@RequestBody HrCompany hrCompany){
        ResponseData<HrCompany> responseData = new ResponseData<>();

        Optional<HrCompany> data = hrCompanyRepo.findById(hrCompany.getCompanyId());

        if (!data.isPresent()){
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage(Collections.singletonList("Company yang Anda Masukan Tidak Terdaftar"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        HrCompany hrCompany1 = new HrCompany();
        hrCompany1.setCompany(data.get().getCompany());
        hrCompany1.setCompanyId(data.get().getCompanyId());
        hrCompany1.setDescription(data.get().getDescription());
        hrCompany1.setCompanyCode(data.get().getCompanyCode());

        responseData.setStatus(true);
        responseData.setPayload(hrCompany1);
        responseData.setMessage(Collections.singletonList("Berhasil Mengambil Data"));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("findAll")
    public Iterable<HrCompany> findAll(){
        return hrCompanyService.findAll();
    }

    @PostMapping("detail/id")
    public Optional<HrCompany> detailCompany2 (@RequestBody HrCompany hrCompany){
        return hrCompanyService.detail(hrCompany.getCompanyId());

    }

}

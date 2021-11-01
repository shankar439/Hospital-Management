package com.HMPackage.controller;

import java.util.Optional;
import com.HMPackage.baseResponse.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HMPackage.DTO.PatientDTO;
import com.HMPackage.baseResponse.BaseResponse;
import com.HMPackage.entity.Patient;
import com.HMPackage.service.PatientServiceInterface;
import javax.annotation.security.RolesAllowed;

@RequestMapping("/patient")
@RestController
public class PatientController {
    @Autowired
    private PatientServiceInterface patientServiceInterface;

    @PostMapping("/create")
    @RolesAllowed(value = "USER")
    public BaseResponse addPatient(@RequestBody PatientDTO patientDTO) {
    	BaseResponse<Patient> baseResponse = null;
        baseResponse = BaseResponse.<Patient>builder().Data(patientServiceInterface.addPatient(patientDTO)).build();
    	return baseResponse;
    }
 
    @GetMapping("/get/{patientId}")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<Patient>> findByPatientId(@PathVariable Long patientId){
    	BaseResponse<Optional<Patient>> baseResponse = null;
    	baseResponse = BaseResponse.<Optional<Patient>>builder().Data(patientServiceInterface.findByPatientId(patientId)).build();
    	return baseResponse;
    }

    @PutMapping("/update")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<Patient>> updatePatient(@RequestBody PatientDTO patientDTO){
    	BaseResponse<Optional<Patient>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Patient>>builder().Data(patientServiceInterface.UpdatePatientById(patientDTO)).build();
    	return baseResponse;
    }

    @PutMapping("/softdelete")
    @RolesAllowed(value = "ADMIN")
    public BaseResponse<Optional<Patient>>  deleteSoftPatient(@RequestBody PatientDTO patientDTO){
    	BaseResponse<Optional<Patient>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Patient>>builder().Data(patientServiceInterface.DeleteSoftPatient(patientDTO)).build();
    	return baseResponse;
    }

    @GetMapping("/pagination/{pageno}/{pageSize}/{patientName}")
    @RolesAllowed(value = "ADMIN")
    private PageResponse<Patient> pageByPatientName(@PathVariable int pageno, @PathVariable int pageSize, @PathVariable String patientName){
        return patientServiceInterface.pageByPatientName(pageno, pageSize, patientName);
    }
}

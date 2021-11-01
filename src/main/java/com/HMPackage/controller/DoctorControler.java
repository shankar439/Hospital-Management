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
import com.HMPackage.DTO.DoctorDTO;
import com.HMPackage.baseResponse.BaseResponse;
import com.HMPackage.entity.Doctor;
import com.HMPackage.service.DoctorServiceInterface;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/doctor")
public class DoctorControler {
	@Autowired
	private DoctorServiceInterface doctorServiceInterface;

    @PostMapping("/create")
    @RolesAllowed(value = "USER")
    public BaseResponse AddDoctor(@RequestBody DoctorDTO doctorDTO) {
    	BaseResponse<Doctor> baseResponse = null;
        baseResponse = BaseResponse.<Doctor>builder().Data(doctorServiceInterface.AddDoctor(doctorDTO)).build();
    	return baseResponse;
    }

    @GetMapping("/get/{doctorId}")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<Doctor>> findByDoctorId(@PathVariable Long doctorId){
    	BaseResponse<Optional<Doctor>> baseResponse = null;
    	baseResponse = BaseResponse.<Optional<Doctor>>builder().Data(doctorServiceInterface.findByDoctorId(doctorId)).build();
        return baseResponse;
    }

    @PutMapping("/update")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<Doctor>> updateDoctor(@RequestBody DoctorDTO doctorDTO){
    	BaseResponse<Optional<Doctor>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Doctor>>builder().Data(doctorServiceInterface.UpdateDoctorById(doctorDTO)).build();
    	return baseResponse;
    }

    @PutMapping("/softdelete")
    @RolesAllowed(value = "ADMIN")
    public BaseResponse<Optional<Doctor>>  deleteSoft(@RequestBody DoctorDTO doctorDTO){
    	BaseResponse<Optional<Doctor>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Doctor>>builder().Data(doctorServiceInterface.DeleteSoftDoctor(doctorDTO)).build();
    	return baseResponse;
    }

    @GetMapping("/pagination/{pageno}/{pageSize}/{doctorName}")
    @RolesAllowed(value = "ADMIN")
    private PageResponse<Doctor> pageByDoctorName(@PathVariable int pageno, @PathVariable int pageSize, @PathVariable String doctorName){
        return doctorServiceInterface.pageByDoctorName(pageno, pageSize, doctorName);
    }
}

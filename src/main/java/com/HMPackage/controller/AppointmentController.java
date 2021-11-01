package com.HMPackage.controller;

import java.util.List;
import java.util.Optional;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HMPackage.DTO.AppointmentDTO;
import com.HMPackage.baseResponse.BaseResponse;
import com.HMPackage.entity.Appointment;
import com.HMPackage.service.AppointmentServiceInterface;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentServiceInterface appointmentServiceInterface;

    @PostMapping("/create")
    @RolesAllowed(value = "USER")
    public BaseResponse addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
    	BaseResponse<Appointment> baseResponse = null;
        baseResponse = BaseResponse.<Appointment>builder().Data(appointmentServiceInterface.AddAppointment(appointmentDTO)).build();
    	return baseResponse;
    }

    @GetMapping("/getAll")
    @RolesAllowed(value = "ADMIN")
    public BaseResponse<List<Appointment>> list() {
    	BaseResponse<List<Appointment>> baseResponse = null;
    	baseResponse = BaseResponse.<List<Appointment>>builder().Data(appointmentServiceInterface.ListAllAppointment()).build();
    	return baseResponse;
    }

    @PutMapping("/update")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<Appointment>> update(@RequestBody AppointmentDTO appointmentDTO){
    	BaseResponse<Optional<Appointment>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Appointment>>builder().Data(appointmentServiceInterface.UpdateAppointment(appointmentDTO)).build();
        return baseResponse;
    }

    @PutMapping("/softdelete")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<Appointment>> deleteSoftAppointment(@RequestBody AppointmentDTO appointmentDTO){
    	BaseResponse<Optional<Appointment>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Appointment>>builder().Data(appointmentServiceInterface.DeleteSoftAppointment(appointmentDTO)).build();
    	return baseResponse;
    }
}

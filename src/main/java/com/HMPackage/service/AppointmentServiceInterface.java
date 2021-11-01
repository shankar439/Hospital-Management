package com.HMPackage.service;

import java.util.List;
import java.util.Optional;
import com.HMPackage.DTO.AppointmentDTO;
import com.HMPackage.entity.Appointment;

public interface AppointmentServiceInterface {

	Appointment AddAppointment(AppointmentDTO appointmentDTO);
	List<Appointment> ListAllAppointment();
	Optional<Appointment> DeleteSoftAppointment(AppointmentDTO appointmentDTO);
	Optional<Appointment> UpdateAppointment(AppointmentDTO appointmentDTO);

}

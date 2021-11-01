package com.HMPackage.serviceImplementation;

import java.util.List;
import java.util.Optional;

import com.HMPackage.exception.BadRequestEx;
import com.HMPackage.exception.NotFoundEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HMPackage.DTO.AppointmentDTO;
import com.HMPackage.entity.Appointment;
import com.HMPackage.entity.Disease;
import com.HMPackage.entity.Doctor;
import com.HMPackage.entity.Patient;
import com.HMPackage.repository.AppointmentRepository;
import com.HMPackage.repository.DiseaseRepository;
import com.HMPackage.repository.DoctorRepository;
import com.HMPackage.repository.PatientRepository;
import com.HMPackage.service.AppointmentServiceInterface;

@Service
public class AppointmentServiceImpl implements AppointmentServiceInterface{
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	DiseaseRepository diseaseRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	DoctorRepository doctorRepository;

    @Override
    public Appointment AddAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentName(appointmentDTO.getAppointmentName());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        Appointment patientAppointment = appointment;
        appointmentDTO.getPatientId().forEach(patientDTO -> {
            Optional<Patient> patient=patientRepository.findByPatientId(patientDTO.getPatientId());
            if(patient.isPresent())
            {
                patientAppointment.setPatient(patient.get());
            }
            else
            {
                throw new BadRequestEx();
            }
        });
        Appointment doctorAppointment = appointment;
        appointmentDTO.getDoctorId().forEach(doctorDTO -> {
            Optional<Doctor> doctor=doctorRepository.findByDoctorId(doctorDTO.getDoctorId());
            if (doctor.isPresent())
            {
                doctorAppointment.setDoctor(doctor.get());
            }
            else
            {
                throw new BadRequestEx();
            }
        });
        Appointment diseaseAppointment = appointment;
        appointmentDTO.getDiseaseId().forEach(diseaseDTO -> {
            Optional<Disease> disease=diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
            if (disease.isPresent())
            {
                diseaseAppointment.setDisease(disease.get());
            }
            else
            { 
                throw new BadRequestEx();
            }
        });
        appointment= appointmentRepository.save(appointment);
		return appointment;
    }

	@Override
    public List<Appointment> ListAllAppointment() {
		return appointmentRepository.findAll();

    }
	
    @Override
    public Optional<Appointment> UpdateAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> appointment = appointmentRepository.findByAppointmentId(appointmentDTO.getAppointmentId());
        if (appointment.isPresent())
        {
            appointment.get().setAppointmentName(appointmentDTO.getAppointmentName());
            appointment.get().setAppointmentTime(appointmentDTO.getAppointmentTime());
        }
        else
        {
            throw new BadRequestEx();
        }
        appointmentDTO.getPatientId().forEach(patientDTO -> {
            Optional<Patient> patientId=patientRepository.findByPatientId(patientDTO.getPatientId());
            if (patientId.isPresent())
            {
                appointment.get().setPatient(patientId.get());
            }
            else
            {
                throw new BadRequestEx();
            }
        });

        appointmentDTO.getDoctorId().forEach(doctorDTO -> {
            Optional<Doctor> doctorId=doctorRepository.findByDoctorId(doctorDTO.getDoctorId());
            if (doctorId.isPresent())
            {
                appointment.get().setDoctor(doctorId.get());
            }
           else
            {
                throw new BadRequestEx();
            }
        });

        appointmentDTO.getDiseaseId().forEach(diseaseDTO -> {
            Optional<Disease> diseaseId=diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
            if (diseaseId.isPresent())
            {
                appointment.get().setDisease(diseaseId.get());
            }
            else
            {
                throw new BadRequestEx();
            }
        });
      appointmentRepository.save(appointment.get());
		return appointment;
    }

	@Override
    public Optional<Appointment> DeleteSoftAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> appointment = appointmentRepository.findByAppointmentId(appointmentDTO.getAppointmentId());
        if (appointment.isPresent()&&appointment.get().getIsDelete()==0)
        {
            appointment.get().setIsDelete(1);
        }
        else
        {
            throw new NotFoundEx();
        }
        appointmentRepository.save(appointment.get());
		return appointment;
    }
}

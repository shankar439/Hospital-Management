package com.HMPackage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HMPackage.entity.Appointment;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	Optional<Appointment> findByAppointmentId(Long appointmentId);
}
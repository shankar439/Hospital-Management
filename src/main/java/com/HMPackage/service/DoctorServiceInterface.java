package com.HMPackage.service;

import java.util.Optional;
import com.HMPackage.DTO.DoctorDTO;
import com.HMPackage.baseResponse.PageResponse;
import com.HMPackage.entity.Doctor;

public interface DoctorServiceInterface {

	Doctor AddDoctor(DoctorDTO doctorDTO);
	Optional<Doctor> findByDoctorId(Long doctorId);
	Optional<Doctor> UpdateDoctorById(DoctorDTO doctorDTO);
	Optional<Doctor> DeleteSoftDoctor(DoctorDTO doctorDTO);
	PageResponse<Doctor> pageByDoctorName(int offset, int pageSize, String doctorName);
}

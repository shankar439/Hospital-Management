package com.HMPackage.service;

import java.util.Optional;

import com.HMPackage.DTO.PatientDTO;
import com.HMPackage.baseResponse.PageResponse;
import com.HMPackage.entity.Patient;

public interface PatientServiceInterface {

	Patient addPatient(PatientDTO patientDTO);
	Optional<Patient> findByPatientId(Long id);
	Optional<Patient> UpdatePatientById(PatientDTO patientDTO);
	Optional<Patient> DeleteSoftPatient(PatientDTO patientDTO);
	PageResponse<Patient> pageByPatientName(int offset, int pageSize, String patientName);
}

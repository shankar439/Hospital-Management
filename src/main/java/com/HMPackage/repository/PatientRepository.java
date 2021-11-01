package com.HMPackage.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HMPackage.entity.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient> findByPatientId(Long patientId);
	Page<Patient> searchAllByPatientNameLike(String s, Pageable paging);
}

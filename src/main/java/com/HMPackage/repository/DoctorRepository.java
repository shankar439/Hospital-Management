package com.HMPackage.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HMPackage.entity.Doctor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	Optional<Doctor> findByDoctorId(Long doctorId);
	Page<Doctor> searchAllByDoctorNameLike(String s, Pageable paging);
}

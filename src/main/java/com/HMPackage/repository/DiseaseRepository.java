package com.HMPackage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HMPackage.entity.Disease;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>{
	Optional<Disease> findByDiseaseId(Long diseaseId);
}

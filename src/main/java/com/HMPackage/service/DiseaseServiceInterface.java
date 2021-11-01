package com.HMPackage.service;

import java.util.List;
import java.util.Optional;
import com.HMPackage.DTO.DiseaseDTO;
import com.HMPackage.entity.Disease;

public interface DiseaseServiceInterface {

	Disease AddDisease(DiseaseDTO diseaseDTO);
	List<Disease> listAlldisease();
	Optional<Disease> UpdateDiseaseById(DiseaseDTO diseaseDTO);
	Optional<Disease> DeleteSoftDisease(DiseaseDTO diseaseDTO);
}

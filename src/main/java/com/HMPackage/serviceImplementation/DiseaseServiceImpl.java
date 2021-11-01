package com.HMPackage.serviceImplementation;

import java.util.List;
import java.util.Optional;

import com.HMPackage.exception.BadRequestEx;
import com.HMPackage.exception.NotFoundEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HMPackage.DTO.DiseaseDTO;
import com.HMPackage.entity.Disease;
import com.HMPackage.repository.DiseaseRepository;
import com.HMPackage.service.DiseaseServiceInterface;

@Service
public class DiseaseServiceImpl implements DiseaseServiceInterface{
	@Autowired
	private DiseaseRepository diseaseRepository;

    @Override
    public Disease AddDisease(DiseaseDTO diseaseDTO) {
        Disease disease = new Disease();
        disease.setDiseaseId(diseaseDTO.getDiseaseId());
        disease.setDiseaseName(diseaseDTO.getDiseaseName());
        diseaseRepository.save(disease);


		return disease;
    }

	@Override
    public List<Disease> listAlldisease() {
		return diseaseRepository.findAll();

    }

	@Override
    public Optional<Disease> UpdateDiseaseById(DiseaseDTO diseaseDTO) {
        Optional<Disease> disease = diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
        if (disease.isPresent())
        {
            disease.get().setDiseaseName(diseaseDTO.getDiseaseName());
        }
        else
        {
            throw new BadRequestEx();
        }
        diseaseRepository.save(disease.get());
		return disease;
    }

	@Override
    public Optional<Disease> DeleteSoftDisease(DiseaseDTO diseaseDTO) {
        Optional<Disease> disease = diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
        if (disease.isPresent()&&disease.get().getIsDelete()==0)
        {
            disease.get().setIsDelete(1);
        }
        else
        {
            throw new NotFoundEx();
        }
        diseaseRepository.save(disease.get());
		return disease;
    }
}

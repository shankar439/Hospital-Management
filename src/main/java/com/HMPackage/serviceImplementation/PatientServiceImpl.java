package com.HMPackage.serviceImplementation;

import java.util.Optional;
import com.HMPackage.baseResponse.PageResponse;
import com.HMPackage.exception.BadRequestEx;
import com.HMPackage.exception.NotFoundEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.HMPackage.DTO.PatientDTO;
import com.HMPackage.entity.Patient;
import com.HMPackage.entity.User;
import com.HMPackage.repository.PatientRepository;
import com.HMPackage.repository.UserRepository;
import com.HMPackage.service.PatientServiceInterface;

@Service
public class PatientServiceImpl implements PatientServiceInterface{
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private UserRepository userRepository;

@Override
    public Patient addPatient(PatientDTO patientDTO) {
    Patient patient = new Patient();
    patient.setPatientName(patientDTO.getPatientName());
    patient.setContactNumber(patientDTO.getContactNumber());
    patientDTO.getId().forEach(userDTO -> {
        Optional<User> user=userRepository.findById(userDTO.getId());
        if(user.isPresent())
        {
            patient.setUser(user.get());

        }
        else
        {
            throw new NotFoundEx();
        }
    });
    patient.setPatientId(patientDTO.getPatientId());
    patient.setPatientName(patientDTO.getPatientName());
    patient.setContactNumber(patientDTO.getContactNumber());
    patientRepository.save(patient);
    return patient;
}

@Override
public Optional<Patient> findByPatientId(Long patientId) {
    Optional<Patient> patient=patientRepository.findByPatientId(patientId);
    if(patient.isPresent()){
        if(patient.get().getIsDelete()==0){
            return patient;
        }
        else{
            throw new NotFoundEx();
        }
    }
    else {
        throw new NotFoundEx();
    }
}


@Override
public Optional<Patient>  UpdatePatientById(PatientDTO patientDTO) {
    Optional<Patient> patient = patientRepository.findByPatientId(patientDTO.getPatientId());
    if (patient.isPresent())
    {
        patient.get().setPatientName(patientDTO.getPatientName());
        patient.get().setContactNumber(patientDTO.getContactNumber());
        
    }
    else 
    {
        throw new BadRequestEx();
    }
    patientDTO.getId().forEach(userDTO -> {
        Optional<User> userId=userRepository.findById(userDTO.getId());
        if (userId.isPresent())
        {
            patient.get().setUser(userId.get());
        }
        else
        {
            throw new BadRequestEx();
        }

    });
    patientRepository.save(patient.get());
    return patient;
}

@Override
public Optional<Patient> DeleteSoftPatient(PatientDTO patientDTO) {
    Optional<Patient> patient = patientRepository.findByPatientId(patientDTO.getPatientId());
    if(patient.isPresent()&&patient.get().getIsDelete()==0)
    {
        patient.get().setIsDelete(1);
        patientRepository.save(patient.get());
    }
    else
    {
        throw new NotFoundEx();
    }
    return patient;
}
    @Override
    public PageResponse<Patient> pageByPatientName(int pageno, int pageSize, String patientName) {
        Pageable paging= PageRequest.of(pageno,pageSize);
        Page<Patient> patients = patientRepository.searchAllByPatientNameLike("%" + patientName + "%", paging);
        PageResponse PageResponse=new PageResponse();
        PageResponse.setResponse(patients);
        PageResponse.setPages(patients.getTotalPages());
        return PageResponse;
    }
}

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
import com.HMPackage.DTO.DoctorDTO;
import com.HMPackage.entity.Doctor;
import com.HMPackage.entity.User;
import com.HMPackage.repository.DoctorRepository;
import com.HMPackage.repository.UserRepository;
import com.HMPackage.service.DoctorServiceInterface;

@Service
public class DoctorServiceImpl implements DoctorServiceInterface{
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Doctor AddDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctorDTO.getId().forEach(userDTO -> {
            Optional<User> user=userRepository.findById(userDTO.getId());
            if (user.isPresent())
            {
                doctor.setUser(user.get());
            }
            else
            {
                throw new BadRequestEx();
            }
        });
        doctor.setDoctorId(doctorDTO.getDoctorId());
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctorRepository.save(doctor);
        return doctor;
    }

	@Override
    public Optional<Doctor> findByDoctorId(Long doctorId) {
        Optional<Doctor> doctor=doctorRepository.findByDoctorId(doctorId);
        if(doctor.isPresent()){
            if(doctor.get().getIsDelete()==0){
                return doctor;
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
    public Optional<Doctor>UpdateDoctorById(DoctorDTO doctorDTO) {
        Optional<Doctor> doctor = doctorRepository.findByDoctorId(doctorDTO.getDoctorId());
        if (doctor.isPresent())
        {
            doctor.get().setDoctorName(doctorDTO.getDoctorName());
        }
        else
        {
            throw new BadRequestEx();
        }
        doctorDTO.getId().forEach(userDTO -> {
            Optional<User> user=userRepository.findById(userDTO.getId());
            if (user.isPresent())
            {
                doctor.get().setUser(user.get());
            }
            else
            {
                throw new BadRequestEx();
            }

        });
        doctorRepository.save(doctor.get());
        return doctor;
    }

	@Override
    public Optional<Doctor> DeleteSoftDoctor(DoctorDTO doctorDTO) {
        Optional<Doctor> doctor = doctorRepository.findByDoctorId(doctorDTO.getDoctorId());
        if (doctor.isPresent()&&doctor.get().getIsDelete()==0)
        {
            doctor.get().setIsDelete(1);
            doctorRepository.save(doctor.get());
        }
        else
        {
            throw new NotFoundEx();
        }
        return doctor;
    }

    @Override
    public PageResponse<Doctor> pageByDoctorName(int pageno, int pageSize, String doctorName) {
        Pageable paging= PageRequest.of(pageno,pageSize);
        Page<Doctor> doctor = doctorRepository.searchAllByDoctorNameLike("%" + doctorName + "%", paging);
        PageResponse pageResponse=new PageResponse();
        pageResponse.setResponse(doctor);
        pageResponse.setPages(doctor.getTotalPages());
        return pageResponse;
    }
}

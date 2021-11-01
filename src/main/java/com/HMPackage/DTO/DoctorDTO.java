package com.HMPackage.DTO;

import lombok.Data;

import java.util.List;
@Data
public class DoctorDTO {
	private long doctorId;
	private String doctorName;
	private int isActive;
	private int isDelete;
	private List<UserDTO> id;

}

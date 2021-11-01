package com.HMPackage.DTO;

import lombok.Data;

import java.util.List;
@Data
public class PatientDTO {
	private Long patientId;
	private String patientName;
	private Long contactNumber;
	private int isActive;
	private int isDelete;
	private List<UserDTO> id;
}

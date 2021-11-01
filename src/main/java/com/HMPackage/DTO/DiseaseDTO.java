package com.HMPackage.DTO;

import lombok.Data;

@Data
public class DiseaseDTO {
	private Long diseaseId;
	private String diseaseName;
	private int isActive;
	private int isDelete;
}

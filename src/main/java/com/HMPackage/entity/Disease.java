package com.HMPackage.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="disease_id")
	private Long diseaseId;

	@NotNull
	@Size(min=3,message = "DiseaseName Should Contain At lest 3 Character")
	@Column(name="disease_name")
	private String diseaseName;

	@CreationTimestamp
	@Column(name = "disease_created_time")
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	@Column(name = "disease_updated_time")
	private LocalDateTime upDateTime;

    @Column(name="is_active_disease",columnDefinition = "integer default 0")
    private int isActive;

	@Column(name = "is_deleted_disease",columnDefinition = "integer default 0")
	private int isDelete;
}

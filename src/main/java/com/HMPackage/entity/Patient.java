package com.HMPackage.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patient_id")
	private Long patientId;

	@NotNull
	@Size(min=3,message = "PatientName Should Contain At lest 3 Character")
	@Column(name="patient_name")
	private String patientName;

	@Column(name="contactnumber")
	private Long contactNumber;

	@CreationTimestamp
	@Column(name = "patient_created_time")
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	@Column(name = "patient_updated_time")
	private LocalDateTime upDateTime;

    @Column(name="is_active_patient",columnDefinition = "integer default 0")
    private int isActive;

	 @Column(name = "is_deleted_patient",columnDefinition = "integer default 0")
	 private int isDelete;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_UserId")
	private User user;
}

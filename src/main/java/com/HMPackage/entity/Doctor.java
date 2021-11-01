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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor_appointment")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doctor_id")
	private Long doctorId;

	@NotNull
	@Size(min=3,message = "DoctorName Should Contain At lest 3 Character")
	@Column(name="Doctor_name")
	private String doctorName;

	@CreationTimestamp
	@Column(name = "doctor_created_time")
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	@Column(name = "doctor_updated_time")
	private LocalDateTime upDateTime;

    @Column(name="is_active_doctor",columnDefinition = "integer default 0")
    private int isActive;

	@Column(name = "is_deleted_doctor",columnDefinition = "integer default 0")
	private int isDelete;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_UserId_Doctor")
	private User user;

}

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentId;

	@NotNull
	@Size(min=4,message = "AppointmentType Should Contain At lest 4 Character")
	@Column(name = "appointment_type")
	private String appointmentName;

	@NotNull
	@Column(name = "appointment_time")
	private Long appointmentTime;

	@CreationTimestamp
	@Column(name = "appointment_created_time")
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	@Column(name = "appointment_updated_time")
	private LocalDateTime upDateTime;

    @Column(name="is_active_appointment",columnDefinition = "integer default 0")
    private int isActive;

	@Column(name = "is_deleted_appointment",columnDefinition = "integer default 0")
	private int isDelete;
	 
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "FK_PatientId")
		private Patient patient;

		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "FK_DoctorId")
		private Doctor doctor;

		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "FK_DiseaseId")
		private Disease disease;
}

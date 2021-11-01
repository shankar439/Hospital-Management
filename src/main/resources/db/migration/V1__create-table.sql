CREATE TABLE user (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  user_created_time datetime(6) DEFAULT NULL,
  user_updated_time datetime(6) DEFAULT NULL,
  is_active_user int DEFAULT 0,
  is_deleted_user int DEFAULT 0,
  PRIMARY KEY (id)
);
CREATE TABLE patient (
  patient_id bigint NOT NULL AUTO_INCREMENT,
  patient_name varchar(100) NOT NULL,
  contactnumber bigint DEFAULT NULL,
  patient_created_time datetime(6) DEFAULT NULL,
  patient_updated_time datetime(6) DEFAULT NULL,
  is_active_patient int DEFAULT 0,
  is_deleted_patient int DEFAULT 0,
  fk_user_id bigint,
  FOREIGN KEY (fk_user_id) REFERENCES user (id),
  PRIMARY KEY (patient_id)
);
CREATE TABLE doctor_appointment (
  doctor_id bigint NOT NULL AUTO_INCREMENT,
  doctor_name varchar(100) NOT NULL,
  doctor_created_time datetime(6) DEFAULT NULL,
  doctor_updated_time datetime(6) DEFAULT NULL,
  is_active_doctor int DEFAULT 0,
  is_deleted_doctor int DEFAULT 0,
  fk_user_id bigint,
  FOREIGN KEY (fk_user_id) REFERENCES user (id),
  PRIMARY KEY (doctor_id)
);
CREATE TABLE disease (
  disease_id bigint NOT NULL AUTO_INCREMENT,
  disease_created_time datetime(6) DEFAULT NULL,
  disease_name varchar(100) NOT NULL,
  disease_updated_time datetime(6) DEFAULT NULL,
  is_active_disease int DEFAULT 0,
  is_deleted_disease int DEFAULT 0,
  PRIMARY KEY (disease_id)
);
CREATE TABLE appointment (
  appointment_id bigint NOT NULL AUTO_INCREMENT,
  appointment_type varchar(255) NOT NULL,
  appointment_time bigint NOT NULL,
  appointment_created_time datetime(6) DEFAULT NULL,
  appointment_updated_time datetime(6) DEFAULT NULL,
  is_active_appointment int DEFAULT 0,
  is_deleted_appointment int DEFAULT 0,
  fk_patient_id bigint,
  fk_doctor_id bigint,
  fk_disease_id bigint,
  FOREIGN KEY (fk_patient_id) REFERENCES patient (patient_id),
  FOREIGN KEY (fk_disease_id) REFERENCES disease (disease_id),
  FOREIGN KEY (fk_doctor_id) REFERENCES doctor_appointment (doctor_id),
  PRIMARY KEY (appointment_id)
);
CREATE TABLE role (
  role_id bigint NOT NULL AUTO_INCREMENT,
  role_name varchar(100) NOT NULL,
  is_active_user int DEFAULT 0,
  is_deleted_user int DEFAULT 0,
  PRIMARY KEY (role_id)
);
CREATE TABLE user_role (
  user_role_id bigint NOT NULL AUTO_INCREMENT,
  role_id_fk bigint NOT NULL,
  id_fk bigint NOT NULL,
  FOREIGN KEY (id_fk) REFERENCES user (id),
  FOREIGN KEY (role_id_fk) REFERENCES role (role_id),
  PRIMARY KEY (user_role_id)
);

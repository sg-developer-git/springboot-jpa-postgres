package com.sg.poc.postgres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sg.poc.postgres.utils.AppConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
@ToString
public class Student {

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postgres_poc_student_id")
	@SequenceGenerator(name = "postgres_poc_student_id", sequenceName = "postgres_poc_student_id", allocationSize = 1)
	private Long studentId;
	
	@Column(name = AppConstant.TABLE_STUDENT_COL_NAME_STUDENTREF, unique = true)
	@NotNull(message = "Student Ref can not be empty")
	private String studentRef;
	
	@Column(name = "STU_FIRST_NAME")
	@NotNull(message = "First Name can not be empty")
	private String firstName;
	
	@Column(name = "STU_MID_NAME")
	private String midName;
	
	@Column(name = "STU_LAST_NAME")
	@NotNull(message = "Last Name can not be empty")
	private String lastName;
	
	@Column(name = AppConstant.TABLE_STUDENT_COL_NAME_EMAIL, unique = true)
	@Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = AppConstant.TABLE_STUDENT_COL_NAME_EMAIL_ERROR)
	private String email;
}

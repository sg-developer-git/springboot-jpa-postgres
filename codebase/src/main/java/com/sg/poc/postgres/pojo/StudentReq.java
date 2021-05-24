package com.sg.poc.postgres.pojo;

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
@ToString
public class StudentReq {
	
	private Long studentId;
	
	@NotNull(message = "Student Ref can not be empty")
	private String studentRef;
	
	@NotNull(message = "First Name can not be empty")
	private String firstName;
	
	private String midName;
	
	@NotNull(message = "Last Name can not be empty")
	private String lastName;
	
	@Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = AppConstant.TABLE_STUDENT_COL_NAME_EMAIL_ERROR)
	@NotNull(message = "dent Email can not be empty")
	private String email;
}

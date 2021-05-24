package com.sg.poc.postgres.pojo;

import java.util.List;

import com.sg.poc.utility.exception.model.ApiStatus;

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
public class StudentRes {
	ApiStatus status;
	StudentReq student;
	List<StudentReq> students;

}

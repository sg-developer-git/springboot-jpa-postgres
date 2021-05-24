package com.sg.poc.postgres.mapper;

import com.sg.poc.postgres.entity.Student;
import com.sg.poc.postgres.pojo.StudentReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentMapper {
	
	private StudentMapper() {}

	public static Student mapStudentToStudentReq(StudentReq studentReq) {
		log.info("before mapping");
		Student student = new Student();
		student.setStudentId(studentReq.getStudentId());
		student.setEmail(studentReq.getEmail());
		student.setFirstName(studentReq.getFirstName());
		student.setMidName(studentReq.getMidName());
		student.setLastName(studentReq.getLastName());
		student.setStudentRef(studentReq.getStudentRef());
		log.info("after mapping");
		return student;
	}
	
	
	public static StudentReq mapStudentReqToStudent(Student student) {
		StudentReq studentReq = new StudentReq();
		studentReq.setStudentId(student.getStudentId());
		studentReq.setStudentRef(student.getStudentRef());
		studentReq.setEmail(student.getEmail());
		studentReq.setFirstName(student.getFirstName());
		studentReq.setMidName(student.getMidName());
		studentReq.setLastName(student.getLastName());
		return studentReq;
	}
}

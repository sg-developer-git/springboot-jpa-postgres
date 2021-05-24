package com.sg.poc.postgres.service;

import org.springframework.stereotype.Service;

import com.sg.poc.postgres.pojo.StudentReq;
import com.sg.poc.postgres.pojo.StudentRes;

@Service
public interface StudentService {

	StudentRes saveStudent(StudentReq student);
	StudentRes getStudents();
}

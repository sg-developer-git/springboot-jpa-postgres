package com.sg.poc.postgres.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.poc.postgres.pojo.StudentReq;
import com.sg.poc.postgres.pojo.StudentRes;
import com.sg.poc.postgres.service.StudentService;


@RestController
@RequestMapping("/student/v1")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping(
			path = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentRes> addStudent(@Valid @RequestBody StudentReq studentReq){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(this.studentService.saveStudent(studentReq));
	}
	
	
	
	@GetMapping(
			path = "",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentRes> fetchAllStudents(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.studentService.getStudents());
	}
	
	
	

	
}

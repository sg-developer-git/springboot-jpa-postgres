package com.sg.poc.postgres.dao;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.sg.poc.postgres.entity.Student;
import com.sg.poc.postgres.repository.StudentRepository;
import com.sg.poc.postgres.utils.AppConstant;
import com.sg.poc.utility.config.UtilsConstant;
import com.sg.poc.utility.exception.model.CustomDataException;
import com.sg.poc.utility.exception.model.CustomInternalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentDao {

	@Autowired
	StudentRepository studentRepo;
	
	
	//3101
	public Student addStudent(Student student) {
		try {
			return this.studentRepo.save(student);
		} catch (Exception e) {
			
			//unique constraint error handle
			if(e instanceof DataIntegrityViolationException) {
				Throwable exception = e.getCause().getCause();
				String error = exception.toString().toLowerCase();
				log.error("Database Error\n {}", error);
				
				if(error.contains(AppConstant.DAO_PSQL_EXCEPTION)
						&& error.contains(AppConstant.DAO_DUPLICATE_KEY_CONSTRAINT)) {
					String errorsInString="";
					if(error.contains(AppConstant.TABLE_STUDENT_COL_NAME_STUDENTREF.toLowerCase())) {
						errorsInString = "StudentRef already exists";
					} else if(error.contains(AppConstant.TABLE_STUDENT_COL_NAME_EMAIL.toLowerCase())) {
						errorsInString = "Student email already exists";
					} else {
						errorsInString = "Duplicate record";
					}
					throw new CustomDataException("PSQL-POC-310101", "Data Exists", Arrays.asList(errorsInString));
				}
			} 
			else if(e instanceof ConstraintViolationException) {
				String errorsInString="";
				String error = e.toString().toLowerCase();
				log.error("Database ErrorConstraintViolationException)\n {}", error);
				if(error.contains(AppConstant.TABLE_STUDENT_COL_NAME_EMAIL_ERROR.toLowerCase())) {
					errorsInString = AppConstant.TABLE_STUDENT_COL_NAME_EMAIL_ERROR;
				}
				throw new CustomDataException("PSQL-POC-310102", "Validation Error", Arrays.asList(errorsInString));
			}
			throw new CustomInternalException("PSQL-POC-310103", UtilsConstant.ERROR_UNKNOWN, Arrays.asList(UtilsConstant.ERROR_MSG_UNKNOWN));
		}
	}
	
	
	public Student getStudentByEmail(String email) {
		return this.studentRepo.findByEmail(email);
	}
	
	public List<Student> getAllStudents(){
		return this.studentRepo.findAll();
	}
}

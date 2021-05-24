package com.sg.poc.postgres.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.poc.postgres.dao.StudentDao;
import com.sg.poc.postgres.entity.Student;
import com.sg.poc.postgres.mapper.StudentMapper;
import com.sg.poc.postgres.pojo.StudentReq;
import com.sg.poc.postgres.pojo.StudentRes;
import com.sg.poc.utility.config.DateUtils;
import com.sg.poc.utility.config.UtilsConstant;
import com.sg.poc.utility.exception.model.ApiStatus;
import com.sg.poc.utility.exception.model.CustomDataException;
import com.sg.poc.utility.exception.model.CustomInternalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDao studentDao;

	@Override
	public StudentRes saveStudent(StudentReq studentReq) {
		try {
			StudentRes studentRes= null;
			Student dbStudent = null;
			
			dbStudent = StudentMapper.mapStudentToStudentReq(studentReq);
			
			dbStudent = studentDao.addStudent(dbStudent);
			studentRes = new StudentRes(new ApiStatus(null, "201", UtilsConstant.STATUS_SUCCESS, "Student Created", null), StudentMapper.mapStudentReqToStudent(dbStudent), null);
			log.info("Response->{}",studentRes);
			return studentRes;
		} catch (CustomDataException | CustomInternalException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomInternalException("PSQL-POC-210101", UtilsConstant.ERROR_UNKNOWN, Arrays.asList(UtilsConstant.ERROR_MSG_UNKNOWN));
		}
	}

	@Override
	public StudentRes getStudents() {
		try {
			log.info("before db call");
			List<Student> dbStudents = this.studentDao.getAllStudents();
			log.info("after db call");
			List<StudentReq> students = new ArrayList<>();
			dbStudents.stream().forEach(student -> {
				students.add(StudentMapper.mapStudentReqToStudent(student));
			});
			StudentRes response = new StudentRes(new ApiStatus(DateUtils.getSystemDate(), "200", UtilsConstant.STATUS_SUCCESS, "All student records fetched", null), null, students);
			log.info("Response->{}",response);
			return response;
		} catch (Exception e) {
			throw new CustomInternalException("PSQL-POC-210201", UtilsConstant.ERROR_UNKNOWN, Arrays.asList(UtilsConstant.ERROR_MSG_UNKNOWN));
		}
	}

}

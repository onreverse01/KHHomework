package com.kh.mybatis.student.model.service;

import com.kh.mybatis.student.model.vo.Student;
import static com.kh.mybatis.common.MybatisUtils.getSqlSession;

import java.util.Map;

public interface StudentService {

	int insertStudent(Student student);

	int insertStudentMap(Map<String, Object> student);

	int selectStudentCount();

	Student selectOneCount(int no);

	Map<String, Object> selectOneStudentMap(int no);
}

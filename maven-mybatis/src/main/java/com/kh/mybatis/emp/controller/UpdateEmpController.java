package com.kh.mybatis.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.emp.model.service.EmpService;
import com.kh.mybatis.emp.model.service.EmpServiceImpl;

public class UpdateEmpController extends AbstractController {

	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값
			String empId = request.getParameter("empId");
			System.out.println("empId@controller = " + empId);
			
			//2. 업무로직 : 직급목록, 부서목록, 사원1명 정보(부서명, 직급명)
			List<Map<String,String>> jobList = empService.selectJobList();
			List<Map<String,String>> deptList = empService.selectDeptList();
			Map<String, Object> emp = empService.selectOneEmp(empId);
			System.out.println("emp@controller = " + emp);
			
			if(emp == null) {
				throw new IllegalArgumentException(empId); 
			}
			
			//3. jsp위임
			request.setAttribute("jobList", jobList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("emp", emp);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "emp/updateEmp"; 
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empId;
		try {
			//1. 사용자 입력값 처리
			Map<String, String> param = new HashMap<>();
			param.put("empId", empId = request.getParameter("empId"));
			param.put("jobCode", request.getParameter("jobCode"));
			param.put("deptCode", request.getParameter("deptCode"));
			System.out.println("param@controller = " + param);
			
			//2. 업무로직
			int result = empService.updateEmp(param);
			if(result == 0)
				throw new IllegalArgumentException(empId);
			
			//3. 사용자피드백&리다이렉트
			request.getSession().setAttribute("msg", "사원 정보 수정 성공!");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/emp/updateEmp.do?empId=" + empId; 
	}
	
	
}

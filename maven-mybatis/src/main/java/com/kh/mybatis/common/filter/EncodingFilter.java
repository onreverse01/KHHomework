package com.kh.mybatis.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * web.xml에 등록된 순서대로 처리됨.
 * web.xml이 @webFilter 보다 우선 처리.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//인코딩처리
		request.setCharacterEncoding("utf-8");
		System.out.println("[utf-8] encoding 처리함.");
		
		chain.doFilter(request, response);
	}

}

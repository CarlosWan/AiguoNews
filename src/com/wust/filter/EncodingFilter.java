package com.wust.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ʵ�ֱ��������
 * @author hp
 *
 */
public class EncodingFilter implements Filter {
	private FilterConfig fc = null;
	
	public void init(FilterConfig fc) throws ServletException {
		this.fc = fc;
	}
	
	/*ִ�й�������صĴ���*/
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//��ȡ��ʼ�����õ��ַ�������
		String charset = fc.getInitParameter("charset");
		if (charset==null){
			charset="UTF-8";
		}
		
		
		//����������ַ�������
		req.setCharacterEncoding(charset);
		
		//������ת�����������е���һ��������,�������������û�������Ĺ������������󵽴�Ŀ����Դ
		chain.doFilter(req, resp);
	}
	

	public void destroy() {
		
	}

	
}

package com.pmg.security1.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GlobalIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("#####################################");
		log.info("# {}.{}();", ((HandlerMethod) handler).getBeanType().getSimpleName(),
				((HandlerMethod) handler).getMethod().getName());

//		Enumeration<String> parmas = request.getParameterNames();
//		while (parmas.hasMoreElements()) {
//			String name = (String) parmas.nextElement();
//			String[] values = request.getParameterValues(name);
//			for (String value : values) {
//				log.info("# name=" + name + ",value=" + value);
//			}
//		}

		log.info("#####################################");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("#####################################");
		log.info("# {}.{}(); END!!!", ((HandlerMethod) handler).getBeanType().getSimpleName(), ((HandlerMethod) handler).getMethod().getName());
		log.info("#####################################");
	}

}

package com.example.springboot.app.springbootschedule.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("scheduleInterceptor")
public class ScheduleInterceptor implements HandlerInterceptor {

	@Value("${config.schedule.openAt}")
	private Integer opensAt;

	@Value("${config.schedule.closeAt}")
	private Integer closesAt;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			if (request.getMethod().equalsIgnoreCase("get")) {
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
				if (hour >= opensAt && hour <= closesAt) {
					StringBuilder message = new StringBuilder("Welcome to the client attention schedule. ");
					message.append("We attend from ").append(opensAt).append(" hrs. to ").append(closesAt).append(" hrs.");
					message.append("Thank you for your visit");
					request.setAttribute("message", message.toString());
					return true;
				}
				response.sendRedirect(request.getContextPath().concat("/closed"));
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			if (request.getMethod().equalsIgnoreCase("get")) {
				String message = (String) request.getAttribute("message");
				modelAndView.addObject("message", message);
			}
		}
	}
	
}

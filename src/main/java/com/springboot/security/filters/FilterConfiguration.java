package com.springboot.security.filters;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfiguration {
	
	private DateFilter dateFilter;

	public FilterConfiguration(DateFilter dateFilter) {
		super();
		this.dateFilter = dateFilter;
	}
	
	@Bean
	public FilterRegistrationBean<DateFilter> dateFilterRegistrationBean(){
		FilterRegistrationBean<DateFilter> myDateFilterRegistrationBean
		= new FilterRegistrationBean<>();
		myDateFilterRegistrationBean.setFilter(dateFilter);
		myDateFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
		myDateFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		myDateFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE-1);
		return myDateFilterRegistrationBean;
		
	}

}

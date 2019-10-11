package com.app.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

	public LoginInterceptor getLoginInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 拦截路径
//		registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/")
//				.excludePathPatterns("/admin/login").excludePathPatterns("/static/css/*.css")
//				.excludePathPatterns("/static/js/*.js").excludePathPatterns("/static/image/*.png")
//				.excludePathPatterns("/static/image/*.jpg").excludePathPatterns("/static/image/*.gif");

	}
}

package com.fangcheng.test.configuration;

import com.fangcheng.test.converter.RoleToUserProfileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fangcheng.test")
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	/**
     * Configure ViewResolvers to deliver preferred views.
     */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); //视图解析器
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	/**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
	public void addCorsMappings(CorsRegistry registry) {
		// 允许指定的pathPattern可以进行跨域请求
		CorsRegistration corsRegistration = registry.addMapping("/pathPattern");
		// 设置允许哪些可以进行跨域访问，设置为"*"表示允许所有
		// 默认设置为允许所有
		corsRegistration.allowedOrigins("*");
		// 设置允许的跨域请求动作，设置为"*"表示允许所有
		// 默认设置为允许简单动作，包括GET POST HEAD
		corsRegistration.allowedMethods("*");
		// 设置允许的请求头，默认设置为允许所有，即"*"
		corsRegistration.allowedHeaders("Cache-Control", "Content-Language");
		// 设置response的头结构，不支持"*"
		corsRegistration.exposedHeaders("Cache-Control", "Content-Language");
		// 设置浏览器是否需要发送认证信息
		corsRegistration.allowCredentials(true);
		// 设置客户端保存pre-flight request缓存的时间
		// pre-flight request 预检请求
		corsRegistration.maxAge(1);
	}
    /**
	 * Configure Converter to be used.
	 * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
	 */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }


    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
    
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
}


package com.fangcheng.test.security;

import com.fangcheng.test.configuration.RestAuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

	/**
	 *
	 */
	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}
	/*				*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		http.csrf().ignoringAntMatchers("/test/**");
		http.csrf().ignoringAntMatchers("/upload");
		http.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
		http.sessionManagement().sessionFixation().newSession();
		http.authorizeRequests()
				.antMatchers( "/","/main","/alterApplication","/approval","/userInfo","/applicationRecord","/upload")
				.access("hasRole('STUDENT') or hasRole('COUNSELLOR') or hasRole('COLLEGE') or hasRole('ADMIN')")
				.antMatchers("/userList")
				.access("hasRole('COUNSELLOR') or hasRole('COLLEGE') or hasRole('ADMIN')")
				.antMatchers("/newuser", "/delete-user")
				.access("hasRole('ADMIN')")
				.antMatchers("/edit-user-*")
				.access("hasRole('STUDENT')")
				.antMatchers("/addapplication")
				.access("hasRole('STUDENT')")
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("userId")
				.passwordParameter("password")
				.and()
				.rememberMe()
				.rememberMeParameter("remember-me")
				.tokenRepository(tokenRepository)
				.tokenValiditySeconds(10)
				.and()
				.exceptionHandling()
				.accessDeniedPage("/Access_Denied");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new RestAuthenticationAccessDeniedHandler();
	}

}
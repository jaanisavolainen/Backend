package com.example.kyselyBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.example.kyselyBack.domain.EndUser;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UDService detailsService;
    
    @Autowired
	private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(EndUser.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/**").permitAll()
        .and()
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.GET, "/getUserAnswersBySurvey/**").permitAll()
        .and()
        	.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/userAnswers", "/api/answerSets", "/api/uniqueUserSessions").permitAll()
        .and()
        	.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/endUsers").hasAuthority("ROLE_ADMIN")
        .and()
        	.csrf().disable()
            .authorizeRequests().anyRequest().hasAuthority("ROLE_ADMIN")
		.and()
			.httpBasic()
			.authenticationEntryPoint(authEntryPoint)
		.and()
			.csrf().disable();
                
                
    }
}

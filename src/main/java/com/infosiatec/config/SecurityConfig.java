package com.infosiatec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.infosiatec.config.auth.PrincipalDetailService;
import com.infosiatec.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public AuthenticationManager authenticationManager(
        final AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
    }//5.7부터 권한매니저 설정 필요.
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http.userDetailsService(principalDetailService)
					.csrf().disable()
					.authorizeRequests()
						.antMatchers("/**","/auth/**")
						.permitAll()
						.anyRequest()
						.authenticated()
					.and()
						.formLogin()
						.loginPage("/auth/loginForm")
						.loginProcessingUrl("/auth/loginProc")
						.defaultSuccessUrl("/")
					.and()
						.logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/")
						.invalidateHttpSession(false);
		 
		return http.build();
	}
	
	
}

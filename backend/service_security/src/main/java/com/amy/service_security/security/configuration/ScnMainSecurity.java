package com.amy.service_security.security.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.amy.service_security.security.jwt.SjwEntryPoint;
import com.amy.service_security.security.jwt.SjwTokenFilter;
import com.amy.service_security.service.interfaz.SntUser;
//import static com.amy.service_security.util.constant.Constants.AUTH_URL;

//
@Configuration
@EnableWebSecurity
//Para indicar a que metodos puede accesder solo el administrador.
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableZuulProxy
public class ScnMainSecurity extends WebSecurityConfigurerAdapter{
	private final static Logger logger = LoggerFactory.getLogger(ScnMainSecurity.class);

	@Value("${jwt.auth_url}")
	private String auth_url;

	@Autowired
	SntUser ssiUser;
	@Autowired
	SjwEntryPoint swtEntryPoint;
	
	@Bean
	public SjwTokenFilter jwtTokenFilter() {
		return new SjwTokenFilter();
	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(ssiUser).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		logger.warn("configure"); 
		httpSecurity
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable()
		.authorizeRequests().antMatchers(HttpMethod.GET, auth_url ).permitAll()
		//.and().authorizeRequests().antMatchers(HttpMethod.GET, auth_url ).permitAll()
		//.authorizeRequests().antMatchers(HttpMethod.POST, auth_url ).permitAll()
		//.authorizeRequests().antMatchers("/auth/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(swtEntryPoint)

		.and().formLogin()
        .loginPage("/login")
		.permitAll()

		.and().logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll()

		;
				
		/*
		http
		.cors()
		.and().csrf().disable()
        
        .headers().frameOptions().disable()
        
        .and().authorizeRequests()
        .antMatchers("/auth/**", "/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
        .anyRequest().authenticated()
        
		.and().exceptionHandling()
		.authenticationEntryPoint(jwtEntryPoint) //SjwEntryPoint
		
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)       
        
		.and().formLogin()
        .loginPage("/login")
        .permitAll()
        
        .and().logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll();
        */
		
		//Pasar el usuario al contexto de autenticacion
		httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	//@Bean
	//CorsConfigurationSource corsConfigurationSource() {
	//	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	//	source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	//	return source;
	//}
}
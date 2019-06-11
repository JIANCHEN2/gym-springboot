package com.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


//@SpringBootApplication
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//				.withUser("admin").password("123456").roles("ADMIN")
//				.and()
//				.withUser("user").password("123456").roles("USER");
//	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	}

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

    	UserDetails user=User.builder().username("user").password(passwordEncoder.encode("secret")).
    			roles("USER").build();
    	UserDetails userAdmin=User.builder().username("admin").password(passwordEncoder.encode("secret")).
    			roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,userAdmin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 将 check_token 暴露出去，否则资源服务器访问时报 403 错误
		web.ignoring().antMatchers("/oauth/check_token");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
                .antMatchers("/swagger-resources").permitAll()
             	.antMatchers("/search/list").hasRole("USER")
            	.antMatchers("/search/delete").hasRole("ADMIN")
//            	.and()
//                .formLogin()
//                .loginPage("/")
//                .permitAll()
//                .and()
//                .logout() // Metodo get pues he desabilitado CSRF
//                .permitAll()
                ;
    }

}

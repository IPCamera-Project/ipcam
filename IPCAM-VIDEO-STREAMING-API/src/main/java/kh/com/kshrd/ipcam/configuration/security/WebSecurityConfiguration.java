package kh.com.kshrd.ipcam.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("ajaxAuthenticationFailureHandler")
    AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    @Autowired
    @Qualifier("ajaxAuthenticationSuccessHandler")
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
        protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
            	.withUser("a")
            	.password("a")
            	.roles("ADMIN");
    }
    @Override
        protected  void configure(HttpSecurity httpSecurity)throws  Exception{
            httpSecurity.authorizeRequests().antMatchers("/swagger/**").hasRole("ADMIN");
            httpSecurity.
                    formLogin().
                    loginPage("/login").
                    usernameParameter("username").
                    passwordParameter("password").
                    permitAll().
                    successHandler(ajaxAuthenticationSuccessHandler).
                    failureHandler(ajaxAuthenticationFailureHandler);
            httpSecurity.csrf().disable();
            httpSecurity.exceptionHandling().accessDeniedPage("/access-denied");

    }
}

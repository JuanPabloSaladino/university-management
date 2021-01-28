/*
package com.juanpablosaladino.university_management.config;

import org.springframework.context.annotation.Configuration;
*/
/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*//*


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    String[] resources = new String[]{
            "/include/**", "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**"
    };

*/
/*    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .anyRequest().authenticated()
*//*
*/
/*
                .antMatchers("/admin/**").access("hasRole('admin')")
*//*
*/
/*


*//*
*/
/*
                .antMatchers("/", "/login").permitAll()
*//*
*/
/*

                .and()
                .oauth2Login()
                .defaultSuccessUrl("/user-form");

    }*//*

}
*/

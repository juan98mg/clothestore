package com.experimentaly.api.clothesstore.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
@Configuration
@Profile(value = {"dev"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().and().authorizeRequests()
                .antMatchers("/v1/auth/").permitAll().antMatchers("/v1/recover-password/*")
                .permitAll().antMatchers("/swagger-ui/").permitAll()
                .antMatchers("/v1/api-docs", "/swagger-resources",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security")
                .permitAll().antMatchers("/actuator/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/v1").permitAll();
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}

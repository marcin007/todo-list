package com.marcinwo.todolist.app.security;

import com.marcinwo.todolist.app.security.jwt.JwtTokenFilter;
import com.marcinwo.todolist.app.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = false)
public class MultiHttpSecurityConfiguration {

    private UserDetailsService userDetailsService;

    @Autowired
    public MultiHttpSecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Order(1)
    @Configuration
    public static class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private JwtTokenProvider jwtTokenProvider;

        @Autowired
        public ApiSecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
            this.jwtTokenProvider = jwtTokenProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .headers().frameOptions().disable()//
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/h2console/**").permitAll()
                    .and()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Order(2)
    @Configuration
    public static class VaadinSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/", "/login", "/register", "/h2console/**").permitAll()
                    .antMatchers("/configuration/ui", "/configuration/security", "/webjars/**", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**").permitAll()
                    .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login")
                    .permitAll()
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error")
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")

            ;
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(
                    // Vaadin Flow static resources
                    "/VAADIN/**",

                    // the standard favicon URI
                    "/favicon.ico",

                    // the robots exclusion standard
                    "/robots.txt",

                    // web application manifest
                    "/manifest.webmanifest",
                    "/sw.js",
                    "/offline-page.html",

                    // icons and images
                    "/icons/**",
                    "/images/**",

                    // (development mode) static resources
                    "/frontend/**",

                    // (development mode) webjars
                    "/webjars/**",

                    // (development mode) H2 debugging console
                    "/h2-console/**",

                    // (production mode) static resources
                    "/frontend-es5/**", "/frontend-es6/**");
        }
    }


}

package es.mastercloudapps.security_example.security;

import es.mastercloudapps.security_example.filter.AuthenticationFilter;
import es.mastercloudapps.security_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //explicar
@RequiredArgsConstructor

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService; //It helps us to identify our users.
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication() Users can be added here
        BCryptPasswordEncoder encoder = (BCryptPasswordEncoder) passwordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilter(new AuthenticationFilter(authenticationManager()));
    }

    //There is already an authentication manager in WebSecurityConfigurerAdapter
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

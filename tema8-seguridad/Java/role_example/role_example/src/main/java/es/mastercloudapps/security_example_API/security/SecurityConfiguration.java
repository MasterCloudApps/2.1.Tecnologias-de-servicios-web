package es.mastercloudapps.security_example_API.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//To deal with everything in our application
@Configuration
@EnableWebSecurity
//In order to use preAuthorize annotation we need add this one previously
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //Method that allow us to configure access to webpages.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests() //To all the requests
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                // INIT : Permissions based
                //.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(UserPermissions.BOOK_WRITE.getPermission())
                //.antMatchers(HttpMethod.POST,"/api/**").hasAuthority(UserPermissions.BOOK_WRITE.getPermission())
                //.antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(UserPermissions.BOOK_WRITE.getPermission())
                //.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name()) // we can add more roles
                // END : Permissions based

                // INIT : Role based
                //.antMatchers("/api/**").hasRole(UserRole.ADMIN.name())
                //.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(UserPermissions.BOOK_WRITE.name())
                //.antMatchers(HttpMethod.POST,"/api/**").hasAuthority(UserPermissions.BOOK_WRITE.name())
                //.antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(UserPermissions.BOOK_WRITE.name())
                //.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name()) //
                // END : Role based
                .anyRequest() //Every request
                .authenticated() //Must be authenticated
                .and()
                .httpBasic(); //Through a basic authentication mechanism
    }

    //How retrieve our users (from the database or wherever)
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails perryUser = User.builder()
                .username("perry")
                .password(passwordEncoder.encode("password"))
                //.roles(UserRole.USER.name()) //It appears like ROLE_USER
                .authorities(UserRole.USER.getGrantedAuthorities())
                .build();

        UserDetails nicoUser = User.builder()
                .username("nico")
                .password(passwordEncoder.encode("password"))
                //.roles(UserRole.ADMIN.name()) //It appears like ROLE_ADMIN
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                perryUser, nicoUser
        );
    }
}

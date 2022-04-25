package com.example.animal.clinic.back.config;

import com.example.animal.clinic.back.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private final static String USERS_QUERY = "SELECT email, password, enabled "
            + "FROM users INNER JOIN passwords ON users.id=user_id "
            + "WHERE email = ?";
    private final static String ROLES_QUERY = "SELECT email, name "
            + "FROM users INNER JOIN user_role ON user.id=user_id "
            + "INNER JOIN roles ON roles.id=role_id "
            + "WHERE email = ?";

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService)
                .and()
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY);
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/medical-services/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

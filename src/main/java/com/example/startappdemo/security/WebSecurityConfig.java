package com.example.startappdemo.security;


import com.example.startappdemo.security.jwt.CustomAccessDeniedHandler;
//import com.example.startappdemo.security.jwt.TrackingUserFilter;
import com.example.startappdemo.security.service.impl.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

//    @Bean
//    TrackingUserFilter authenticationJwtTokenFilter() {
//        return new TrackingUserFilter();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/js/**",
                        "/index", "/auth/*" ).permitAll()
                .requestMatchers("*/chat/", "/romgiaodich/", "/user-infor/")
                .hasAuthority("ROLE_USER")
                .requestMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        );

        http.exceptionHandling(ex ->ex.authenticationEntryPoint(customAccessDeniedHandler));

        http.formLogin(form ->
                form.loginPage("/auth/login")
                        .loginProcessingUrl("/j_spring_security_check")
                        .defaultSuccessUrl("/index")
                        .failureUrl("/auth/login?message=error_login")
                        .usernameParameter("email")
                        .passwordParameter("password"));

        http.logout(form -> form.logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/")
                .deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true)
        );

        http.authenticationProvider(authenticationProvider());
//        http.addFilterAfter( authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }


}

package com.example.startappdemo.security;


import com.example.startappdemo.security.filter.AsyncSupportFilter;
import com.example.startappdemo.security.jwt.CustomAccessDeniedHandler;
import com.example.startappdemo.security.service.impl.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Async
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

/*
Error :
 path [] threw exception [Request processing failed: org.springframework.web.socket.sockjs.SockJsException:
  Uncaught failure in SockJS request,
  uri=http://localhost:8080/tr/384/t155rhg4/xhr?t=1711987828850&continue]
with root cause

vì trong trường hợp bình thường, kết nối là kết nối websocket,
uri này phải là ws=http://xxx/user/ 854/qckzogtf/websocket
( Vì mạng chậm nó sẽ đổi giao thức )
thì có thể thấy giao thức ở đây nên chuyển sang xhr_streaming

* Lý do tại sao câu này phải được thêm vào bộ lọc
*  là do yêu cầu chuyển đổi giao thức websocket bị bộ lọc chặn.
*  Nếu không định cấu hình async trong bộ lọc, thì giao thức sẽ được chuyển đổi.
* Yêu cầu sẽ không đồng bộ nên sẽ báo lỗi trên.
*
* */
    @Bean
    AsyncSupportFilter asyncSupportFilterForWebSocket() {
        return new AsyncSupportFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/js/**", "/fontawesomefree_6.5.1web/**", "/jquery/**",
                        "/images/**", "/index", "/auth/*").permitAll()
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
//        http.addFilterBefore( asyncSupportFilterForWebSocket(), UsernamePasswordAuthenticationFilter.class);


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

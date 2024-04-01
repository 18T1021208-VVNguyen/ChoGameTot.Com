package com.example.startappdemo.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;


public class TrackingUserFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(TrackingUserFilter.class);

//
//    @Autowired
//    private UserLogTimeRepository userLogTimeRepository;
//
//    @Autowired
//    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {


        logger.info(request.getRequestURL().toString());
        try{

//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//
//
//            if(auth != null ){
//
//
//                UserDetailsImpl userDetails = ((UserDetailsImpl) auth.getPrincipal());
//
////                Đã login.
//                Optional<UserLogTimeEntity> userInTableUserLogTime = userLogTimeRepository.findByUser(userDetails.getId());
//
//                UserLogTimeEntity entityUpdatedOrCreated = null;
//
//                if(userInTableUserLogTime.isPresent()){
//                    entityUpdatedOrCreated = userInTableUserLogTime.get();
//                    entityUpdatedOrCreated.setLastTime(new Date());
//                }
//                else {
//                    UserEntity user =  userRepository.findById(userDetails.getId())
//                            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Id: " + userDetails.getId()));
//
//                    entityUpdatedOrCreated = UserLogTimeEntity.builder()
//                                .user(user)
//                                .ip(this.getInforIPAddress(request))
//                                .lastTime(new Date())
//                                .build();
//                    entityUpdatedOrCreated.setCreatedAt(new Date());
//                }
//
//                userLogTimeRepository.save(entityUpdatedOrCreated);
//            }




        }catch (Exception e){
           logger.error( e.getMessage());
        }

        filterChain.doFilter(request, response);


    }
//    private  String getInforIPAddress(HttpServletRequest request){
//        String ipAddress = request.getHeader("X-FORWARDED-FOR");
//        if (ipAddress == null || ipAddress.isEmpty())
//            ipAddress = request.getRemoteAddr();
//        // localhost
//        if(ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("127.0.0.1")){
//            ipAddress = "127.0.0.1";
//        }
//        return  ipAddress;
//
//    }
}

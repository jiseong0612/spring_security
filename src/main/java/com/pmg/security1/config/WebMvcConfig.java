package com.pmg.security1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	//자동으로 빈으로 등록해두고, 해당 클래스를 파싱해서 @Bean이 있는 메소드를 찾아서 빈을 생성해준다.
//@Configuration 안에서 @Bean을 사용해야 싱글톤
public class WebMvcConfig implements WebMvcConfigurer {

}

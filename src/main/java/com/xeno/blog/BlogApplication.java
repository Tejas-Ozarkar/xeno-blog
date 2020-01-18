package com.xeno.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

	@SpringBootApplication
	public class BlogApplication {

		public static void main(String[] args) {
			SpringApplication.run(BlogApplication.class, args);
		}

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurerAdapter() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedMethods("*")
							.allowedOrigins("http://localhost:4200","http://blizzmolt-blog.s3-website.us-east-2.amazonaws.com");
				}
			};
		}
}

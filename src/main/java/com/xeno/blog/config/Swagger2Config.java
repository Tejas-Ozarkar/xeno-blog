package com.xeno.blog.config;


import com.google.common.collect.Lists;
import com.xeno.blog.model.JwtRequest;
import com.xeno.blog.model.JwtResponse;
import com.xeno.blog.service.AuthService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Autowired
    AuthService authService;
    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(singletonList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .hidden(true)
                                .defaultValue("Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWpvemFya2FyIiwiZXhwIjoxNTc5NDU2NDA5LCJpYXQiOjE1Nzk0Mzg0MDl9.jCze8hJTmMgZ4siCYNN2IkiGU4w_kkE-D0tu1BwaRjwgE6myq7-bgH7zm_744xSLOUAaCQiUzWyxWn2V-l-O-A")
                                .build()
                        )
                ).select()
                .apis(RequestHandlerSelectors.basePackage("com.xeno.blog"))
                .build().apiInfo(apiInfo()).securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Xeno Blog", "", "", "",
               "", "", "");
        return apiInfo;
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "Authorization", "");
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
}


//
//@Configuration
//@EnableSwagger2
//public class Swagger2Config {
//
//    @Autowired
//    AuthService authService;
//    @Bean
//    public Docket productApi() {
////        getEmployees();
//        JwtRequest jwtRequest = new JwtRequest("tejozarkar","tejas");
//        JwtResponse jwtResponse = new JwtResponse("");
//        try{
//           jwtResponse = authService.createAuthenticationToken(jwtRequest);
//            System.out.println(jwtResponse);
//        } catch(Exception e){
//            System.out.println("Exception");
//        }
//        ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("Authorization")                 // name of header
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")               // type - header
//                .defaultValue("Bearer "+jwtResponse.getToken())        // based64 of - zone:mypassword
//                .required(true)                // for compulsory
//                .build();
//        java.util.List<Parameter> aParameters = new ArrayList<>();
//        aParameters.add(aParameterBuilder.build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.xeno.blog"))
//                .build()
//                .globalOperationParameters(aParameters);
//    }
//
////    @Bean
////    public Docket api() {
////        //Adding Header
////                  // add parameter
////        return new Docket(DocumentationType.SWAGGER_2).select()
////                .apis(RequestHandlerSelectors
////                        .any())
////                .paths(PathSelectors.any())
////                .build().
////                        pathMapping("")
////                .globalOperationParameters(aParameters);
////    }
//}

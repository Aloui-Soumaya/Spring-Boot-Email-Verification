package com.example.demo.Config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMapper {

    //Configuration
    //mapper
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}

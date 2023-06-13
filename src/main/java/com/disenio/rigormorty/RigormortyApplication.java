package com.disenio.rigormorty;

import com.disenio.rigormorty.security.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class RigormortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RigormortyApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties(){
		return new AppProperties();
	}

}

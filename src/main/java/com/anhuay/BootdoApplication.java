package com.anhuay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.anhuay.*.dao")
@SpringBootApplication
@EnableCaching
public class BootdoApplication {

	/**
	 * 防止json时出现错误FAIL_ON_EMPTY_BEANS
	 * 
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		ObjectMapper myObjectMapper = new ObjectMapper();
		myObjectMapper.registerModule(simpleModule);
		myObjectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return myObjectMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(BootdoApplication.class, args);
		System.out.println(
				"ヾ(◍°∇°◍)ﾉﾞ    anhuay启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" + " ______                    _   ______            \n"
						+ "|_   _ \\                  / |_|_   _ `.          \n"
						+ "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n"
						+ "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n"
						+ " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n"
						+ "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}
}

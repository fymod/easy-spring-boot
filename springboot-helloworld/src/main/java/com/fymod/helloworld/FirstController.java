package com.fymod.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 
 * 是@Controller和方法上@@ResponseBody的合并，可以理解为方法入口并异步返回结果
 */
@RestController
public class FirstController {

	/**
	 * @Value 注解可以读取配置文件中该key下面的value，并赋值给对象
	 */
	@Value("${myname}")
	private String name;
	
	/**
	 * 理论上，这个返回值就是application.yml中myname对应的value
	 * 注解@GetMapping限制只能使用Get方法访问，自然也有PostMapping
	 */
	@GetMapping("name")
	public String getName() {
		return name;
	}
	
	@Value("${key}")
	private String key;
	
	@GetMapping("key")
	public String getKey() {
		return key;
	}
	
}

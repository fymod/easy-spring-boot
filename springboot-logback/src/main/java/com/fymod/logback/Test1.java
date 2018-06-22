package com.fymod.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1 {

	private static Logger log = LoggerFactory.getLogger(Test1.class);

	@GetMapping("/test1")
	public String get(String name) {
		log.info("info消息");
		log.debug("debug消息");
		log.error("error消息");
		return "收到了：" + name;
	}

}

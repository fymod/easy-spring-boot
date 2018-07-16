package com.fymod.helloworld;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 
 * 是@Controller和方法上@@ResponseBody的合并，可以理解为方法入口并异步返回结果
 */
@RestController
public class SecondController {

	/**
	 * @Autowired 将定义好的接口注入
	 */
	@Autowired private MyTableRepository myTableRepository;
	
	/**
	 * 测试1。 使用JpaRepository提供的save方法保存数据
	 * 多说一句：save不一定是保存，也可能是更新数据，要看提供数据的主键是否已经存在了
	 */
	@GetMapping("save")
	public Long save() {
		MyTable table = new MyTable();
		table.setName("测试名称");
		myTableRepository.save(table);
		return table.getTableId();
	}
	
	/**
	 * 测试2。 使用JpaRepository提供的findById方法来查询数据
	 * 测试1中会返回表Id，测试2就用这个Id来查询全部数据并返回
	 */
	@GetMapping("query")
	public MyTable queryById(Long tableId) {
		// SpringBoot 1.5中有findOne方法，2.0中可以使用findById
		return myTableRepository.findById(tableId).get();
	}
	
	/**
	 * 测试3。 使用我们定义的方法来查询数据
	 */
	@GetMapping("query2")
	public List<MyTable> queryByName(String name) {
		return myTableRepository.findByName(name);
	}
	
}

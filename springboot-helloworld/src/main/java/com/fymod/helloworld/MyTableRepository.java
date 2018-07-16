package com.fymod.helloworld;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 必须是接口。
 * 需要继承JpaRepository。
 */
public interface MyTableRepository extends JpaRepository<MyTable, Serializable>{

	/**
	 * 会根据命名规则来自动生成sql去查询。findByName的意思是根据字段Name来查询数据。
	 */
	public List<MyTable> findByName(String name);
	
}

package com.fymod.shiro.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable>
		extends PagingAndSortingRepository<T, I>, JpaSpecificationExecutor<T> {

}

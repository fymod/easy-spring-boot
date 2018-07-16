package com.fymod.timed.quartz;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Serializable> {

	public List<Config> findByTypes(Integer types);
}
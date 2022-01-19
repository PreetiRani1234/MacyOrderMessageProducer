package com.macy.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macy.producer.entity.ProducerEntity;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Integer> {
	
	

}

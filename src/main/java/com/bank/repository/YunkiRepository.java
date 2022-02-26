package com.bank.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bank.entity.Yunki;

public interface YunkiRepository extends ReactiveMongoRepository<Yunki, String>{

}

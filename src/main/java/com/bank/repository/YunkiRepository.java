package com.bank.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bank.entity.Yunki;

import reactor.core.publisher.Mono;

public interface YunkiRepository extends ReactiveMongoRepository<Yunki, String>{

	Mono<Yunki> findByPhonenumber(String numberphone);
}

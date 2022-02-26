package com.bank.service;

import com.bank.entity.Yunki;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IYunkiService {

	Mono<Yunki> create(Yunki yunki);
	
	Flux<Yunki> findAll();
	
	Mono<Yunki> update(Yunki yunki,String idyunki);
	
	Mono<Void> deleteById(String idyunki);
	
	Mono<Yunki> findById(String idyunki);
}

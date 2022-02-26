package com.bank.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.entity.Yunki;
import com.bank.service.IYunkiService;

import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accountyunki")
public class YunkiController {

	private final IYunkiService serviceYunki;
	
	@PostMapping
	public Mono<Yunki> save(@RequestBody Yunki yunki){
		return serviceYunki.create(yunki);
	}
	
	@PutMapping("/{idyunki}")
	public Mono<Yunki> update(@RequestBody Yunki yunki,@PathVariable String idyunki){
		
		return serviceYunki.update(yunki,idyunki); 
	}
	
	@DeleteMapping("/{idyunki}")
	public Mono<Void> delete(@PathVariable String idyunki){
		
		return serviceYunki.deleteById(idyunki); 
	}
	
	@GetMapping("/{idyunki}")
	public Mono<Yunki> findById(@PathVariable String idyunki){
		
		return serviceYunki.findById(idyunki); 
	}
	
	@GetMapping
	public Flux<Yunki> findAll(){
		
		return serviceYunki.findAll(); 
	}
}

package com.bank.servicedb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bank.entity.Yunki;
import com.bank.repository.YunkiRepository;
import com.bank.service.IYunkiService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class YunkiServicedb implements IYunkiService{

	private final YunkiRepository repoYunki;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public Mono<Yunki> create(Yunki yunki) {
		// TODO Auto-generated method stub
		return repoYunki.save(yunki);
	}

	@Override
	public Flux<Yunki> findAll() {
		// TODO Auto-generated method stub
		return repoYunki.findAll();
	}

	@Override
	public Mono<Yunki> update(Yunki yunki, String idyunki) {
		// TODO Auto-generated method stub
		return repoYunki.findById(idyunki)
				.flatMap(e->{
					yunki.setId(e.getId());
					return repoYunki.save(yunki);
				});
	}

	@Override
	public Mono<Void> deleteById(String idyunki) {
		// TODO Auto-generated method stub
		return repoYunki.deleteById(idyunki);
	}

	@Override
	public Mono<Yunki> findById(String idyunki) {
		// TODO Auto-generated method stub
		return repoYunki.findById(idyunki);
	}

	
	@KafkaListener(topics = "yunki")
    public void consumeMessage(String ddd){
        System.out.println("consumidor Yunki :"+ddd);
        //createssss(ddd);
       //kafkaTemplate.send("yunkisubmit", "Enviado desde el account");
    }
	
	public void createssss(String yunki){
        
       kafkaTemplate.send("yunkisubmit", "Enviado");
    }
}

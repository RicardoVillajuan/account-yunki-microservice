package com.bank.servicedb;


import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.stereotype.Service;

import com.bank.entity.Yunki;
import com.bank.model.Operation;
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
	KafkaTemplate<String, Yunki> kafkaTemplate;
	
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
	//Entidad accountYunki
	//recibimos(consumer)
	@KafkaListener(topics = "yunki")
    public void consumeMessage(Operation operation){
        System.out.println("consumidor Yunki :"+operation.toString());
        createssss(operation);
       //kafkaTemplate.send("yunkisubmit", "Enviado desde el account");
    }
	
	//envio 
	public void createssss(Operation operation){
       Mono<Yunki> yunkiaccountsub=repoYunki.findByPhonenumber(operation.getPhonenumbersubmit());
       Mono<Yunki> yunkiaccountrec=repoYunki.findByPhonenumber(operation.getPhonenumberreceive());
       yunkiaccountsub.doOnNext(yunkisubmit->{
    	   if(yunkisubmit.getBalance()<operation.getBalance()) {
    		   throw new RuntimeException("No cuenta con el saldo suficiente");
    	   }
       }).flatMap(yunkisubmit->{
    	   return yunkiaccountrec.map(yunkireceive->{
    		   yunkisubmit.setBalance(yunkisubmit.getBalance()-operation.getBalance());
    		   yunkireceive.setBalance(yunkireceive.getBalance()+operation.getBalance());
    		   
    		   update(yunkisubmit, yunkisubmit.getId()).subscribe();
    		   update(yunkireceive, yunkireceive.getId()).subscribe();
    		   /*try {
    				kafkaTemplate.send("enviocuatro", yunki).get();
    				} catch (InterruptedException | ExecutionException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    	*/   
    		   System.out.println("========================="+yunkireceive);
    		   System.out.println("========================="+yunkireceive);
    		   System.out.println("========================="+yunkireceive);
    		   System.out.println("========================="+yunkireceive);
    		   System.out.println("========================="+yunkireceive);
    		   return yunkireceive;
    	   });
       }).subscribe();
    }
	
	
}

package com.bank.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Yunki {
	
	@Id
	private String id;
	private String phonenumber;
	private String typedocument;
	private String documentnumber;
	private String imei;
	private String email;
	private Double balance;
	private String idaccount;
	
	
}

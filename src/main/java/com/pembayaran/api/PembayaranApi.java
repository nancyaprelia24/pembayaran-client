package com.pembayaran.api;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pembayaran.model.Pembayaran;

@Service
public class PembayaranApi {
	
	String url = "http://localhost:8080/";
	
	@Autowired RestTemplate restTemplate;
	
	public List<Pembayaran> getAllPembayaran(){
		List<Pembayaran> listPembayaran= Arrays.stream(restTemplate.getForObject(url + "pembayaran/get", Pembayaran[].class)).collect(Collectors.toList());
		return listPembayaran;
	}
	
	public Pembayaran getPembayaranById(int id) {
		Pembayaran pembayaran = restTemplate.getForObject(url + "/pembayaran/get/{id}", Pembayaran.class, id);
		return pembayaran;
	}
	
	public void deletePembayaran(Integer id) {
		Map<String, Integer> parameter = new HashMap<String, Integer>();
		parameter.put("id", id);
		restTemplate.delete(url + "/pembayaran/delete/{id}", parameter);
	}
	
	public void updatePembayaran(Pembayaran pembayaran) {
		Map<String, Integer> parameter = new HashMap<String, Integer>();
		parameter.put("id", pembayaran.getId_pembayaran());
		restTemplate.put(url + "pembayaran/edit/{id}", pembayaran, parameter);
	}
	
	public void addPembayaran(Pembayaran pembayaran) {
		restTemplate.postForObject(url + "/pembayaran/add", pembayaran, Pembayaran.class);
	}


}

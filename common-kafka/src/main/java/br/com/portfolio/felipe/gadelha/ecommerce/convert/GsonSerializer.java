package br.com.portfolio.felipe.gadelha.ecommerce.convert;

import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

public class GsonSerializer<T> implements Serializer<T>{
	
	private final Gson gson = new Gson();

	@Override
	public byte[] serialize(String topic, T data) {
		return gson.toJson(data).getBytes();
	}
	
	

}

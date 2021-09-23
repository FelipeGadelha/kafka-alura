package br.com.portfolio.felipe.gadelha.ecommerce.convert;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;

public class GsonDeserializer<T> implements Deserializer<T>{
	
	public static final String TYPE_CONFIG = "br.com.portfolio.felipe.gadelha.ecommerce.type_config";
	private final Gson gson = new Gson();
	private Class<T> type;

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		String typeName = String.valueOf(configs.get(TYPE_CONFIG));
		try {
			this.type = (Class<T>) Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Type for deserialization does not exist in the classpath.", e);
		}
	}
	
	@Override
	public T deserialize(String topic, byte[] data) {
		return gson.fromJson(new String(data), type);
	}
}

package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import com.kafka.properties.KafkaProperties;

@Configurable
@EnableKafka
public class KafkaConsumerConfig {

	private final KafkaProperties properties;
	
	public KafkaConsumerConfig(KafkaProperties properties) {
		this.properties = properties;
	}
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		 Map<String, Object> config = new HashMap<>();
	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	        return new DefaultKafkaConsumerFactory<>(config);
	}
	
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();
        
        // Opcional: manejar errores en consumo
	    factory.setCommonErrorHandler(new DefaultErrorHandler((record, exception) -> {
	        // Aquí puedes hacer logging, guardar en BD, o cualquier lógica de fallback
	        System.err.println("Error deserializando: " + exception.getMessage());
	    }, new FixedBackOff(0L, 0))); // No reintentos
	    return factory;
    }
	
}

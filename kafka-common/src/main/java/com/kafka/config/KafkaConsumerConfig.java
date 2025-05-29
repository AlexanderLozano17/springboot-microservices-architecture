package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafka.properties.KafkaProperties;
import com.kafka.saga.BaseEventData;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	private final KafkaProperties properties;
	
	public KafkaConsumerConfig(KafkaProperties properties) {
		this.properties = properties;
	}
	
	@Bean
	public ConsumerFactory<String, BaseEventData> consumerFactorySagaEvent() {
		Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        JsonDeserializer<BaseEventData> deserializer = new JsonDeserializer<>(BaseEventData.class, mapper);
        deserializer.setRemoveTypeHeaders(false);
        //deserializer.addTrustedPackages("com.kafka.saga");
        deserializer.setUseTypeMapperForKey(true);
        
        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                deserializer
            );
	}
	    
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, BaseEventData> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BaseEventData> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactorySagaEvent());

        // Manejo de errores opcional
        factory.setCommonErrorHandler(new DefaultErrorHandler(
            (record, exception) -> {
                System.err.println("Error deserializando: " + exception.getMessage());
            },
            new FixedBackOff(0L, 0)
        ));

        return factory;
    }
}

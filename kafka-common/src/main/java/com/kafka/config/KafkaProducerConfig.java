package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kafka.properties.KafkaProperties;
import com.kafka.saga.BaseEventData;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

	private final KafkaProperties properties;
	
	public KafkaProducerConfig(KafkaProperties kafkaProperties) {
		this.properties = kafkaProperties;
	}
	
	@Bean
	public ProducerFactory<String, BaseEventData> producerFactory() {
		Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        config.put(ProducerConfig.RETRIES_CONFIG, properties.getProducer().getRetries());
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getProducer().getBatchSize());
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, properties.getProducer().getBufferMemory());
        config.put(ProducerConfig.LINGER_MS_CONFIG, properties.getProducer().getLingerMs());
        config.put(ProducerConfig.ACKS_CONFIG, properties.getProducer().getAcks());
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, properties.getProducer().isEnableIdempotence());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return new DefaultKafkaProducerFactory<>(config);
	}
	
	/**
	 *🔹KafkaTemplate es el componente principal para enviar mensajes en Kafka.
	 *🔹Usa el ProducerFactory definido anteriormente.
	 *🔹Permite a los servicios enviar mensajes de forma de Objeto.
	 * @return
	 */
	@Bean
	public KafkaTemplate<String, BaseEventData> kafkaTemplate(ProducerFactory<String, BaseEventData> factory) {
		return new KafkaTemplate<>(factory);
	}
}

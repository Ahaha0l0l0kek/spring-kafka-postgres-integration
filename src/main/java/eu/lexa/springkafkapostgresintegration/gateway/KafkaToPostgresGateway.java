package eu.lexa.springkafkapostgresintegration.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerProperties;

public class KafkaToPostgresGateway {
    @Bean
    public IntegrationFlow flow(ConsumerFactory<String, String> cf) {
        return IntegrationFlow.from(Kafka.inboundChannelAdapter(cf, new ConsumerProperties("ilya")),
                        e -> e.poller(Pollers.fixedDelay(5000)))
                .handle(System.out::println)
                .handle(fileWriter())
                .get();
    }
}

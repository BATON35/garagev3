package com.konrad.garagev3.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

  //  @Scheduled(fixedRate = 1000)
    public void send(){
        kafkaTemplate.send("test", "to jest wiadomość");
    }
}

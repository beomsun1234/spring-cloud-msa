package com.bs.msaboardservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "reply-blog-topic")
    public void updateQty(String kafkaMessage){ //토픽에서 메세지 가져옴
        log.info("Kafka Message: ->" + kafkaMessage);

    }

}

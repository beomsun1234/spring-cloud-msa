package com.bs.msareplyservice.service;

import com.bs.msareplyservice.dto.ReplyInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String send(String topic, ReplyInfo replyInfo){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(replyInfo);
            kafkaTemplate.send(topic, jsonInString);
            log.info("Kafka Producer sent data from the Board microservice: " + replyInfo);
            return "success";
        }
        catch (JsonProcessingException ex){
            log.info("에러발생={}",ex.getMessage());
            return "failure";
        }
    }
}

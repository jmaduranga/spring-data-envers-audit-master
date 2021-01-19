package com.wiley.resultcontext.service;

import com.wiley.resultcontext.dto.Progress;
import com.wiley.resultcontext.dto.ProgressMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafKaProducerService
{


    @Value("${testmodule.kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Progress message)
    {
        log.info(String.format("Message sent {}", message));
        this.kafkaTemplate.send(topicName, message);
    }


}

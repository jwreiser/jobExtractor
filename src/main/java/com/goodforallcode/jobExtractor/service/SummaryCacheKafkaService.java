package com.goodforallcode.jobExtractor.service;

import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import jakarta.annotation.Resource;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.config.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SummaryCacheKafkaService {

    private static final Logger LOG = LoggerFactory.getLogger(SummaryCacheKafkaService.class);

    @Resource(name = "summaryTemplate")
    private KafkaTemplate<String, CompanySummary> kafkaTemplate;

    @Value("${app.topic.cache.summary}")
    private String topic;


    public void sendSummaryInsert(CompanySummary data, boolean remaining) {
        System.out.println(STR."sending data \{data} to kafka using topic: \{topic} ");
        Message message = null;
        if (!remaining) {
            System.out.println("sending add summary");
            message = MessageBuilder
                    .withPayload(data)
                    .setHeader(KafkaHeaders.KEY, "summary.add")
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();
        } else {
            System.out.println("sending remaining summaries");

            message = MessageBuilder
                    .withPayload("")
                    .setHeader(KafkaHeaders.KEY, "summary.remaining")
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();
        }


        if (message != null) {
            try {
                kafkaTemplate.send(message);
            }catch(KafkaException ex){
                if(ex.getCause() instanceof ConfigException){
                    LOG.error(STR."Failed to send summary \{data} to kafka using topic: \{topic} ", data, topic, ex);//nothing we can do here
                }else{
                    throw ex;
                }
            }
        }

    }
}

package com.goodforallcode.jobExtractor.service;

import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.StringUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.config.ConfigException;

import static java.lang.StringTemplate.STR;

@Service
public class JobCacheKafkaService {

    private static final Logger LOG = LoggerFactory.getLogger(JobCacheKafkaService.class);

    @Resource(name = "jobTemplate")
    private KafkaTemplate<String, Job> kafkaTemplate;

    @Value("${app.topic.cache.job}")
    private String topic;

    public void sendJobInsert(Job data, Boolean include) {
        System.out.println(STR."sending data \{data} to kafka using topic: \{topic} ");
        if (data != null) {//data will be null if we are sending remaining jobs
            if (data.getDescription() != null && data.getCompressedDescription() == null) {
                data.setCompressedDescription(StringUtil.compressDescription(data.getDescription(), data.getTitle()));
            }
            data.setDescription(null);//DON"T SEND THIS HUGE DESCRIPTION OVER THE WIRE, WE CAN PROBABLY USE MULTIPLE unMARSHALLERS to treat the data differently  each time we convert it, but it is easier to do it this way
        }

        Message message = null;
        if (include != null) {
            if (include) {
                System.out.println("sending include job");

                message = MessageBuilder
                        .withPayload(data)
                        .setHeader(KafkaHeaders.KEY, "job.include")
                        .setHeader(KafkaHeaders.TOPIC, topic)
                        .build();
            } else {
                System.out.println("sending exclude job");

                message = MessageBuilder
                        .withPayload(data)
                        .setHeader(KafkaHeaders.KEY, "job.exclude")
                        .setHeader(KafkaHeaders.TOPIC, topic)
                        .build();
            }

        } else {
            System.out.println("sending remaining jobs");

            message = MessageBuilder
                    .withPayload("")
                    .setHeader(KafkaHeaders.KEY, "job.remaining")
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();
        }
        if (message != null) {
            try {
                kafkaTemplate.send(message);
            }
            catch (KafkaException kafkaException){
                if(kafkaException.getCause() instanceof ConfigException){
                    LOG.error("kafka config exception: {}", kafkaException.getMessage());//there is nothing we can do here
                }else {
                    try {
                        kafkaTemplate.send(message);
                    } catch (KafkaException kafkaException2) {
                        LOG.error("kafka exception: {}", kafkaException2.getMessage());//it is more important to continue with the remaining jobs than to make sure we cache each job
                    }
                }
            }
        }

    }

}

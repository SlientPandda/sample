package com.wuhao.kafka.service;/**
 *
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 *@ClassName Producer
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/15 17:14
 *@Version 1.0
 **/
public class ProducerService {

    public void  produce(){
        Properties props = new Properties();
        props.put("arg 1", "value 1");
        props.put("arg 2", "value 2");
        try (Producer<String, String> producer = new KafkaProducer<String, String>(props)){
            producer.send(new ProducerRecord<>(), )

        }
    }
}

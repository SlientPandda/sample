package com.wuhao.kafka.service.producer;/**
 *
 */

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *@ClassName CustomProducer
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/4/17 19:49
 *@Version 1.0
 **/
public class CustomProducer {
    public static void main(String[] args) {
        //0 配置
        Properties properties = new Properties();

        //连接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.192.16.223:9092");

        //指定key和value序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //使用自定分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.wuhao.kafka.service.partition.MyPartitioner");

        //生产者提高吞吐量
        // 缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // linger.ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        //acks
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        //开启事务时，需要先指定事务id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional_id_01");

        //1、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //2、发送数据
        //普通异步发送
//            kafkaProducer.send(new ProducerRecord<>("first", "bonjour" + i));
        //回调方法的异步发送
//            kafkaProducer.send(new ProducerRecord<>("first", "bonjour" + i),
//                    (recordMetadata, e) -> {
//                        System.out.println("发送到,topic:" + recordMetadata.topic() + "分区：" + recordMetadata.partition());
//                    });
        //同步发送
//            try {
//                kafkaProducer.send(new ProducerRecord<>("first", "bonjour" + i)).get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }


//            //指定分区
//            kafkaProducer.send(new ProducerRecord<>("first", 1, "", "bonjour" + i),
//                    (recordMetadata, e) -> {
//                        System.out.println("发送到, topic: " + recordMetadata.topic() + " 分区：" + recordMetadata.partition());
//                    });

        //使用自定分区器
//            kafkaProducer.send(new ProducerRecord<>("first", "bonjour" + i),
//                    (recordMetadata, e) -> {
//                        System.out.println("发送到, topic: " + recordMetadata.topic() + " 分区：" + recordMetadata.partition());
//                    });

        //开启事务，开启事务需要开启幂等性并且使用acks=-1以及手动指定唯一的事务id
        kafkaProducer.initTransactions();
        kafkaProducer.beginTransaction();
        try {
            for (int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("first", "bonjour" + i),
                        (recordMetadata, e) -> {
                            System.out.println("发送到, topic: " + recordMetadata.topic() + " 分区：" + recordMetadata.partition());
                        });
            }
            //发生异常，事务执行失败，消息提交不成功
            int i = 1 / 0;
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            kafkaProducer.abortTransaction();
        } finally {
            kafkaProducer.close();
        }


        //3、关闭资源
//        kafkaProducer.close();
    }
}

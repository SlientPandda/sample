package com.wuhao.kafka.service.partition;/**
 *
 */

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Arrays;
import java.util.Map;

/**
 *@ClassName MyPartitioner
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/4/17 20:40
 *@Version 1.0
 **/
public class MyPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String valueString = value.toString();
        int partition;
        if (valueString.contains("bon")) {
            partition = 0;
        } else {
            partition = 1;
        }
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

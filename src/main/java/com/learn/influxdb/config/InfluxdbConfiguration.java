//package com.learn.influxdb.config;
//
//import org.influxdb.dto.Point;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
//import org.springframework.data.influxdb.InfluxDBConnectionFactory;
//import org.springframework.data.influxdb.InfluxDBProperties;
//import org.springframework.data.influxdb.InfluxDBTemplate;
//import org.springframework.data.influxdb.converter.PointConverter;
//
///**
// * @author gonghe.hogan
// */
//@Configuration
//public class InfluxdbConfiguration {
//
////    @Bean
////    public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties){
////        InfluxDBProperties properties1 = new InfluxDBProperties();
////        return new InfluxDBConnectionFactory(properties);
////    }
//
//    @Bean
//    public InfluxDBConnectionFactory connectionFactory(){
//        InfluxDBProperties properties = new InfluxDBProperties();
//        properties.setDatabase("test1");
//        properties.setUrl("http://localhost:8086");
//        properties.setPassword("~");
//        properties.setUsername("user");
//        properties.setRetentionPolicy("autogen");
//        return new InfluxDBConnectionFactory(properties);
//    }
//
//    @Bean
//    public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory){
//        return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
//    }
//
//    @Bean
//    public DefaultInfluxDBTemplate defaultTemplate(final InfluxDBConnectionFactory connectionFactory){
//        return new DefaultInfluxDBTemplate(connectionFactory);
//    }
//
//}

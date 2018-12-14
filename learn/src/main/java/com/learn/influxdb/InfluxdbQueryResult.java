package com.learn.influxdb;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gonghe.hogan
 */
@Data
@Log
@NoArgsConstructor
public class InfluxdbQueryResult {
    List<Result> results;
    private String error;

    @Data
    public static class Result {
        private List<Series> series;
        private String error;
        private long statement_id;
    }

    @Data
    static class Series{
        private String name;
        private Map<String, String> tags;
        private List<String> columns;
        private List<List<String>> values;
    }

    public static void main(String[] args) {

    }
}

package com.learn.influxdb;

import lombok.extern.java.Log;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author gonghe.hogan
 */
@Log
public class Influxdbclient {

    /**
     * db必须先创建（貌似可以重复创建）； measurement如何没有就会自动创建
     */
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * curl -i -XPOST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE mydb"
     *
     * curl默认是通过get方式传参，如果是要用post方式，则需要通过--data或者-d方式来传递数据，如下所示
     * curl -u username --data "param1=value1&param2=value" https://api.github.com
     * 但是如果传递过去的数据中存在特殊字符，则需要将特殊字符转义再传给服务器。例如value值值包括空格，则需要将空格转换为%20.如下
     *curl -d "value%201" http://hostname.com
     * 这样比较麻烦，新版本curl中提供了--data-urlencode，他会自动进行转义
     * curl --data-urlencode "value 1" http://hostname.com
     *
     * 除了使用GET和POST协议外，还可以通过 -X 选项指定其它协议，如：
     * curl -I -X DELETE https://api.github.cim
     *
     * 上传文件
     * curl --form "fileupload=@filename.txt" http://hostname/resource
     *
     *
     * 默认Content-type为application/x-www-form-urlencoded
     *
     *
     * --data-binary <key=value>
     * HTTP POST请求中的数据为纯二进制数据
     *
     * ?pretty=true是为了让显示更加好看，prod环境不允许使用，会耗费网络资源
     */


    public static void createDB(String dbName) {
    /**
     * curl -i -XPOST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE mydb"
     */
        String url = "http://localhost:8086/query?epoch=ms";

        MultiValueMap<String, String> postParameter = new LinkedMultiValueMap<>();
        postParameter.add("q", "CREATE DATABASE " + dbName);

        restTemplate.postForEntity(url, postParameter, Object.class).getBody();
    }

    public static void showDbs() {
        String url = "http://localhost:8086/query";

        MultiValueMap<String, String> postParameter = new LinkedMultiValueMap<>();
        postParameter.add("q", "show databases");

        final InfluxdbQueryResult body = restTemplate.postForEntity(url, postParameter, InfluxdbQueryResult.class).getBody();
        log.info(body.toString());
    }

    public static void showMeasurements() {
//        String url = "http://localhost:8086/query";

//        MultiValueMap<String, String> postParameter = new LinkedMultiValueMap<>();
//        postParameter.add("q", "show measurements");

        String url = "http://localhost:8086/query?pretty=true&q=show measurements &db=cgh";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void showSeries() {
        String url = "http://localhost:8086/query?pretty=true&q=show series from \"switch.cpu\" where source_id='58' &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void query() {
        /**
         * curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"
         *
         */

//        String url = "http://localhost:8086/query?pretty=true&q=select water_level,time from \"h2o_feet\" &db=swy";
        String url = "http://localhost:18086/query?pretty=true&q=select host,max(cpu_utilization) as cpu_utilization from \"switch.cpu\"  group by time(1m),host limit 100 &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }


    public static void groupbyQuery() {


//        String url = "http://localhost:8086/query?pretty=true&q=select water_level,time from \"h2o_feet\" &db=swy";
        String url = "http://localhost:8086/query?pretty=true&q=select last(host),max(errorIndex),max(cpu_utilization) as cpu_utilization from \"switch.cpu\"  group by time(1m),gims_source_id limit 100 &db=swy";
//        String url = "http://localhost:8086/query?pretty=true&q=select last(host),max(errorIndex),max(cpu_utilization) as cpu_utilization from \"switch.cpu\"  group by time(1m) limit 10 &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }


    public static void multiSeriesQuery() {

        String url = "http://localhost:8086/query?pretty=true&q= select water_level,location from \"h2o_feet\" group by location &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void multiuery() {

        String url = "http://localhost:8086/query?pretty=true&q= select *,max(switch2.output_bytes_delta) from gims_test group by fields.source_id,time(1d) &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void queryTsdbMeasurementFields() {
        /**
         * curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"
         *
         */

        String url = "http://localhost:8086/query?pretty=true&q=select gims_source,host,cpu_utilization from \"switch.cpu\" group by gims_source,host &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void queryTsdbMeasurementTags() {
        /**
         * curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"
         *
         */

        String url = "http://localhost:8086/query?pretty=true&q=show tag keys from \"switch.power\" &db=swy";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void queryTransofrm() {

        String url = "http://localhost:8086/query?pretty=true&q=select * from transform &db=test3";
        RestTemplate restTemplate = new RestTemplate();
        final InfluxdbQueryResult influxdbResult = restTemplate.getForEntity(url, InfluxdbQueryResult.class).getBody();
        log.info(influxdbResult.toString());
    }

    public static void insert() {

        /**
         * curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server01,region=us-west value=0.64 1434055562000000000'
         *
         * github上面的是
         * curl -XPOST "http://localhost:8086/write?db=mydb" -d 'cpu,host=server01,region=uswest load=42 1434055562000000000'
         *
         * 注意：
         * 1）--data-binary 表示后面的数据是纯二进制数
         * 2)cpu_load_short代表table名称。
         * 3）cpu_load_short,host=server01,region=us-west后面的空格用来隔离tag和field，空格前面的是tag，空格后面的gield
         * 4）1434055562000000000前面的空格用来隔离timestamp，可以不加，则为服务器时间；
         * 5）一行数据中，可以没有tag，必须要有field（因此我猜测如果逗号内只填一个数据的话，会认为是没有tag，时间为服务器时间）
         */

//        String url = "http://localhost:8086/write?db=test5&precision=ms";
        String url = "http://localhost:8086/write?db=swy";
//        String pointValue = "cpu_load_short,host=server01,region=us-west value=0.64 1434055562000000000";

//        ,switch2.output_error_message='abc'
//        2018-10-10T16:28:54.876
        String pointValue = "same_time," +
                "fields.project_id=7788,fields.source=test_switch_data_1009,fields.source_id=88,finish_time=2018-10-10\\ 16:28:54.876Z" +
                " switch2.output_bytes_delta=33,cpu_load=33,load1=33" +
                " 1539585734000000000";

        /**
         * https://jasper-zhang1.gitbooks.io/influxdb/content/Write_protocols/line_protocol.html
         * url中的特殊字符需要用反斜杠\来转义。
         */
        final Object postForEntity = restTemplate.postForEntity(url, pointValue, Object.class);
        log.info(postForEntity.toString());
    }

    public static void insertBatch() {

        /**
         * curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server02 value=0.67
         * cpu_load_short,host=server02,region=us-west value=0.55 1422568543702900257
         * cpu_load_short,direction=in,host=server01,region=us-west value=2.0 1422568543702900257'
         *
         *
         * 批量插入的注意：
         * 1）每条记录之间用换行隔开（故实际我们需要把每一行记录用换行符隔开，并拼接为一个字符串来处理）
         * 2）每条数据的字段数可以不同(tag和field都可以不同，不同的就是新增字段，旧的数据此处填空)
         * 3）开头的表名也可以是不同的
         * 4）如果后续的记录是完全相同的（time、tag、field都相同），那么只保留最后的那一条，等于是time是主键，被覆盖了。
         */

        String url = "http://localhost:8086/write?db=test3";
        String pointValues = "cpu_load_short,host=server02 num=0.67"
        + "\ncpu_load_short,host=server02,region=us-west value=0.55 1422568543702900257"
        + "\ncpu_load_short,direction=in,host=server01,region=us-west value=2.0 1422568543702900257";
        final Object postForEntity = restTemplate.postForEntity(url, pointValues, Object.class);
        log.info(postForEntity.toString());
    }

    public static void main(String[] args) {
//        createDB("test5");
//        insert();
//        query();

        groupbyQuery();
//        log.info("batch insert");

//        queryTransofrm();
//        insertBatch();
//        query();
//        multiSeriesQuery();
//        showDbs();

//        showSeries();

//        showMeasurements();

//        queryTsdbMeasurementTags();
//
//        queryTsdbMeasurementFields();

    }




}

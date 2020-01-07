package com.jast.graph.example;

import com.baidu.hugegraph.driver.GremlinManager;
import com.baidu.hugegraph.driver.HugeClient;
import com.baidu.hugegraph.driver.SchemaManager;
import com.baidu.hugegraph.structure.gremlin.ResultSet;

public class HugeGraphClientTest {

    private String hugeHost;
    private int hugePort;
    private String hugeDb;
    private HugeClient hugeClient;
    private GremlinManager gremlin;
    private SchemaManager schemaManager;
    public HugeGraphClientTest(String host, int port, String db) {
        this.hugeHost = host;
        this.hugePort = port;
        this.hugeDb = db;
    }


    public void init() {
        String hugeHostAndPort = "http://"+this.hugeHost+":"+this.hugePort;
        if (this.hugeClient != null) {
            System.out.println("client already exist.");
        }
        else {
            System.out.println("initializing hugeClient...");
            this.hugeClient = new HugeClient(hugeHostAndPort, this.hugeDb);
            this.gremlin = hugeClient.gremlin();
            this.schemaManager = hugeClient.schema();

        }


    }

    public void close() {
        this.hugeClient.close();
        System.out.println("hugeClient closed.");
    }

    public ResultSet gremlinExecute(String query) {
        System.out.println("script: " + query);
        ResultSet resultSet =  gremlin.gremlin(query).execute();
        Printer.printList(resultSet.data());
        return resultSet;
    }

    public void showSchema() {
        Printer.printSchema(schemaManager);
    }

}








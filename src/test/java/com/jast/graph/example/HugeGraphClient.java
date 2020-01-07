package com.jast.graph.example;

import com.baidu.hugegraph.driver.GraphManager;
import com.baidu.hugegraph.driver.GremlinManager;
import com.baidu.hugegraph.driver.HugeClient;
import com.baidu.hugegraph.driver.SchemaManager;

public class HugeGraphClient {

	static HugeClient hugeClient = new HugeClient("http://192.168.2.116:8080", "hugegraph");
	static GraphManager graph = hugeClient.graph();
	static GremlinManager gremlin = hugeClient.gremlin();
	static SchemaManager schema = hugeClient.schema();

}








package com.jast.graph.example.schema;

import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.VertexLabel;
import org.janusgraph.core.schema.JanusGraphManagement;

public class JanusGraphSchema {

	public JanusGraph janusGraph ;
	public JanusGraphManagement mgmt ;
	private String config = "config/server/janusgraph-cassandra-es.properties";

	private JanusGraphSchema(String config) {
		this.config = config;
	}

	private JanusGraphSchema() {

	}

	public JanusGraphManagement createMgmt() {
		janusGraph = JanusGraphFactory.open(config);
		mgmt = janusGraph.openManagement();
		return mgmt;
	}


	public static JanusGraphSchema getInstance(String config) {
		return new JanusGraphSchema(config);
	}

	public static JanusGraphSchema getInstance() {
		return new JanusGraphSchema();
	}

}

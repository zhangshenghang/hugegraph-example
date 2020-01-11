package com.jast.graph.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.baidu.hugegraph.structure.constant.T;
import com.baidu.hugegraph.structure.graph.Edge;
import com.baidu.hugegraph.structure.graph.Path;
import com.baidu.hugegraph.structure.graph.Vertex;
import com.baidu.hugegraph.structure.gremlin.Result;
import com.baidu.hugegraph.structure.gremlin.ResultSet;

public class Test extends HugeGraphClient{

	public static void main(String[] args) {

//				schema.getIndexLabels().forEach(x->schema.removeIndexLabel(x.name()));
//				schema.getEdgeLabels().forEach(x->schema.removeEdgeLabel(x.name()));
//				schema.getVertexLabels().forEach(x->schema.removeVertexLabel(x.name()));
//				schema.getPropertyKeys().forEach(x->schema.removePropertyKey(x.name()));
		
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article")
		.properties("name", "uid", "city","mid","url")
		//		.primaryKeys("name")
		.useCustomizeStringId()
		.ifNotExist()
		.create();

		schema.indexLabel("articleByurl")
		.onV("article")
		.by("url")
		.secondary()
		.ifNotExist()
		.create();

		schema.edgeLabel("forward")
		.sourceLabel("article")
		.targetLabel("article")
		.properties("date")
		.ifNotExist()
		.create();


		schema.indexLabel("forwardBydate")
		.onE("forward")
		.by("date")
		.secondary()
		.ifNotExist()
		.create();
		//System.out.println(sb);
		Map<Integer,String> map = new HashMap<Integer, String>();
		for(int i =1;i<20;i++) {
			String name = "";
			for(int j=0;j<new Random().nextInt(5)+2;j++) {
				name+=getRandomChar();
			}
			long uid = new Random().nextLong();
			String uuid = UUID.randomUUID().toString();
			Vertex addVertex = graph.addVertex(T.label, "article",T.id,uuid, "mid", uuid, "name", name, "uid", uid,"city","未知","url","https://weibo.com/2664293327/IodUhh2bV");
			map.put(i, uuid);
			int jj = new Random().nextInt(i)+1;
			System.out.println(map.size()+" " + jj+" " + map.get(jj));
			addVertex.addEdge("forward", graph.getVertex(map.get(jj)), "date", "20160110");
		}
//		List<Edge> edges = new LinkedList<>();
//		for(int i =1;i<1000;i++) {
//			String a = map.get(new Random().nextInt(999)+1);
//			String b = map.get(new Random().nextInt(999)+1);
//			System.out.println("1:"+a);
//			System.out.println("2:"+b);
//			Edge property = new Edge("forward").source(graph.getVertex(a)).target(graph.getVertex(b))
//            .property("date", "20170324");
//			edges.add(property);
//			if(edges.size()==500) {
//				edges = graph.addEdges(edges, false);
//				System.out.println("size:"+edges.size());
//				edges = new LinkedList<>();
//			}
//		} 
		System.exit(0);

	}

	public static String getRandomChar() {
		return String.valueOf((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
	}

	public void singleExample() {
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article")
		.properties("name", "uid", "city","mid","url")
		//		.primaryKeys("name")
		.useCustomizeStringId()
		.ifNotExist()
		.create();

		schema.indexLabel("articleByurl")
		.onV("article")
		.by("url")
		.secondary()
		.ifNotExist()
		.create();

		schema.edgeLabel("forward")
		.sourceLabel("article")
		.targetLabel("article")
		.properties("date")
		.ifNotExist()
		.create();


		schema.indexLabel("forwardBydate")
		.onE("forward")
		.by("date")
		.secondary()
		.ifNotExist()
		.create();

		Vertex article1 = graph.addVertex(T.label, "article", T.id,"6177764748mid","mid", "6177764748mid", "name", "语文星光", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
		Vertex article2 = graph.addVertex(T.label, "article",T.id,"6430029562", "mid", "6430029562", "name", "ling001972", "uid", 6430029562l,"city","未知","url","https://weibo.com/6430029562/IodTtA4ZT");
		Vertex article3 = graph.addVertex(T.label, "article",T.id,"6051786224", "mid", "6051786224", "name", "山中野猫m", "uid", 6051786224l,"city","未知","url","https://weibo.com/6051786224/IodTtktKX?type=comment");
		Vertex article4 = graph.addVertex(T.label, "article",T.id,"7367894775", "mid", "7367894775", "name", "县红i能品面_eQ", "uid", 7367894775l,"city","未知","url","https://weibo.com/7367894775/IodTrfrop");
		Vertex article5 = graph.addVertex(T.label, "article",T.id,"7367894771", "mid", "7367894771", "name", "日长k叫门伟_", "uid", 7367894771l,"city","未知","url","https://weibo.com/7367894771/IodTrf9Xe");
		Vertex article6 = graph.addVertex(T.label, "article",T.id,"6384977836", "mid", "6384977836", "name", "用户6384977836", "uid", 6384977836l,"city","未知","url","https://weibo.com/6384977836/IodUloAdw");
		Vertex article7 = graph.addVertex(T.label, "article",T.id,"7361237622", "mid", "7361237622", "name", "唱悲殇乄吕LQQ", "uid", 7361237622l,"city","未知","url","https://weibo.com/7361237622/IodUl6V8j");
		Vertex article8 = graph.addVertex(T.label, "article",T.id,"3964098185", "mid", "3964098185", "name", "我还是觉得我的名字不够洋气", "uid", 3964098185l,"city","未知","url","https://weibo.com/3964098185/IodUjkf6N");
		Vertex article9 = graph.addVertex(T.label, "article",T.id,"7005136440", "mid", "7005136440", "name", "不冫令", "uid", 7005136440l,"city","未知","url","https://weibo.com/7005136440/IodUhyI69");
		Vertex article10 = graph.addVertex(T.label, "article",T.id,"2664293327", "mid", "2664293327", "name", "无敌风火轮", "uid", 2664293327l,"city","未知","url","https://weibo.com/2664293327/IodUhh2bV");

		article2.addEdge("forward",article1, "date", "20160110");
		article3.addEdge("forward",article1, "date", "20160110");
		article4.addEdge("forward",article2, "date", "20160110");
		article5.addEdge("forward",article2, "date", "20160110");
		article6.addEdge("forward",article2, "date", "20160110");
		article7.addEdge("forward",article6, "date", "20160110");
		article8.addEdge("forward",article5, "date", "20160110");
		article9.addEdge("forward",article5, "date", "20160110");
		article10.addEdge("forward",article9, "date", "20160110");


		ResultSet resultSet = gremlin.gremlin("g.V().outE().path()").execute();
		Iterator<Result> results = resultSet.iterator();
		results.forEachRemaining(result -> {
			System.out.println(result.getObject().getClass());
			Object object = result.getObject();
			if (object instanceof Vertex) {
				System.out.println(((Vertex) object).id());
			} else if (object instanceof Edge) {
				System.out.println(((Edge) object).id());
			} else if (object instanceof Path) {
				List<Object> elements = ((Path) object).objects();
				elements.forEach(element -> {
					System.out.println(element.getClass());
					System.out.println(element);
				});
			} else {
				System.out.println(object);
			}
		});
	}
}

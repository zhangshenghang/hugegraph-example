package com.jast.graph.example;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.baidu.hugegraph.structure.constant.T;
import com.baidu.hugegraph.structure.graph.Edge;
import com.baidu.hugegraph.structure.graph.Path;
import com.baidu.hugegraph.structure.graph.Vertex;
import com.baidu.hugegraph.structure.gremlin.Result;
import com.baidu.hugegraph.structure.gremlin.ResultSet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleArticleExample extends HugeGraphClient{
	
	/**
	 * TODO 多个图创建
	 * TODO 关系推断
	 * TODO hbase结合
	 * TODO 分布式部署
	 */
	
	/**
	 * 数据添加demo 
	 * @return void
	 */
	@Test
	public void singleExample() {
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article")
		.properties("name", "uid", "city","mid","url")
		.primaryKeys("name")
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

		Vertex article1 = graph.addVertex(T.label, "article", "mid", "6177764748mid", "name", "语文星光", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
		Vertex article2 = graph.addVertex(T.label, "article", "mid", "6430029562", "name", "ling001972", "uid", 6430029562l,"city","未知","url","https://weibo.com/6430029562/IodTtA4ZT");
		Vertex article3 = graph.addVertex(T.label, "article", "mid", "6051786224", "name", "山中野猫m", "uid", 6051786224l,"city","未知","url","https://weibo.com/6051786224/IodTtktKX?type=comment");
		Vertex article4 = graph.addVertex(T.label, "article", "mid", "7367894775", "name", "县红i能品面_eQ", "uid", 7367894775l,"city","未知","url","https://weibo.com/7367894775/IodTrfrop");
		Vertex article5 = graph.addVertex(T.label, "article", "mid", "7367894771", "name", "日长k叫门伟_", "uid", 7367894771l,"city","未知","url","https://weibo.com/7367894771/IodTrf9Xe");
		Vertex article6 = graph.addVertex(T.label, "article", "mid", "6384977836", "name", "用户6384977836", "uid", 6384977836l,"city","未知","url","https://weibo.com/6384977836/IodUloAdw");
		Vertex article7 = graph.addVertex(T.label, "article", "mid", "7361237622", "name", "唱悲殇乄吕LQQ", "uid", 7361237622l,"city","未知","url","https://weibo.com/7361237622/IodUl6V8j");
		Vertex article8 = graph.addVertex(T.label, "article", "mid", "3964098185", "name", "我还是觉得我的名字不够洋气", "uid", 3964098185l,"city","未知","url","https://weibo.com/3964098185/IodUjkf6N");
		Vertex article9 = graph.addVertex(T.label, "article", "mid", "7005136440", "name", "不冫令", "uid", 7005136440l,"city","未知","url","https://weibo.com/7005136440/IodUhyI69");
		Vertex article10 = graph.addVertex(T.label, "article", "mid", "2664293327", "name", "无敌风火轮", "uid", 2664293327l,"city","未知","url","https://weibo.com/2664293327/IodUhh2bV");

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
	
	/**
	 * 删除属性变量，全部删除需要按照顺序先删除边，再删除点
	 * @return void
	 */
	@Test
	public void delete() {
		schema.getIndexLabels().forEach(x->schema.removeIndexLabel(x.name()));
		schema.getEdgeLabels().forEach(x->schema.removeEdgeLabel(x.name()));
		schema.getVertexLabels().forEach(x->schema.removeVertexLabel(x.name()));
		schema.getPropertyKeys().forEach(x->schema.removePropertyKey(x.name()));
	}
	
	/**
	 * 删除数据
	 * @return void
	 */
	@Test
	public void deleteData() {
		//遍历边id
		graph.listEdges().forEach(x->graph.removeEdge(x.id()));
		//遍历顶点id
		graph.listVertices().forEach(x->graph.removeVertex(x.id()));
		//删除顶点
		graph.removeVertex("1:日长k叫门伟_");
		//删除边
		graph.removeEdge("S1:ling001972>1>>S1:语文星光");
		//遍历边id
		graph.listEdges().forEach(x->System.out.println(x.id()));
		//遍历顶点id
		graph.listVertices().forEach(x->System.out.println(x.id()));
	}
	
	/**
	 * 顶点、边的遍历操作
	 */
	@Test
	public void test1() {
		String id = "1:日长k叫门伟_";

		//both():查询指定顶点id的双向邻接点
		ResultSet resultSet = gremlin.gremlin("g.V().hasId(\""+id+"\").both()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");

		//both():查询指定顶点id的双向邻接点
		resultSet = gremlin.gremlin("g.V(\""+id+"\").both()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");

		//bothE():查询指定顶点id的双向邻接边
		resultSet = gremlin.gremlin("g.V(\""+id+"\").bothE()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");

		//inE():查询指定顶点id的入方向邻接边
		resultSet = gremlin.gremlin("g.V(\""+id+"\").inE()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");

		//outE():查询指定顶点id的出方向邻接边
		resultSet = gremlin.gremlin("g.V(\""+id+"\").outE()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");

		//in():查询指定顶点id的入方向邻接点
		resultSet = gremlin.gremlin("g.V(\""+id+"\").in()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");

		//out():查询指定顶点id的出方向邻接点
		resultSet = gremlin.gremlin("g.V(\""+id+"\").out()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");
		
		//outV():查询指定边的出顶点，只有边可以调用该方法，点无法调用
		//inE().outV()等价于in()
		//outE().outV()等于本身顶点
		resultSet = gremlin.gremlin("g.V(\""+id+"\").outE().outV()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");
		
		//inV():查询指定边的入顶点，只有边可以调用该方法，点无法调用
		//outE().inV()等价于out()
		//outE().outV()等价于本身顶点
		resultSet = gremlin.gremlin("g.V(\""+id+"\").outE().inV()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");
		
		//bothV():查询指定边的双向顶点，只有边可以调用该方法，点无法调用
		//bothV()会把源顶点也一起返回，因此只要源顶点有多少条出边，结果集中就会出现多少次源顶点
		resultSet = gremlin.gremlin("g.V(\""+id+"\").outE().bothV()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");
		
		//otherV():查询指定边的出顶点，只有边可以调用该方法，点无法调用
		//outE().otherV()等价于out()，inE().otherV()等价于in()
		resultSet = gremlin.gremlin("g.V(\""+id+"\").outE().otherV()").execute();
		Printer.printList(resultSet.data());
		System.out.println("\n");
	}

	/**
	 * 创建顶点，然后 判断顶点是否存在，如果存在则创建边，如果不存在不作处理
	 */
	@Test
	public void test2() {
		String id = "1:语文星光1";
		Vertex addVertex = graph.addVertex(T.label, "article", "mid", "6177764748mid", "name", "单独顶点", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
		boolean isEexists = Boolean.parseBoolean(gremlin.gremlin("g.V().hasId(\""+id+"\").hasNext()").execute().data().get(0).toString());
		if(isEexists) {
			log.info("{}：存在",id);
			addVertex.addEdge("forward", graph.getVertex(id), "date","202016");		 
		}else {
			log.info("{}：不存在",id);
		}
	}

	/**
	 * 判断该ID是否存在
	 */
	@Test
	public  void test3() {
		ResultSet resultSet =  gremlin.gremlin("g.V().hasId(\"1:josh\").hasNext()").execute();
		Printer.printList(resultSet.data());
	}
	
	/**
	 * 查询顶点和边
	 */
	@Test
	public  void test4() {
		ResultSet resultSet =  gremlin.gremlin("g.V().limit(10)").execute();
		Printer.printList(resultSet.data());
		resultSet =  gremlin.gremlin("g.E().limit(10)").execute();
		Printer.printList(resultSet.data());
	}
}
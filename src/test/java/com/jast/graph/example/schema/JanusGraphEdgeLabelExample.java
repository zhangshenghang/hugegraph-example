package com.jast.graph.example.schema;

import org.janusgraph.core.Cardinality;
import org.janusgraph.core.PropertyKey;
import org.janusgraph.core.VertexLabel;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.junit.Test;

public class JanusGraphEdgeLabelExample {

	JanusGraphManagement mgmt = JanusGraphSchema.getInstance().createMgmt();

	public static void main(String[] args) {
		new JanusGraphEdgeLabelExample().getVvertexLabel();
	}
	/**
	 * 查看顶点标签
	 * @return void
	 */
	@Test
	public void getVvertexLabel() {
		String vertexLabelName = "titan";
		VertexLabel vertexLabel = mgmt.getVertexLabel(vertexLabelName);
		System.out.println(vertexLabel.name());
		if(vertexLabel!=null) {
			vertexLabel.mappedProperties().forEach(pp->System.out.printf("属性：%s 数据类型为：%s 基数为：%s\n",pp,pp.dataType(),pp.cardinality()));
		}
	}

	/**
	 * 顶点标签名称修改
	 * @return void
	 */
	@Test
	public void vertexLabelChangeName() {
		String vertexLabelName = "titan";
		VertexLabel vertexLabel = mgmt.getVertexLabel(vertexLabelName);
		if(vertexLabel!=null) {
			mgmt.changeName(vertexLabel, "new_"+vertexLabelName);
			System.out.printf("顶点属性 %s 修改成 %s\n",vertexLabel.name(),"new_"+vertexLabelName);
		}
		String printVertexLabels = mgmt.printVertexLabels();
		System.out.println(printVertexLabels);
	}

	/**
	 * 查看所有顶点标签
	 * @return void
	 */
	@Test
	public void getVertexLabels() {
		Iterable<VertexLabel> vertexLabels = mgmt.getVertexLabels();
		vertexLabels.forEach(vertexLabel->vertexLabel.mappedProperties().forEach(pp->System.out.printf("属性：%s 数据类型为：%s 基数为：%s\n",pp,pp.dataType(),pp.cardinality())));

		//打印
		String printPropertyKeys = mgmt.printVertexLabels();
		System.out.println(printPropertyKeys);
	}

	/**
	 * 创建顶点标签
	 * @return void
	 */
	@Test
	public void makeVertexLabel() {
		String vertexLabelName = "vertex"+System.currentTimeMillis();
		PropertyKey birthDate = mgmt.getPropertyKey("birthDate");
		if(birthDate==null)
			birthDate = mgmt.makePropertyKey("birthDate").dataType(Long.class).cardinality(Cardinality.SINGLE).make();
		PropertyKey uid = mgmt.getPropertyKey("uid");
		if(birthDate==null)
			uid = mgmt.makePropertyKey("uid").dataType(Long.class).cardinality(Cardinality.SINGLE).make();
		VertexLabel make = mgmt.makeVertexLabel(vertexLabelName).make();
		mgmt.addProperties(make, birthDate);
		mgmt.addProperties(make, uid);
		System.out.println(make);
		getVertexLabels();
		mgmt.commit();
	}


}

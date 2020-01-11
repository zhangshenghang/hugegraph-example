package com.jast.graph.example.schema;

import org.janusgraph.core.EdgeLabel;
import org.janusgraph.core.Multiplicity;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.junit.Test;

public class JanusGraphVertexLabelExample {

	JanusGraphManagement mgmt = JanusGraphSchema.getInstance().createMgmt();

	/**
	 * 查看指定边标签
	 * @return void
	 */
	@Test
	public void getEdgeLabel() {
		String edgeLabelName = "titan";
		EdgeLabel edgeLabel = mgmt.getEdgeLabel(edgeLabelName);
		if(edgeLabel!=null)
			System.out.printf("边标签：%s 包含的属性:%s\n",edgeLabel.name(),edgeLabel.mappedProperties());
	}

	/**
	 * 边标签名称修改
	 * @return void
	 */
	@Test
	public void edgeLabelChangeName() {
		String edgeLabelName = "edge1";
		EdgeLabel edgeLabel = mgmt.getEdgeLabel(edgeLabelName);
		if(edgeLabel!=null) {
			mgmt.changeName(edgeLabel, "new_"+edgeLabelName);
			System.out.printf("顶点属性 %s 修改成 %s\n",edgeLabel.name(),"new_"+edgeLabelName);
		}
		System.out.println(mgmt.printEdgeLabels());
	}

	/**
	 * 查看所有边标签
	 * @return void
	 */
	@Test
	public void getEdgeLabels() {
		Iterable<EdgeLabel> relationTypes = mgmt.getRelationTypes(EdgeLabel.class);
		System.out.println("所有边标签："+relationTypes);
		//打印
		String printPropertyKeys = mgmt.printEdgeLabels();
		System.out.println(printPropertyKeys);
	}

	/**
	 * 创建边标签
	 * @return void
	 */
	@Test
	public void makeEdgeLabel() {
		String edgeLabelName = "edge1";
		//MULTI：允许一对顶点之间有任意多个相同标签的边。换句话说就是没有约束。
		//SIMPLE：允许最多一个相同标签的边。
		//MANY2ONE：该边标签只允许有一条外向的边，但对向内的边的条数没有限制。
		//ONE2MANY：该边标签只允许有一条向内的边，但对向外的边的条数没有限制。
		//ONE2ONE：该边标签只允许一条向内和 一条向外的边
		EdgeLabel follow = mgmt.makeEdgeLabel(edgeLabelName).multiplicity(Multiplicity.MULTI).make();
		System.out.println(mgmt.printEdgeLabels());
		mgmt.commit();
	}


}

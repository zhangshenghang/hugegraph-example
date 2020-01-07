package com.jast.graph.example;

import org.junit.Test;

import com.baidu.hugegraph.structure.constant.T;

/**
 * 顶点Id生成方式 
 *  
 * 默认的Id策略是AUTOMATIC，如果用户调用primaryKeys()方法并设置了正确的PrimaryKeys，则自动启用PRIMARY_KEY策略。
 *  启用PRIMARY_KEY策略后HugeGraph能根据PrimaryKeys实现数据去重。
 * @author jast
 * @date 2020年1月7日 下午2:52:03
 *
 */
public class VertexId extends HugeGraphClient{

	/**
	 * 自动生成（AUTOMATIC）：使用Snowflake算法自动生成全局唯一Id，Long类型；
	 * .primaryKeys("name") 与 useAutomaticId 不能同时设置
	 * @return void
	 */
	@Test
	public void autoMatic() {
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article1")
		.properties("name", "uid", "city","mid","url")
		.useAutomaticId()
//		.primaryKeys("name") //与 useAutomaticId 不能同时设置
		.ifNotExist()
		.create();

		graph.addVertex(T.label, "article1", "mid", "6177764748mid", "name", "语光", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
	}
	
	/**
	 * 通过VertexLabel+PrimaryKeyValues生成Id，String类型
	 * 如果用户调用primaryKeys()方法并设置了正确的PrimaryKeys，则自动启用PRIMARY_KEY策略。
	 *  启用PRIMARY_KEY策略后HugeGraph能根据PrimaryKeys实现数据去重。
	 * @return void
	 */
	@Test
	public void primaryKey() {
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article2")
		.properties("name", "uid", "city","mid","url")
		.usePrimaryKeyId()
		.primaryKeys("name") 
		.ifNotExist()
		.create();

		graph.addVertex(T.label, "article2", "mid", "6177764748mid", "name", "语光", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
	}
	
	/**
	 * 用户自定义Id，分为String和Long类型两种，需自己保证Id的唯一性；
	 * 与 .primaryKeys("name") 不能同时设置
	 * @return void
	 */
	@Test
	public void customizeString() {
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article3")
		.properties("name", "uid", "city","mid","url")
		.useCustomizeStringId()
		.ifNotExist()
		.create();

		graph.addVertex(T.label, "article3", T.id,"自定义的StringId","mid", "6177764748mid", "name", "语光1", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
	}
	
	
	/**
	 * 用户自定义Id，分为String和Long类型两种，需自己保证Id的唯一性；
	 * 与 .primaryKeys("name") 不能同时设置
	 * id 可以为long
	 * @return void
	 */
	@Test
	public void customizeNumberString() {
		schema.propertyKey("name").asText().ifNotExist().create();
		schema.propertyKey("uid").asLong().ifNotExist().create();
		schema.propertyKey("city").asText().ifNotExist().create();
		schema.propertyKey("mid").asText().ifNotExist().create();
		schema.propertyKey("url").asText().ifNotExist().create();
		schema.propertyKey("date").asText().ifNotExist().create();

		schema.vertexLabel("article5")
		.properties("name", "uid", "city","mid","url")
		.useCustomizeNumberId()
		.ifNotExist()
		.create();

		graph.addVertex(T.label, "article5", T.id,666666666666l,"mid", "6177764748mid", "name", "语光1", "uid", 6177764748l,"city","未知","url","https://weibo.com/6177764748/Io73xCtrF");
	}
}

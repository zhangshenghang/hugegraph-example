package com.jast.graph.example.schema;

import org.janusgraph.core.Cardinality;
import org.janusgraph.core.PropertyKey;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.junit.Test;

public class JanusGraphPropertyExample {

	JanusGraphManagement mgmt = JanusGraphSchema.getInstance().createMgmt();

	/**
	 * 查看属性类型
	 * @return void
	 */
	@Test
	public void getPropertyKey() {
		String propertyKeyName = "new_birthDate11";
		PropertyKey propertyKey = mgmt.getPropertyKey(propertyKeyName);
		if(propertyKey!=null) {
			System.out.printf("属性：%s 数据类型为：%s 基数为：%s\n",propertyKey,propertyKey.dataType(),propertyKey.cardinality());
		}
	}
	
	/**
	 * 属性名称修改
	 * @return void
	 */
	@Test
	public void propertyKeyChangeName() {
		String propertyKeyName = "new_birthDate11";
		PropertyKey propertyKey = mgmt.getPropertyKey(propertyKeyName);
		if(propertyKey!=null) {
			propertyKey = mgmt.getPropertyKey(propertyKeyName);
			mgmt.changeName(propertyKey, "new_"+propertyKeyName);
			System.out.printf("属性 %s 修改成 %s\n",propertyKey.name(),"new_"+propertyKeyName);
		}
	}
	
	/**
	 * 查看所有属性名称
	 * @return void
	 */
	@Test
	public void getPropertyKeys() {
		//获取对象
		Iterable<PropertyKey> relationTypes = mgmt.getRelationTypes(PropertyKey.class);
		relationTypes.forEach(propertyKey->System.out.printf("属性：%s 数据类型为：%s 基数为：%s\n",propertyKey,propertyKey.dataType(),propertyKey.cardinality()));
		//打印
		String printPropertyKeys = mgmt.printPropertyKeys();
		System.out.println(printPropertyKeys);
	}
	
	/**
	 * 创建属性<br/>
	 * 
	 * #dataType 取值<br/>
	 * 
	 * String 字符串序列<br/>
	 * Character 单个字符<br/>
	 * Boolean 布尔类型：true 或者 false<br/>
	 * Byte 字节<br/>
	 * Short 短整型<br/>
	 * Integer 整型<br/>
	 * Long 长整型<br/>
	 * Float 浮点型<br/>
	 * Double 双精度型<br/>
	 * Date 时间类型，java.util.Date 的实例<br/>
	 * GeoShape 地理的 shape 类型，如 point、circle 或者 box<br/>
	 * UUID 全局唯一标识符（java.util.UUID）<br/>
	 * <br/>
	 * #cardinality 取值<br/>
	 * SINGLE：只允许有一个单值<br/>
	 * LIST：值可以是一个列表<br/>
	 * SET：值可以有多个，但不能重复<br/>
	 * @return void
	 */
	@Test
	public void makePropertyKey() {
		String propertyKeyName = "test113"+System.currentTimeMillis();
		Class<?> dataType = Long.class;
		Cardinality cd = Cardinality.SINGLE;
		PropertyKey birthDate = mgmt.makePropertyKey(propertyKeyName).dataType(dataType).cardinality(cd).make();
		getPropertyKeys();
		mgmt.commit();
	}
	

}

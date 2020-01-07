package com.jast.graph.example;

import com.baidu.hugegraph.driver.SchemaManager;
import com.baidu.hugegraph.structure.gremlin.ResultSet;

import java.util.Iterator;
import java.util.List;

public final class Printer {

    public static<T> void printList(List<T> list) {
        for (int i = 0; i < 20; ++i) {
            if (i >= list.size()) {
                break;
            }
            System.out.println(list.get(i).toString());
        }
    }

    public static void printSchema(SchemaManager schemaManager) {
        System.out.println("====property keys====");
        schemaManager.getPropertyKeys().forEach(t -> System.out.println(t.toString()));
        System.out.println("====edge labels====");
        schemaManager.getEdgeLabels().forEach(t -> System.out.println(t.toString()));
        System.out.println("====vertex labels====");
        schemaManager.getVertexLabels().forEach(t -> System.out.println(t.toString()));
        System.out.println("====index labels====");
        schemaManager.getIndexLabels().forEach(t -> System.out.println(t.toString()));
    }
}

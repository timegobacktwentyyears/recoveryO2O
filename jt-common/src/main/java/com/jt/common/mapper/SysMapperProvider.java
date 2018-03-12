package com.jt.common.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Table;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.MapperHelper;

public class SysMapperProvider extends MapperProvider {

    public SysMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public SqlNode deleteByIDS(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
        EntityHelper.EntityColumn column = null;
        for (EntityHelper.EntityColumn entityColumn : entityColumns) {
            column = entityColumn;
            break;
        }
        
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        // 开始拼sql
        BEGIN();
        // delete from table
        DELETE_FROM(tableName(entityClass));
        // 得到sql
        String sql = SQL();
        // 静态SQL部分
        sqlNodes.add(new StaticTextSqlNode(sql + " WHERE " + column.getColumn() + " IN "));
        // 构造foreach sql
        SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{"
                + column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")", ",");
        sqlNodes.add(foreach);
        return new MixedSqlNode(sqlNodes);
    }

    
    
    /**Mybatis介绍sql的工具      
     * SqlNode:sql的存储工具
     * MappedStatement  mybatis内置对象
     * @param ms
     * @return
     * String sql = "select count(*) from XXX"
     * 
     * 问题:如何获取表名?
     * 思路:
     * 	1.获取当前执行的Mapper的路径  com.jt.manage.mapper.ItemMapper.findAll()
     *  2.截取字串     com.jt.manage.mapper.ItemMapper
     *  3.通过反射可以获取Class    ItemMapper.class
     *  4.获取ItemMapper的父级接口   SysMapper<Item>
     *  5.获取泛型的参数          Item.class
     *  6.获取@Table注解
     *  7.获取name属性值
     *  8.拼接sql   select count(*) from "name"      
     * 
     */ 
    public SqlNode findCount(MappedStatement ms){
    	//1.获取当前Mapper执行的方法全路径      com.jt.manage.mapper.ItemMapper.findAll()
    	String methodPath =  ms.getId();
    	
    	//2.获取接口的路径     com.jt.manage.mapper.ItemMapper
    	String interFacePath = methodPath.substring(0, methodPath.lastIndexOf("."));
    	
    	//3.通过反射机制 获取Class类型
    	try {
			Class<?> targetClass = Class.forName(interFacePath);
			
			//4.获取当前接口的上级
			Type[] parentTypes = targetClass.getGenericInterfaces();
			
			//5.表示获取父级Type
			Type parentType = parentTypes[0];
			
			//6.判断当前上级是否为一个泛型接口
			if(parentType instanceof ParameterizedType){
				//7.将Type类型转化为泛型类型 ,调用对应的方法
				ParameterizedType parameterizedType = (ParameterizedType) parentType;
				
				//8.获取参数的数组
				Type[] argsTypes = parameterizedType.getActualTypeArguments();
				
				//9.获取参数的Class类型
				Class<?> argsClass = (Class<?>) argsTypes[0];
				
				//10.判断当前Class类型是否有@Table注解
				if(argsClass.isAnnotationPresent(Table.class)){
					
					//11.获取table注解
					Table table = argsClass.getAnnotation(Table.class);
					
					//12.获取name属性值
					String tableName = table.name();
					
					//13.拼接sql语句
					String sql = "select count(*) from "+tableName;
					
					//14.准备sqlNode对象
					SqlNode sqlNode = new StaticTextSqlNode(sql);
					
					return sqlNode;
					
				}	
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
   
    
}

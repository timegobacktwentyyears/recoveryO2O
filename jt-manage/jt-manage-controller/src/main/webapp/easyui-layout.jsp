<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="/css/jt.css" />
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<div id="cc" class="easyui-layout" style="width:600px;height:400px;"> 

<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div> 

<div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div> 

<div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div> 

<div data-options="region:'west',title:'West',split:true" style="width:100px;"></div> 

<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div> 

</div>



</html>
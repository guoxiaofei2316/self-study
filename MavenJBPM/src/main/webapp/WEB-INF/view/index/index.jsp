<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<link href="http://localhost:8086/res/css/leftStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://localhost:8086/res/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//setMenuHeight
	$('.menu').height($(window).height()-51-27-26-5);
	$('.sidebar').height($(window).height()-51-27-26-5);
	$('.page').height($(window).height()-51-27-26-5);
	$('.page iframe').width($(window).width()-15-168);
	
	//menu on and off
	$('.btn').click(function(){
		$('.menu').toggle();
		
		if($(".menu").is(":hidden")){
			$('.page iframe').width($(window).width()-15+5);
			}else{
			$('.page iframe').width($(window).width()-15-168);
				}
		});
		
	//
	$('.subMenu a[href="#"]').click(function(){
		$(this).next('ul').toggle();
		return false;
		});
})
</script>


</head>

<body style="overflow-y:hidden">
<div id="wrap">
	<div id="header">
    <div class="logo fleft"></div>
    <!-- <div class="nav fleft">
    	<ul>
        	<div class="nav-left fleft"></div>
            <li class="first"><a href="#">我的面板</a></li>
        	<li><a href="#">设置</a></li>
            <li><a href="#">模块</a></li>
            <li><a href="#">内容</a></li>
            <li><a href="#">用户</a></li>
            <li><a href="#">扩展</a></li>
            <li><a href="#">应用</a></li>
            <div class="nav-right fleft"></div>
        </ul>
    </div> -->
    <a class="logout fright" href="login.html" style="margin-right: 30px;background-color: red;border-radius:20%;"></a>
    <div class="clear"></div>
    <div class="subnav">
    	<div class="subnavLeft fleft"></div>
        <div class="fleft"></div>
        <div class="subnavRight fright"></div>
    </div>
    </div><!--#header -->
    <div id="content">
    <div class="space"></div>
    <div class="menu fleft">
    	<ul>
        	<li class="subMenuTitle">CMS SOP</li>
            <li class="subMenu"><a href="#">任务</a>
            	<ul>
                	<li><a href="table.html" target="right">进行中..</a></li>
                </ul>
                <ul>
                	<li><a href="table.html" target="right">发起任务</a></li>
                </ul>
                <ul>
                	<li><a href="table.html" target="right">发起任务</a></li>
                </ul>
            </li>
           
            <li class="subMenu"><a href="#">部门</a>
            	<ul>
                	<li><a href="table.html" target="right">部门列表</a></li>
                    <li><a href="table.html" target="right">新增部门</a></li>
                </ul>
            </li>
            
            <li class="subMenu"><a href="#">员工</a>
           		 <ul>
                	<li><a href="table.html" target="right">员工列表</a></li>
                    <li><a href="table.html" target="right">新增员工</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="sidebar fleft"><div class="btn"></div></div>
    <div class="page">
    <iframe width="100%" scrolling="auto" height="100%" frameborder="false" allowtransparency="true" style="border: medium none;" src="main.html" id="rightMain" name="right"></iframe>
    </div>
    </div><!--#content -->
    <div class="clear"></div>
    <div id="footer">CopyRight &copy; 2012-2014 www.sj5d.com </div><!--#footer -->
   
    
</div><!--#wrap -->
</body>
</html>
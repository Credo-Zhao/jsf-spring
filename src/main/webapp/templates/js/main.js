
//菜单栏效果
 $(document).ready(function () {
	/*此代码执行后的DOM完全加载*/
	
	/*获取系统当前时间*/
	$('#down_right_timer').timer({ format: "yy\u5e74mm\u6708dd\u65e5 W HH:MM:ss" });
    
	/*改变thedefault缓动效果 - 将影响到/ slideDown方法效果基本show：*/
    $.easing.def = "easeOutBounce";
	//$.easing.def = "easeOutQuad";
            
	/*单击事件处理程序绑定到链接：*/       
	$('li.button a').click(function (e) {
		/*下拉列表中找到对应于当前部分：*/
        var dropDown = $(this).parent().next();
		
		/*关闭所有其他降下来的部分，除了目前的 */
        $('.dropdown').not(dropDown).slideUp('slow');
        dropDown.slideToggle('slow');
		
		/*防止违约事件（这将是导航到链接的地址浏览器）*/
        e.preventDefault();
    })
			
	/*模拟鼠标点击事件--> 设置选中一级菜单*/
	//
    if($("#menuTwo").val()!=null&&$("#menuTwo").val()!=""&&$("#menuOne").val()!=null&&$("#menuOne").val()!=""){
	//$("#"+$("#menuOne").val()).trigger("click");
    var url=$("#requestContextPath").val();
	$("#"+$("#menuOne").val()).css("background-image","url("+url+"/images/nav-a-1.png)");
	$("#"+$("#menuOne").val()).css("color","#FFFFFF");
	/*-> 设置选中二级菜单样式*/
	$("#"+$("#menuTwo").val()).css("background-image","url("+url+"/images/nav-a-2.png)");
    }
	
	/*页面加载时设置按钮提示框*/
	$("button").tooltip({
		left: 5,top:20
	});
	/*页面加载时设置按钮提示框*/
	$(":text").tooltip({
		left: 20,top:-10
	});
	
	$("select").tooltip({
		left: 20,top:-10
	});

});
 
 /*页面局部刷新时设置按钮提示框*/
/*function showTooltip(){
	$("button").each(function() {
		if(this.tooltipText==null){
		//if(this.title!=null && this.title.length>0 && $(this).text()!="ui-button"){
		$(this).tooltip({
			left: 5,top:20
		});}
	 });
	$(":text").each(function() {
		if(this.tooltipText==null){
			$(this).tooltip({
			left: 20,top:-10
		});}
	 });
	$("select").each(function() {
		if(this.tooltipText==null){
			$(this).tooltip({
			left: 20,top:-10
		});}
	 });

 }*/
        
//鼠标停留效果
function LiStyleOver(obj) {
	obj.style.border = "1px solid #7cb9dd";
}

 //鼠标离开效果
function LiStyleOut(obj) {
	obj.style.border = "1px solid #d5e8f6";
}
		
/*--获取网页传递的参数--*/
function request(paras){ 
	var url = location.href; 
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
	var paraObj = {} 
 	for (i=0; j=paraString[i]; i++){ 
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
    } 
	var returnValue = paraObj[paras.toLowerCase()]; 
	if(typeof(returnValue)=="undefined"){ 
    	return ""; 
    }else{ 
        return returnValue; 
    } 
}

/*--菜单栏隐藏--*/
//var arrowpic1="/images/control_left.gif";//最好用服务器的绝对路径
//var arrowpic2="/images/control_right.gif";

//--------------------- 状态初始 ----------------------
//var MENU_SWITCH=1;

//------------------ 点击时切换菜单栏状态 ---------------------
function enter_menu_ctrl()
{
	if(getCookie("MENU_SWITCH")==null){
		SetCookie("MENU_SWITCH","1");
	}
	if(getCookie("MENU_SWITCH")==0)
    {
		$("#menu_id").css("display","block");
		$("#control_id").css("left","172px");
		$("#main_id").css("left","180px");
        SetCookie("MENU_SWITCH","1");
        $("#menu_ctrl_img_left").css("display","block");
        $("#menu_ctrl_img_right").css("display","none");
		//document.getElementById("menu_ctrl_img_left").src=arrowpic1;
		//document.getElementById("menu_ctrl_img_right").src=arrowpic2;
		document.getElementById("menu_ctrl_tit").title="\u5173\u95ed\u83dc\u5355";
     }
     else
     {
		$("#menu_id").css("display","none");
		$("#control_id").css("left","0px");
		$("#main_id").css("left","8px");
       	SetCookie("MENU_SWITCH","0");
       	$("#menu_ctrl_img_left").css("display","none");
        $("#menu_ctrl_img_right").css("display","block");
       	//document.getElementById("menu_ctrl_img_left").src=arrowpic1;
		//document.getElementById("menu_ctrl_img_right").src=arrowpic2;
		document.getElementById("menu_ctrl_tit").title="\u6253\u5f00\u83dc\u5355";
     }
}

//------------------ 默认时记忆中的菜单栏状态 ---------------------
function ready_menu_ctrl()
{
	if(getCookie("MENU_SWITCH")==null){
		SetCookie("MENU_SWITCH","1");
	}
	if(getCookie("MENU_SWITCH")==1)
    {
		$("#menu_id").css("display","block");
 		$("#control_id").css("left","172px");
 		$("#main_id").css("left","180px");
         $("#menu_ctrl_img_left").css("display","block");
         $("#menu_ctrl_img_right").css("display","none");
 		//document.getElementById("menu_ctrl_img_left").src=arrowpic1;
 		//document.getElementById("menu_ctrl_img_right").src=arrowpic2;
 		document.getElementById("menu_ctrl_tit").title="\u5173\u95ed\u83dc\u5355";
     }
     else
     {
    	$("#menu_id").css("display","none");
 		$("#control_id").css("left","0px");
 		$("#main_id").css("left","8px");
 		$("#menu_ctrl_img_left").css("display","none");
        $("#menu_ctrl_img_right").css("display","block");
        //document.getElementById("menu_ctrl_img_left").src=arrowpic1;
 		//document.getElementById("menu_ctrl_img_right").src=arrowpic2;
 		document.getElementById("menu_ctrl_tit").title="\u6253\u5f00\u83dc\u5355";
     }
}

/*
 * 关闭验证消息框
 * id 消息ID
 */
function closeMsg(id){
	$("#"+id).css("z-index","0");
	$("#"+id).css("position","absolute");
	//$("#"+id).css("float","right");
	$("#"+id).css("left","400px");
	$("#"+id).css("top","-10px");
	 setTimeout(function(){
		 $("#"+id).hide('slow');
		 $("#"+id).css("display","none");
		 }, 3000);
	//var intervalID = window.setTimeout($("#"+id).css("display","none"),5000);
	//var intervalID2 = window.setTimeout($("#"+id).css("display","block"),5000);
}

//写cookies函数
function SetCookie(name, value)//两个参数，一个是cookie的名子，一个是值
{
    delCookie(name);
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)//取cookies函数        
{
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]); return "";

}
function delCookie(name)//删除cookie
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

//------------------------------------------------------------

//源系统物料代码自动根据源代码赋值
function updateByMaterialCode(vars) {
	var sysCode = document.getElementById("material_addOriginalVendorNo");
	sysCode.value=vars.value;
}

//关闭添加panel
function handleAddComplete(args) {
	if(args.validationFailed){
  } else {
  	addPanel.hide();
  }
}
//关闭修改panel
function handleUpdateComplete(args) {
	if(args.validationFailed){
  } else {
  	updatePanel.hide();
  }
}
/*
* 弹出层验证事件
* panel 需要关闭的层
* args
*/
function handleComplete(panel,args){
	 if(args.validationFailed){
	    } else {
	    	panel.hide();
	    }
}

/**
* 弹出非Ajax数据提交等待层
* 用法：<h:form id="packingMaterialInputForm" prependId="false" onsubmit="statusComplete();">
*/
function statusComplete(){
	 $("#submitDiv").css("display","");
}


$(document).ready(function() {
	obj.sessionTime();
	removeFirstBtnCSS();
	addStyleToj_idt20_label();
});
var obj = {};
var num = 1500;
var t;
obj.sessionTime = function() {
	if (--num == 0) {
		window.location.href = "/base";
	}
	/*$("#currentUserBtn").html(num); */
	t = setTimeout("obj.sessionTime()", 1000);
};
/* ajax时重置时间! */
function showTooltip() {
	if (this.tooltipText == null) {
		clearTimeout(t);
		obj.sessionTime();
		num = 1500;
	}
}
/*消除掉选择样式控件的圆滑效果*/
function removeFirstBtnCSS() {
	$("#currentUserBtn").removeClass("ui-state-disabled");
}

function addStyleToj_idt20_label(){
	$("#j_idt20_label").addClass("topBtnNoRoundness");
	$("#j_idt20_label").height(19);
}
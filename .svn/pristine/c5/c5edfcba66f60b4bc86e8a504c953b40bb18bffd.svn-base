$(function(){
	$("#top").load("header.html",function(){
		if ( $.browser.msie && $.browser.version=='6.0' ){
			 DD_belatedPNG.fix('#logoss');
		}
		var urlParam = getUrlParam(), publicTitle = cmsinfoHTML = "",navList=[];
		if(typeof urlParam.type !="undefined" ){
			switch(urlParam.type){
			case 'aboutus':
				$(".nav a:eq(1)").addClass("selected").siblings().removeClass('selected');
				publicTitle = "公司简介";		
				break;
			case 'news' :
				$(".nav a:eq(2)").addClass("selected").siblings().removeClass('selected');
				publicTitle = "水务新闻";
				navList.push('<li class="cur" link-type="gsxw"><a href="news.html?type=news&category=gsxw">公司新闻</a></li>');
				navList.push('<li link-type="hyzx"><a href="news.html?type=news&category=hyzx">行业资讯</a></li>');
				navList.push('<li link-type="mtzx"><a href="news.html?type=news&category=mtzx">媒体资讯</a></li>');          
          		navList.push('<li link-type="swzs"><a href="news.html?type=news&category=swzs">水务知识</a></li>');
				$('.nav-change').html(navList.join(''));
				$('.cont_title_posi').find('.articleTile').html('公司新闻');
				type =2;
				break;
			case 'law' :
				$(".nav a:eq(3)").addClass("selected").siblings().removeClass('selected');
				publicTitle = "政策法规";
				navList.push('<li class="cur" link-type="gjfg"><a href="news.html?type=law&category=gjfg">国家行业法律法规</a></li>');
				navList.push('<li link-type="hnfg"><a href="news.html?type=law&category=hnfg">湖南省法律法规</a></li>');
				navList.push('<li link-type="csfg"><a href="news.html?type=law&category=csfg">长沙市法律法规</a></li>'); 
				$('.nav-change').html(navList.join(''));
				$('.cont_title_posi').find('.articleTile').html('国家行业法律法规');
				type=6;
				break;
			case 'server' :
				$(".nav a:eq(4)").addClass("selected").siblings().removeClass('selected');
				publicTitle = "客户服务";
				break;
			case 'szgk' :
				$(".nav a:eq(5)").addClass("selected").siblings().removeClass('selected');
				publicTitle = "水质公开"; 
				navList.push('<li link-type="szgk" class="cur"><a href="news.html?type=szgk&category=szgk">水质公开</a></li>')
				$('.nav-change').html(navList.join(''));
				$('.cont_title_posi').html('<a href="index.html">首页</a> > <a href="news.html?type=szgk&category=szgk" class="publicTitle">水质公开</a>');			
				break;
			}
		}else{
			$(".nav .selected").removeClass("selected");
			$(".nav a:first").addClass("selected");
		}
		
		if(publicTitle){
			$(".publicTitle").html(publicTitle);
			switch( publicTitle ){
				case '水务新闻' :
					$(".cont_title_posi .publicTitle").attr("href","news.html?type=news&category=gsxw");
					break;
				case '政策法规' :
					$(".cont_title_posi .publicTitle").attr("href","news.html?type=law&category=gjfg");
					break;
				case '水质公开' :
					$(".cont_title_posi .publicTitle").attr("href","news.html?type=szgk&category=szgk");
					break;
			}
		}
		
		
		if(cmsinfoHTML){
			
			$(".right #law_content").html(cmsinfoHTML);
		}
		
		if(typeof lawgetInfo == "function"){
			lawgetInfo( urlParam );
		}
		
	});
	$("#footer").load("footers.html",function(){
		if ( $.browser.msie && $.browser.version=='6.0' ){
			 DD_belatedPNG.fix('#footerloge');
		}
		var seth = function(){
		
			var foter = $("#footer").offset();
			var wh = $(window).height();
			
			if(wh>foter.top){
				
				$("#footer").offset({ top: foter.top+(wh-foter.top), left:foter.left })
			}
		};
		seth();
	});
	
});
function getUrlParam(){
	   var h = [];
	   str = window.location.href, arr = str.substr(str.indexOf("?") + 1, str.length).split("&");
	   for(var i = 0, g = arr.length; i < g; i++){
		   var f = [];
		   f = arr[i].split("=");
		   h[f[0]] = f[1];
	   }
	   return h;
}
template.helper('checkPublishText', function (str,len){
   var str   =    $.trim(str);
   var myLen = 0;
   var i     = 0;
   for(;(i<str.length)&&(myLen<=len*2);i++){
       if(str.charCodeAt(i)>0&&str.charCodeAt(i)<128) {
               myLen++;
       } else {
               myLen+=2;
       }
   }
   if(myLen>len*2){
          return  str.substring(0,i-1)+"…";
   }else{
	   return str
   }
});
template.helper('delHtml',function(str){
	return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
})
function Datescroller(type,fn,isShowDay){
//格式化
var dateOrder,dateFormat;
if(!isShowDay){
	dateOrder  = 'yymm';
	dateFormat = 'yy-mm';
} else {
	dateOrder  = 'yymmdd';
	dateFormat = 'yy-mm-dd';
}

var opt = {
	preset: type, //类型(date|time|datetime|select|select-opt)
	theme: 'default', //皮肤样式(default|android|android-holo|android-holo light|ios|ios7|jqm|sense-ui|wp|wp light)
	display: 'modal', //显示方式(modal|inline|bubble|top|bottom)
	mode: 'scroller', //日期选择模式(scroller|clickpick|mixed)
	dateOrder:dateOrder,
	dateFormat:dateFormat,// 日期格式
	setText: '确定', //确认按钮名称
	cancelText: '取消',//取消按钮名籍我
	timeFormat: 'HH：ii', //面板中时间排列格式
	startYear:2016,
	onBeforeShow: function (inst) { 
		if(!isShowDay){
			inst.settings.wheels[0].length>2?inst.settings.wheels[0].pop():null; //弹掉“日”滚轮
			}
		},
		onSelect:fn,
	}
		return opt;
}







$(function(){
	var urlParam = getUrlParam(), publicTitle = cmsinfoHTML = "",navList=[];
	//alert(urlParam);
	if(typeof urlParam.type !="undefined" ){
		switch(urlParam.type){
			case 'gswx':
				$('.header-title').html('公司新闻');
			break;
			case 'hyzx':
				$('.header-title').html('行业资讯');
			break;
			case 'mtzx':
				$('.header-title').html('媒体资讯');
			break;
			case 'swzs':
				$('.header-title').html('水务知识');
			break;
			case 'szgk':
				$('.header-title').html('水质公开');
			break;
			case 'zcfg':
				$('.header-title').html('政策法规');
			break;
			case 'ysjg':
				$('.header-title').html('用水价格');
				$('.service-cont').html('<p class="empty">该内容正文还在收集编辑中，亲，等一等，期待产生美</p>');
			break;
			case 'lxwm':
				$('.header-title').html('联系我们');
				$('.service-cont').html('<p class="empty">该内容正文还在收集编辑中，亲，等一等，期待产生美</p>');
			break;
			case 'ywzn':
				$('.header-title').html('业务指南');
				$('.service-cont').html('<p class="empty">该内容正文还在收集编辑中，亲，等一等，期待产生美</p>');
			break;
			case 'yywd':
				$('.header-title').html('营业网点');
				$('.service-cont').html('<p class="empty">该内容正文还在收集编辑中，亲，等一等，期待产生美</p>');
			break;
			case 'yhxz':
				$('.header-title').html('用户须知');
				$('.service-cont').html('<p class="empty">该内容正文还在收集编辑中，亲，等一等，期待产生美</p>');
			break;
		}
	}	

	var sWidth = $(".slider").width(); //获取焦点图的宽度（显示面积）
    $(".slider ul li").css("width", sWidth);
    var len = $(".slider ul li").length; //获取焦点图个数
    var index = 0;
    var picTimer;

    //为小按钮添加鼠标滑入事件，以显示相应的内容
    $(".slider .thumb_btn span").css("opacity", 0.7).click(function () {
        index = $(".slider .thumb_btn span").index(this);
        showPics(index);
    }).eq(0).trigger("click");

    //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
    $(".slider ul").css("width", sWidth * (len));

    //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
    $(".slider").hover(function () {
        clearInterval(picTimer);
    }, function () {
        picTimer = setInterval(function () {
            showPics(index);
            index++;
            if (index == len) { index = 0; }
        }, 3000); //此4000代表自动播放的间隔，单位：毫秒
    }).trigger("mouseleave");

    //显示图片函数，根据接收的index值显示相应的内容
    function showPics(index) { //普通切换
        var nowLeft = -(index * sWidth); //根据index值计算ul元素的left值
        $(".slider ul").stop(true, false).animate({ "left": nowLeft }, 1000); //通过animate()调整ul元素滚动到计算出的position
        //$("#focus .thumb_btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
        $(".slider .thumb_btn span").stop(true, false).animate({ "opacity": "0.7" }, 1000).eq(index).stop(true, false).animate({ "opacity": "1" }, 1000); //为当前的按钮切换到选中的效果
    }
    if(typeof lawgetInfo == "function"){
		lawgetInfo( urlParam );
	}
})
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




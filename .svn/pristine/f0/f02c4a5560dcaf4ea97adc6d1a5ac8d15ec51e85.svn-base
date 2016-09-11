function createMenu(arr){
	
	this.title = arr;
	var threeItem , items  ;
	var  arrMenu = new Array();
	this.list = "";
						
	this.td = $("#menus");
	this.headCode = $("<div class=\"w180 h35 \"></div>");
	this.head2Code = $("<div class=\"overhide  hide\" style=\"display:block\"></div>");
	this.ListFather = $("<div class=\"title-menu bggray3 w176 fl\"></div>");
	this.ThreeListFather = $("<div class=\"threeMeun\"></div>");
						 
	for(var i=0; i < this.title.length;i++){
	    this.ThreeListFather.html("");
	    this.ListFather.html("");
	    this.head2Code.html("");
	    this.list = "";
	    this.headCode.html("<div class=\"nav-title w176 bggray_hover h33 curpoin border-top-withe border-bot-gray fl bggray-hover\"><span class=\"inline "+this.title[i].img+" h20 w22 relative top3\"></span><span class=\"text-l inline font13 bold color-gray w127 line-h33 \">"+this.title[i].name+"</span> <span class=\"icon_expand relative top3 inline w16 h16 \"></span></div><div style=\"width:4px;\" class=\"h35 fl bggray2 bggray1\"></div>"); 
		
		var dg = function(er_zi,type){
			var threeItem='',items = '', lastlist='', dgListFather = $("<div class=\"threeMeun\"></div>"),list = er_zi;			
			for(var j = 0 ; j<list.length; j++){
				if(typeof(list[j].children)!= 'undefined'){
				   items += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"threeMeun(this)\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+list[j].name+"</span></a>";
				   for (var k =0 ; k< list[j].children.length; k++){
					    if (typeof( list[j].children[k].children )=='undefined'){
							var urlstr = "";
							if (typeof list[j].children[k].url !='undefined' ){
								var urlstr =list[j].children[k].url;
							}
							threeItem += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"addTab('"+list[j].children[k].name+"','"+urlstr+"',this,"+list[j].children[k].id+")\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+list[j].children[k].name+"</span></a>";   
				     	}else{
						   threeItem += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"threeMeun(this)\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+ list[j].children[k].name+"</span></a>"+dg(	list[j].children[k].children ,2 );
						}
				   }
				   items = items+dgListFather.html(threeItem).get(0).outerHTML;
				}else{
					var urlstr = "";
				    if (typeof list[j].url !='undefined'){
						var urlstr =  list[j].url;
					}
					if(typeof type !='undefined' ){
					    lastlist +=  "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"addTab('"+list[j].name+"','"+urlstr+"',this,"+list[j].id+")\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+list[j].name+"</span></a>";
						items = "<div class=\"threeMeun\" style=\"margin-left:15px;\">"+lastlist+"</div>";
					}else{
						items += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"addTab('"+list[j].name+"','"+urlstr+"',this,"+list[j].id+")\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+list[j].name+"</span></a>";
					}
				}
				threeItem = "";
			}
			return items;
		}
	    if(typeof( this.title[i].children )!='undefined'){
	       this.list = this.title[i].children;	
		   items = dg( this.list);		
	    }else{
		   items = '';
	    }
	    /* for(var j = 0 ; j<this.list.length; j++){
			if(typeof(this.list[j].children)!= 'undefined'){
			   items += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"threeMeun(this)\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+this.list[j].name+"</span></a>";
			   for(var k =0 ; k< this.list[j].children.length; k++){
				 threeItem += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"addTab('"+this.list[j].children[k].name+"','"+this.list[j].children[k].url+"',this)\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+this.list[j].children[k].name+"</span></a>";					
			   }
			   items = items+this.ThreeListFather.html(threeItem).get(0).outerHTML;
		    }else{
			    items += "<a href=\"javascript:;\" class=\"nav_nohover inline\" onclick=\"addTab('"+this.list[j].name+"','"+this.list[j].url+"',this)\"><span class=\"icon_jiantou relative  inline w6 h6 mr12\"></span><span class=\" menutext\">"+this.list[j].name+"</span></a>";
			}
				threeItem = "";
		}*/
		this.ListFather.html(items);
		this.head2Code.html(this.ListFather.get(0).outerHTML+"<div style=\"min-height:240px;\" class=\"w4 relative fr bggray2\"></div>");
		arrMenu.push(this.headCode.get(0).outerHTML+this.head2Code.get(0).outerHTML);			
    }			
	this.td.html(arrMenu.join("")+"<div class=\"w180 h35 \"><div class=\" w176 h33 border-top-withe fl\"></div> <div style=\"width:4px;\" class=\"h35 fl bggray2\"></div></div><div style=\" height:1000px;\" class=\"w4 relative fr bggray2\"></div>");		
}				
	var arr = [	
	           	{"name":"栏目管理","img":"incon7",
	           		"children":[{"name":"公司活动","id":"6","url":"./work/dynamicwork2.html?type=1"},
	           		            {"name":"公司新闻","id":"7","url":"./work/dynamicwork.html?type=2"},
	           		            {"name":"行业资讯","id":"5","url":"./work/dynamicwork.html?type=3"},
	           		            {"name":"媒体资讯","id":"9","url":"./work/dynamicwork.html?type=4"},
	           		            {"name":"水务知识","id":"10","url":"./work/dynamicwork.html?type=5"},
	           		            {"name":"国家行业法律法规","id":"11","url":"./work/dynamicwork.html?type=6"},
	           		            {"name":"湖南省法律法规","id":"12","url":"./work/dynamicwork.html?type=7"},
	           		            {"name":"长沙市法律法规","id":"13","url":"./work/dynamicwork.html?type=8"},	           		            
	           		            {"name":"水质公开","id":"15","url":"./work/dynamicwork.html?type=9"},
	           		            {"name":"用水价格","id":"14","url":"./work/dynamicwork2.html?type=10"},
	           		            {"name":"报装申请","id":"16","url":"./work/services.html?type=1"},
	           		            {"name":"漏水举报","id":"17","url":"./work/services.html?type=2"},
	           		            {"name":"网上报修","id":"18","url":"./work/services.html?type=3"},
	           		            {"name":"人员管理","id":"8","url":"./work/department.html"},
	           		            {"name":"修改密码","id":"7","url":"./work/userpwd.html"}
	           		            ]
	           	}
	         ]

   createMenu(arr);
   function updateTab(url) {
	  var tab = $('#tt').tabs('getSelected');
      var ifram = $(tab).find("iframe");
      ifram=ifram[0];
      ifram.src=url;
   }
   function f(){
		window.shuline.height( window.menu.outerHeight(true) );
		if ($(window).height()<768 ){
		    var h = $(window).height();
			var h2 =107; 
			window.menu.parent().prev().prevAll('.w180').each(function (i,items){
				h2+=$(items).height();
			});
			if (window.menu.height()>h-h2){
			    window.menu.css({height:h-h2,overflow:'auto'}); 
			}			
		}
	}
   function threeMeun(obj){
	  var menu  =  $(obj).next();
	    window.menu = $(menu.parents('.title-menu').get(0));
	    window.shuline  =  window.menu.next();
		$('.nav_hover').removeClass('nav_hover');
			if($(obj).hasClass("nav_hover2")){
				$(obj).addClass('nav_nohover2').removeClass("nav_hover2");
    				menu.slideUp('fast',function(){
				   $(obj).removeClass("nav_nohover2").addClass('nav_nohover');
				   f();
			    })
		    }else{
				if(menu.is(":hidden")){
					$(obj).addClass('nav_hover2').removeClass("nav_nohover2");
					var sibling = menu.siblings(".threeMeun");
					var h  = 0;
					$.each(sibling,function(i,items){
						if(!$(items).is(":hidden")){
							h+=$(items).height();
							$(items).prev().addClass('nav_nohover2').removeClass("nav_hover2");
						}
					});
					menu.slideDown('fast',function(){
					   f();
					}).siblings(".threeMeun").hide();
				}else{
					$(obj).addClass('nav_nohover2');
    				menu.slideUp('fast',function(){
					  $(obj).removeClass("nav_nohover2").addClass('nav_nohover');
					  f();
				    });
			    }
			}
		}
		var index =  0;
		function addTab(title,url,obj,id){
			window.tab_tid=id
			if(!$(obj).parent().hasClass("threeMeun")){
				$('.nav_hover2').removeClass('nav_hover2').addClass("nav_nohover2");
			}else{
				$(".nav_hover2").removeClass("nav_hover2").addClass("nav_nohover2");
				$(obj).parent().prev().addClass("nav_hover2").removeClass("nav_nohover2");
			}
			$('.nav_hover').removeClass('nav_hover');
			$(obj).addClass('nav_hover');
				if ($('#tt').tabs('exists', title)){  
					$('#tt').tabs('select', title); 
					updateTab(url);
				}else{  
					index++;
				if($.browser.msie&&($.browser.version == "6.0")&&!$.support.style){
					setTimeout("document.getElementById('www"+index+"').contentWindow.location.reload(true);",1000);
			    }		  
				$('#tt').tabs('add',{
					title:title,
					content:'<iframe scrolling="yes" frameborder="0" id="www'+index+'" src="'+url+'" style="width:100%;height:100%;"></iframe>',
					closable:true
				});
			} 
		}
		
	
	var wh = $(window).height();
		$("#ead-contq").height(wh - 125);
		$("#left-h2").height(wh-80);    
		   
	$(function(){
		var w =$("#ead-contq").width();
		var h =$("#ead-contq").height();
		var w2 = $(window).width();
		if( w2 > 1350 ){
			$("#tt").width(w+3);
		}else if(w2 < 1350){
			$("#tt").width(w+19);
		}
		$("#tt").height(h+40);
		$(".tabs-inner:first").trigger("click");
   });
   function remove_ie6(id){
	 $("#"+id).remove();
   }
  $(function(){
		function ie(){
			var v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');
			while(
				div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
				all[0]
			);
			return v > 4 ? v : false ;	
		}
	    var v = ie();
		if(v==6 || v==7){
			$("#left-h2").attr('style',"height:none");
			var t2 = 0;
			$('<div class="spring" id="springs"><div class="wrapper">您使用的浏览器版本过低，影响网页性能，建议您<a href="http://www.skycn.com/soft/appid/880.html" target="_blank">升级IE浏览器</a>、或换用<a href="http://www.skycn.com/soft/appid/896.html">火狐</a>浏览器。<a href=javascript:remove_ie6("springs");>关闭 </a></div></div>').prependTo('body').fadeIn();
			$(window).bind("scroll",function(){
				var t =	$(this).scrollTop();
				if(t>t2){
				   $(".spring").animate({"margin-top":"+="+t},500);t2= t;
				}else{ 
				   $(".spring").animate({"margin-top":"-="+t2},500);t2=0;
				}
			});
		}	
		$(".nav-title").each(function(index,item){	
		var  a = $(item);
			$(item).click(function(e){	
				$(".bggray-hover").each(function(j,i){
					$(i).next().removeClass("bggray1");
					$(i).parent().next().hide();
					if(skin_id=="blue"){
						a.attr("style","background:url(images/sidebar_list_on.png)")
						a.find(".text-l ").attr("style","color:#000;font-weight:700;");
						a.parent().siblings(".w180").each(function(i,index){
							$(index).find(".nav-title").removeAttr('style').find(".text-l ").removeAttr("style");
						});
					}
			});			
			var o = $(this);
			o.addClass('bggray-hover');
			o.next().addClass("bggray1");
			
			var t = o.parent().next();
			if(t.is(":hidden")){
			   t.children(":last").height(t.height());
			   t.slideDown("fast");
			}else{
			   t.slideUp("fast");
			   o.next().removeClass("bggray1");
			}	
		});
     });	
	
	if(skin_id=="blue"){
		$("#menus :last-child").prev().find(".w176").removeClass("border-top-withe");
	}
});

function changeSkin(val){
	$.cookie("skin",val);
	window.location.reload();
}

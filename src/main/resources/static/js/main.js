initMenu();

var html = '';
function initMenu(){
	 $.ajax({  
	     url:"mainPage/getUserMenu.handler",
	     type:"get",  
	     async:false,
	     success:function(data){
	    	 // if(!$.isArray(data)){
	    		//  location.href='/myLogin.html';
	    		//  return;
	    	 // }
	    	 var menu = $("#menu");
			 var ulHtml = '<ul class="layui-nav layui-nav-tree beg-navbar">';
			 for (var i = 0; i < data.length; i++) {
				 if (data[i].spread) {
					 ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
				 } else {
					 ulHtml += '<li class="layui-nav-item">';
				 }
				 if (data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0) {
					 ulHtml += '<a  lay-id="'+data[i].programId+'" >' + data[i].programName;
					 ulHtml += '<span class="layui-nav-more"></span>';
					 ulHtml += '</a>';
					 ulHtml += '<dl class="layui-nav-child">';
					 //二级菜单
					 for (var j = 0; j < data[i].children.length; j++) {
						 //是否有孙子节点
						 if (data[i].children[j].children !== undefined && data[i].children[j].children !== null && data[i].children[j].children.length > 0) {
							 ulHtml += '<dd>';
							 ulHtml += '<a lay-id="'+data[i].children[j].programId+'" data-url="'+ data[i].children[j].url +'">' + data[i].children[j].programName;
							 ulHtml += '<span class="layui-nav-more"></span>';
							 ulHtml += '</a>';
							 //三级菜单
							 ulHtml += '<dl class="layui-nav-child">';
							 var grandsonNodes = data[i].children[j].children;
							 for (var k = 0; k < grandsonNodes.length; k++) {
								 ulHtml += '<dd>';
								 ulHtml += '<a lay-id="'+grandsonNodes[k].programId+'" data-url="'+ grandsonNodes[k].url +'">' + grandsonNodes[k].programName + '</a>';
								 ulHtml += '</dd>';
							 }
							 ulHtml += '</dl>';
							 ulHtml += '</dd>';
						 }else{
							 ulHtml += '<dd>';
							 ulHtml += '<a lay-id="'+data[i].children[j].programId+'" data-url="'+data[i].children[j].url+'">' + data[i].children[j].programName;
							 ulHtml += '</a>';
							 ulHtml += '</dd>';
						 }
						 //ulHtml += '<dd title="' + data[i].children[j].title + '">';
					 }
					 ulHtml += '</dl>';
				 } else {
					 var dataUrl = (data[i].url !== undefined && data[i].url !== '') ? 'data-url="' + data[i].url + '"' : '';
					 //ulHtml += '<a href="javascript:;" ' + dataUrl + '>';
					 ulHtml += '<a lay-id="'+data[i].programId+'" data-url="' + data[i].url + '"' + dataUrl + '>';
					 if (data[i].icon !== undefined && data[i].icon !== '') {
						 if (data[i].icon.indexOf('fa-') !== -1) {
							 ulHtml += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
						 } else {
							 ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
						 }
					 }
					 ulHtml += '<cite>' + data[i].programName + '</cite>';
					 ulHtml += '</a>';
				 }
				 ulHtml += '</li>';
			 }
			 ulHtml += '</ul>';

			 menu.append(ulHtml);
	    	//  $.each(data, function(i,item){
	        //      var a = $("<a href='javascript:;'></a>");
			//
	        //      var css = item.css;
	        //      if(css!=null && css!=""){
	        //     	 a.append("<i aria-hidden='true' class='  fa " + css +"'></i>");
	        //      }
	        //      a.append("<cite>"+item.programName+"</cite>");
	        //      a.attr("lay-id", item.programId);
			//
	        //      var href = item.url;
	        //      if(href != null && href != ""){
	        //         a.attr("data-url", href);
	        //      }
			//
	        //      var li = $("<li class='layui-nav-item'></li>");
	        //      if (i == 0) {
	        //     	 li.addClass("layui-nav-itemed");
	        //      }
	        //      li.append(a);
			//
	        //      //二级菜单
	        //      var child2 = item.children;
	        //      if(child2 != null && child2.length > 0){
	        //     	 $.each(child2, function(j,item2){
	        //     		 var ca = $("<a href='javascript:;'></a>");
            //              ca.attr("data-url", item2.url);
            //              ca.attr("lay-id", item2.programId);
			//
            //              var css2 = item2.css;
            //              if(css2!=null && css2!=""){
            //             	 ca.append("<i aria-hidden='true' class='fa " + css2 +"'></i>");
            //              }
			//
            //              ca.append("<cite>"+item2.programName+"</cite>");
			//
            //              var dd = $("<dd></dd>");
            //              dd.append(ca);
			//
            //              var dl = $("<dl class='layui-nav-child site-demo-active '></dl>");
			//
			//
			// 			 var child3 = item2.children;
			// 			 if(child3 != null && child3.length > 0){
			// 				 $.each(child3, function(k,item3){
			// 					 var ca = $("<a href='javascript:;'></a>");
			// 					 ca.attr("data-url", item3.url);
			// 					 ca.attr("lay-id", item3.programId);
			//
			// 					 var css2 = item3.css;
			// 					 if(css2!=null && css2!=""){
			// 						 ca.append("<i aria-hidden='true' class='fa " + css2 +"'></i>");
			// 					 }
			//
			// 					 ca.append("<cite>"+item3.programName+"</cite>");
			//
			// 					 var ol = $("<ol></ol>");
			// 					 var li = $("<li></li>");
			// 					 li.append(ca);
			// 					 ol.append(li);
			// 					 dd.append(ol);
			//
			// 					 // var dl = $("<dl class='layui-nav-child site-demo-active '></dl>");
			// 					 // dl.append(dd);
			// 					 //
			// 					 // li.append(dl);
			//
			// 				 });
			// 			 }
			//
			// 			 dl.append(dd);
			//
			// 			 li.append(dl);
	        //     	 });
	        //     }
	        //     menu.append(li);
	        // });
	     }
	 });
}

// 登陆用户头像昵称
//showLoginInfo();
function showLoginInfo(){
	$.ajax({
		type : 'get',
		url : '/users/current',
		async : false,
		success : function(data) {
			$(".admin-header-user span").text(data.nickname);
			
			var pro = window.location.protocol;
			var host = window.location.host;
			var domain = pro + "//" + host;
			
			var sex = data.sex;
			var url = data.headImgUrl;
			if(url == null || url == ""){
				if(sex == 1){
					url = "/img/avatars/sunny.png";
				} else {
					url = "/img/avatars/1.png";
				}
				
				url = domain + url;
			} else {
				url = domain + "/statics" + url;
			}
			var img = $(".admin-header-user img");
			img.attr("src", url);
		}
	});
}

//showUnreadNotice();
function showUnreadNotice(){
	$.ajax({
		type : 'get',
		url : '/notices/count-unread',
		success : function(data) {
			$("[unreadNotice]").each(function(){
				if(data>0){
					$(this).html("<span class='layui-badge'>"+data+"</span>");
				}else{
					$(this).html("");
				}
			});
			
		}
	});
}

function logout(){
	$.ajax({
		type : 'get',
		url : '/login/logout',
		success : function(data) {
			localStorage.removeItem("token");
			location.href='/myLogin.html';
		}
	});
}

var active;

layui.use(['layer', 'element'], function() {
	var $ = layui.jquery,
	layer = layui.layer;
	var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    element.on('nav(demo)', function(elem){
      //layer.msg(elem.text());
    });
	var $ = layui.$;
	$(".quxiao").on("click",function(){
		// window.parent.location.reload(); //刷新父页面
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);  // 关闭layer
	})

	  //触发事件
	   active = {
	       tabAdd: function(obj){
	    	 var lay_id = $(this).attr("lay-id");
	    	 var title = $(this).html() + '<i class="layui-icon" data-id="' + lay_id + '"></i>';
	         //新增一个Tab项
	         element.tabAdd('admin-tab', {
	           title: title,
	           content: '<iframe src="' + $(this).attr('data-url') + '"></iframe>',
	           id: lay_id
	         });
	         element.tabChange("admin-tab", lay_id);
	       },
	       tabDelete: function(lay_id){
    	      element.tabDelete("admin-tab", lay_id);
    	   },
	       tabChange: function(lay_id){
	         element.tabChange('admin-tab', lay_id);
	       }
	   };
	   //添加tab  
	   $(document).on('click','a',function(){
	       if(!$(this)[0].hasAttribute('data-url') || $(this).attr('data-url')===''){
	    	   return;  
	       }
	       var tabs = $(".layui-tab-title").children();
	       var lay_id = $(this).attr("lay-id");

	       for(var i = 0; i < tabs.length; i++) {
	           if($(tabs).eq(i).attr("lay-id") == lay_id) {
	        	   active.tabChange(lay_id);
	               return;
	           }
	       }
	       active["tabAdd"].call(this);  
	       resize();  
	   });  
	     
	   //iframe自适应  
	   function resize(){  
	       var $content = $('.admin-nav-card .layui-tab-content');  
	       $content.height($(this).height() - 147);  
	       $content.find('iframe').each(function() {  
	           $(this).height($content.height());  
	       });  
	   }  
	   $(window).on('resize', function() {  
	       var $content = $('.admin-nav-card .layui-tab-content');  
	       $content.height($(this).height() - 147);  
	       $content.find('iframe').each(function() {  
	           $(this).height($content.height());  
	       });  
	   }).resize();  
	   
	   //toggle左侧菜单  
	   $('.admin-side-toggle').on('click', function() {
	       var sideWidth = $('#admin-side').width();  
	       if(sideWidth === 200) {  
	           $('#admin-body').animate({  
	               left: '0'  
	           });
	           $('#admin-footer').animate({  
	               left: '0'  
	           });  
	           $('#admin-side').animate({  
	               width: '0'  
	           });  
	       } else {  
	           $('#admin-body').animate({  
	               left: '200px'  
	           });  
	           $('#admin-footer').animate({  
	               left: '200px'  
	           });  
	           $('#admin-side').animate({  
	               width: '200px'  
	               });  
	           }  
	       });
	   
	    //手机设备的简单适配
	    var treeMobile = $('.site-tree-mobile'),
	    shadeMobile = $('.site-mobile-shade');
	    treeMobile.on('click', function () {
	        $('body').addClass('site-mobile');
	    });
	    shadeMobile.on('click', function () {
	        $('body').removeClass('site-mobile');
	    });
});
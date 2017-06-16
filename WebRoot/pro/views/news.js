var pageNum = 1;//默认当前页码
var onePageCount = 8;//默认每一页显示的数目
var total = 0;

$(function() {
	dataInit();
	eventInit();
})


function eventInit(){
	//保存按钮事件
	$('body').on('click', '#add', function(e){
		var params = {	
			title : $('#title').val(),
			date : $('#date').val(),
			article : $('#article').val()
		}
		AjaxUtil.request({url:path+"/post/news/addNews",params:params,type:'json',callback:function(json){
			if (json.returnCode == 0){
				//清除
				$('#title').val("");
				$('#article').val("");
				//隐藏添加面板
				$('#closeBtn').click();
				//重新加载数据
				dataInit();
			}else{
				alert(json.returnMessage);
			}
		}
		});
	});
	
	//删除按钮事件
	$('body').on('click', '#deleteId', function(e){
		var id = $(this).attr('deleteId');
		$("#delModal").modal("show");
		$("#del").attr("deleteId", id);
	});
	//确定删除按钮点击事件
	$('body').on('click', '#del', function(e){
		var id = $(this).attr("deleteId");
		var params = {
			id:id
		}
		AjaxUtil.request({url:path+"/post/news/deleteNews",params:params,type:'json',callback:function(json){
			if (json.returnCode == 0){
				$('#cancelDel').click();
				dataInit();
			}else{
				alert("删除失败！");
			}
		}
		});
	});
	
	
}

function nextPageAction(){
	alert(total+"hh");
	alert("total  " + (total/onePageCount+1) + "   " + (pageNum+1));
	if(pageNum+1 < total/onePageCount+1){
		pageNum += 1;
	}
	
}

function dataInit(){
	//封装分页参数
	var params = {
		pageNum:pageNum,//请求的页码
		onePageCount:onePageCount//单页显示的数目
	}; 
	
	//请求数据
	AjaxUtil.request({url : path + "/post/news/selectAllNews",params : params,type : 'json',callback : function(json) {
			if (json.returnCode == "0") {
				//$("#totalId").html = json.total;
				var source = $("#newsTable").html();
				var template = Handlebars.compile(source);
				$("#tbody").html(template(json));
			} else {
				alert("数据加载失败！");
			}
			
		}
	});
	
}
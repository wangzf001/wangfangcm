$(function () {
    $("#jqGrid").jqGrid({
        url: '../../../ylfwzjzzorder/orderlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 },			
			{ label: '用户名', name: 'username', index: 'uid', width: 80, sortable:false}, 			
			{ label: '医生名', name: 'doctorname', index: 'doctorid', width: 80, sortable:false }, 			
			{ label: '预约电话', name: 'invitemobile', index: 'invitemobile', width: 80 }, 			
			{ label: '预约时间', name: 'invitetime', index: 'invitetime', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '订单状态', name: 'status', index: 'status', width:80 , formatter:function(value, options, row){
				var str = "";
				switch(value){
				case 1: str='<span class="label label-primary">已预约</span> &nbsp;&nbsp;<a class="label label-info" onclick="jd(\''+row.id+'\')" >接单</a> ';break;
				case 3: str='<span class="label label-success">已完成</span>';break;
				case 4: str='<span class="label label-default">待评价</span>';break;
				case 5: str='<span class="label label-warning">已取消</span>';break;
		
				}
				return str;
			}},
			{ label: '其他',  width: 160 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="showorderinfo(\''+row.id+'\')" >订单信息</a> &nbsp;&nbsp;'
				+'<a class="label label-info" onclick="comment(\''+row.id+'\')" >评论信息</a>'
			}}, 
			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    

    
  
    $("#jqGridComment").jqGrid({
        url: '../../../ylfwzjzzcomment/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '评价内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '总分', name: 'score', index: 'score', width: 80 }, 			
			{ label: '服务分数', name: 'servicescore', index: 'servicescore', width: 80 }, 			
			{ label: '是否匿名(1:是，0：不是)', name: 'anonymous', index: 'anonymous', width: 80,formatter: function(value, options, row){
				return value == 1?"是":"否";
			}}, 		
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridCommentPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGridComment").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		ylfwZjzzOrder: {},
		commentorderid:null,
		ylfwZjzzComment:{},
		imglist:null,
		showComment:true,
		q:{
			order:{
				status:"",
				starttime:"",
				endtime:"",
				ordercode:"",
				username:"",
				doctorname:"",
				paystatus:""
			}
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.ylfwZjzzOrder = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.ylfwZjzzOrder.id == null ? "../../../ylfwzjzzorder/save" : "../../../ylfwzjzzorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.ylfwZjzzOrder),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../../ylfwzjzzorder/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../../../ylfwzjzzorder/orderinfo/"+id, function(r){
                vm.ylfwZjzzOrder = r.ylfwZjzzOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.q.order.starttime = $("#dtp_input1").val();
			vm.q.order.endtime = $("#dtp_input2").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"status":vm.q.order.status,
					"starttime":vm.q.order.starttime,
					"endtime":vm.q.order.endtime,
					"username":vm.q.order.username,
					"doctorname":vm.q.order.doctorname,
					"ordercode":vm.q.order.ordercode,
					"paystatus":vm.q.order.paystatus
					},
                page:page
            }).trigger("reloadGrid");
			
		},
		updateComment: function (event) {
			var id = getSelectedRowByGid($("#jqGridComment"));
			if(id == null){
				return ;
			}
			vm.showComment = false;
            vm.title = "修改";
            
            vm.getInfoComment(id)
		},getInfoComment: function(id){
			$.get("../../../ylfwzjzzcomment/commnetinfo/"+id, function(r){
                vm.ylfwZjzzComment = r.ylfwZjzzComment;
                vm.imglist = r.imglist;
            });
		},
	}
});


function showorderinfo(orderid){
	vm.showList = false;
    vm.title = "修改";
    vm.getInfo(orderid);
}


function jd(id){
	var url = "../../../ylfwzjzzorder/jd"
		$.ajax({
			type: "POST",
			url: url,
			contentType : "application/x-www-form-urlencoded",
			data: {"orderid":id},
			success: function(r){
				if(r.errCode === 0){
					alert('操作成功', function(index){
						vm.reload();
					});
				}else{
					alert(r.msg);
				}
			}
		});
}


function comment(oid){
	vm.showComment= true;
	vm.commentorderid=oid;
	var pageOD = $("#jqGridComment").jqGrid('getGridParam','page');
	$("#jqGridComment").jqGrid('setGridParam',{
		postData:{
			"orderid":oid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "订单详情列表",
		area: ['800px', '600px'],
		shade: 0,
		shadeClose: false,
		content: $("#CommentDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
}

$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tbxwxorder/orderlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'username', width: 80 }, 			
			{ label: '联系方式', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '预约时间', name: 'invitetime', index: 'invitetime', width: 80 },
			{ label: '服务地点', name: 'serviceplace', index: 'serviceplace', width: 80 }, 			
			{ label: '维修人员', name: 'realname', index: 'realname', width: 80 ,sortable:false}, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 }, 			
			{ label: '订单状态 ', name: 'orderstatus', index: 'orderstatus', width: 80 ,formatter: function(value, options, row){
				var str = "";
				switch(value){
				case 1: str='<span class="label label-primary">待接单</span>';break;
				case 2: str='<span class="label label-info">已接单</span>';break;
				case 3: str='<span class="label label-success">已完成</span>';break;
				case 4: str='<span class="label label-default">待评价</span>';break;
				case 5: str='<span class="label label-warning">已取消</span>';break;
				}
				return str;
			}},  				
			{ label: '支付状态', name: 'paystatus', index: 'paystatus', width: 80,formatter: function(value, options, row){ 
				return value==1?"已支付":"未支付";
			}}, 			
			{ label: '其他', width: 160,sortable:false ,formatter: function(value, options, row){ 
				return '<a class="label label-info" onclick="showorderinfo(\''+row.id+'\')" >详情</a>'
				+'&nbsp;&nbsp;<a class="label label-info" onclick="showprocesslist(\''+row.id+'\')" >进度</a>'
				+'&nbsp;&nbsp;<a class="label label-info" onclick="comment(\''+row.id+'\')" >评论</a>';
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
    
    $("#jqGridCheck").jqGrid({
        url: '../../tbxwxorderprocesses/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '1:下单，2: 审核中，3：审核通过，4：审核不通过，5：派工，6： 接单', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){ 
				var str ="";
				switch(value){
				case 1:str= "下单";break;
				case 2:str= "审核中";break;
				case 3:str= "审核通过";break;
				case 4:str= "审核不通过";break;
				case 5:str= "派工";break;
				case 6:str= "接单";break;
				}
				return str;
			}}, 			
			{ label: '时间', name: 'createtime', index: 'createtime', width: 100 }	,		
			{ label: '详情信息', name: 'detail', index: 'detail', width: 450 }	
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridCheckPager",
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
        	$("#jqGridCheck").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $("#jqGridComment").jqGrid({
        url: '../../tbxwxcomment/list',
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
		checkorderid:null,
		orderid:null,
		tBxwxOrder: {},
		itemlist:null,
		showComment:true,
		commentimglist:null,
		tBxwxOrderProcesses:{},
		tBxwxComment:{},
		reasonlist:{},
		failurereason:"",
		failurereasonid:"",
		menderlist:null,
		menderid:null,
		mender:{
			realname:null
		},
		q:{
			order:{
				status:"",
				starttime:"",
				endtime:"",
				ordercode:"",
				username:"",
				barbername:"",
				paystatus:"",
				mendername:""
			}
		},
		imglist:null//维修图片
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tBxwxOrder = {};
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
			var url = vm.tBxwxOrder.id == null ? "../../tbxwxorder/save" : "../../tbxwxorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tBxwxOrder),
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
				    url: "../../tbxwxorder/delete",
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
			$.get("../../tbxwxorder/info/"+id, function(r){
                vm.tBxwxOrder = r.tBxwxOrder;
                vm.itemlist = r.itemlist;
                if(r.mender == null){
                	vm.mender.realname = "";
                }else{
                	vm.mender =r.mender;
                }
                vm.imglist = r.imglist;
                vm.reasonlist = r.reasonlist;
                vm.menderlist = r.menderlist;
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
					"mendername":vm.q.order.mendername,
					"ordercode":vm.q.order.ordercode,
					"paystatus":vm.q.order.paystatus
					},
                page:page
            }).trigger("reloadGrid");
			
		},updateComment: function (event) {
			var id = getSelectedRowByGid($("#jqGridComment"));
			if(id == null){
				return ;
			}
			vm.showComment = false;
            vm.title = "修改";
            
            vm.getInfoComment(id)
		},getInfoComment: function(id){
			$.get("../../tbxwxcomment/commnetinfo/"+id, function(r){
                vm.tBxwxComment = r.tBxwxComment;
                vm.commentimglist = r.commentimglist;
            });
		},
		check: function(status){
			if(status == 4){
				layer.open({
					type: 1,
					offset: '50px',
					skin: 'layui-layer-molv',
					title: "审核列表",
					area: ['800px', '300px'],
					shade: 0,
					shadeClose: false,
					content: $("#reasonDIV"),
					btn: ['确定'],
					btn1: function (index) {
						layer.close(index);
						vm.saveprocessor(status);
			        }
				});
				
			}else if(status == 5){
				layer.open({
					type: 1,
					offset: '50px',
					skin: 'layui-layer-molv',
					title: "派单列表",
					area: ['800px', '300px'],
					shade: 0,
					shadeClose: false,
					content: $("#sendDIV"),
					btn: ['确定'],
					btn1: function (index) {
						layer.close(index);
						vm.saveprocessor(status);
			        }
				});
			}else{
				vm.saveprocessor(status);
			}
		},
		saveprocessor: function(status){
			var url ="../../tbxwxorderprocesses/save" ;
			vm.tBxwxOrderProcesses.orderid =vm.orderid;
			vm.tBxwxOrderProcesses.status = status;
			vm.tBxwxOrderProcesses.failurereasonid = vm.failurereasonid;
			vm.tBxwxOrderProcesses.failurereason = vm.failurereason;
			vm.tBxwxOrderProcesses.menderid= vm.menderid;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tBxwxOrderProcesses),
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
	}
});

function showorderinfo(orderid){
	vm.showList = false;
    vm.title = "修改";
    vm.orderid = orderid;
    vm.getInfo(orderid);
}

function showprocesslist(orderid){
	vm.checkorderid =orderid;
	var pageOD = $("#jqGridCheck").jqGrid('getGridParam','page');
	$("#jqGridCheck").jqGrid('setGridParam',{
		postData:{
			"orderid":orderid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "审核列表",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#showCheckDIV"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
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
		title: "评论列表",
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
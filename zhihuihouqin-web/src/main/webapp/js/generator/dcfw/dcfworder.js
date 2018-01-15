function cancelReason(idStr,content){
	alert(content);
}
function finish(uid,foodid){
	$.ajax({
		type: "POST",
	    url: "../../dcfworder/updateBatch",
	    data: JSON.stringify({uid:uid,foodid:foodid}),
	    success: function(r){
			if(r.errCode == 0){
				alert('操作成功', function(index){
					$("#jqGridOD").trigger("reloadGrid");
				});
			}else{
				alert(r.msg);
			}
		}
	});
}
function orderDetail(uid,ispack){
		if(1 == ispack){
			vm.packageuid = uid;
			vm.showfinishall=true;
		}else{
			vm.showfinishall=false;
		}
		var pageOD = $("#jqGridOD").jqGrid('getGridParam','page');
		$("#jqGridOD").jqGrid('setGridParam',{
			postData:{
				"uid":uid,
				"groupbyuid":0,
				"packfood":ispack,
				"realname":vm.q.realname,
				"mobile":vm.q.mobile,
				"orderStatus":vm.q.status,
				"payStatus":vm.q.payStatus,
				"createTimeStart":vm.q.createTimeStart,
				"createTimeEnd":vm.q.createTimeEnd,
				"ordercode":vm.q.ordercode
				},
	        page:1
	    }).trigger("reloadGrid");
		layer.open({
			type: 1,
			offset: '50px',
			skin: 'layui-layer-molv',
			title: "订单详情",
			area: ['800px', '450px'],
			shade: 0,
			shadeClose: false,
			content: $("#menuLayer"),
			btn: ['确定', '取消'],
			btn1: function (index) {
				$("#jqGrid").trigger("reloadGrid");
				layer.close(index);
	        }
		});
	}
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../dcfworder/list',
        datatype: "json",
        colModel: [			
//			{ label: 'id', name: 'id', index: 'id', width: 20, key: true },
			{ label: '用户id', name: 'uid', index: 'uid', width: 40 },
//			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 100 }, 			
//			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 50 }, 			
//			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 100 }, 			
			{ label: '预留电话', name: 'mobile', index: 'mobile', width: 100 }, 			
			{ label: '真实姓名', name: 'realname', index: 'realname', width: 50 }, 			
//			{ label: '过期时间', name: 'expireTime', index: 'expire_time', width: 100 }, 			
//			{ label: '付款状态', name: 'payStatus', index: 'pay_status', width: 50 ,formatter: function(value, options, row){
//				var str = '';
//				switch (value) {
//				case 0:
//					str = '<span class="label label-default">未付款</span>';
//					break;
//				case 1:
//					str = '<span class="label label-success">已付款</span>';
//					break;
//				default:
//					break;
//				}
//				return str;
//			}}, 
//			{ label: '支付方式', name: 'payType', index: 'pay_type', width: 50 ,formatter: function(value, options, row){
//				var str = '';
//				switch (value) {
//				case 0:
//					str = '<span class="label label-success">线下支付</span>';
//					break;
//				case 1:
//					str = '<span class="label label-success">支付宝</span>';
//					break;
//				case 2:
//					str = '<span class="label label-success">微信</span>';
//					break;
//				case 3:
//					str = '<span class="label label-warning">个人余额对公</span>';
//					break;
//				case 4:
//					str = '<span class="label label-success">个人余额对私</span>';
//					break;
//				case 5:
//					str = '<span class="label label-warning">集体余额对公</span>';
//					break;
//				default:
//					str = '<span class="label label-warning">未设置</span>';
//					break;
//				}
//				return str;
//			}},
//			{ label: '订单状态', name: 'status', index: 'status', width: 105 ,formatter: function(value, options, row){
//				var str = '';
//				switch (value) {
//				case 1:
//					str = '<span class="label label-default">已预约</span>';
//					break;
//				case 3:
//					str = '<span class="label label-warning">已完成</span>';
//					break;
//				case 4:
//					str = '<span class="label label-info">已评价</span>';
//					break;
//				case 5:
//					str = '<span class="btn btn-danger" onclick="cancelReason(\''+row.cancelReasonids+'\',\''+row.reasonContent+'\')" >已取消(查看原因)</span>';
//					break;
//				case 6:
//					str = '<span class="label label-default" >已删除</span>';
//					break;
//				default:
//					break;
//				}
//				return str;
//			}},
			{ label: '个人订单', name: '', index: 'reason_content', width: 100 ,formatter:function(value, options, row){
				var str = '<button class="btn  btn-success" onclick="orderDetail('+row.uid+',0)">个人订单</button>';
				return str; 
			}},
			{ label: '当日打包', name: '', index: 'reason_content', width: 100 ,formatter:function(value, options, row){
				var str = '<button class="btn  btn-success" onclick="orderDetail('+row.uid+',1)">当日打包</button>';
				return str; 
			}},
			{ label: '当日订单完成度', name: 'undonum', index: '', width: 100 ,formatter:function(value, options, row){
				var str = "";
				if (value==0) {
					str = '<span class="label label-success" >订单打包完毕</span>';
				}else{
					str = '<span class="label label-danger" >还有订单未打包</span>';
				}
				
				return str; 
			}}
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
    
    $("#jqGridOD").jqGrid({
        url: '../../dcfworder/list',
        datatype: "json",
        colModel: [			
//			{ label: '订单id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '食物', name: 'detailList', index: '', width: 80 ,formatter: function(value, options, row){
				var str = '<span class="label label-danger" >'+value[0].foodName+'</span>';
				return str;
			}},
			{ label: '数量', name: 'detailList', index: '', width: 80 ,formatter: function(value, options, row){
				var str = row.packfood;
				if (row.packfood==1) {
					str = '<span class="label label-danger" >'+row.sumcount+'</span>';
				}else{
					str = '<span class="label label-danger" >'+value[0].count+'</span>';
				}
				return str;
			}}, 
			{ label: '食物图片', name: 'detailList', index: '', width: 100 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value[0].foodImg+'" style="width:100px;height:100px"/>';
				return str;
			}},
			{ label: '付款状态', name: 'payStatus', index: 'pay_status', width: 50 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-default">未付款</span>';
					break;
				case 1:
					str = '<span class="label label-success">已付款</span>';
					break;
				default:
					break;
				}
				return str;
				}},
				{ label: '订单状态', name: 'status', index: 'status', width: 105 ,formatter: function(value, options, row){
					var str = '';
					switch (value) {
					case 1:
						if (row.packfood==1) {
							str = '<span class="btn btn-warning" onclick="finish(\''+row.uid+'\',\''+row.detailList[0].foodid+'\')" >去完成</span>';
						}else{
							str = '<span class="label label-default" >已下单</span>';
						}
						break;
					case 3:
						str = '<span class="label label-warning">已完成</span>';
						break;
					case 4:
						str = '<span class="label label-info">待评价</span>';
						break;
					case 5:
						str = '<span class="btn btn-danger" onclick="cancelReason(\''+row.cancelReasonids+'\',\''+row.reasonContent+'\')" >已取消(查看原因)</span>';
						break;
					case 6:
						str = '<span class="label label-default" >已删除</span>';
						break;
					default:
						break;
					}
					return str;
				}},
			{ label: '食物原料', name: 'detailList', index: '', width: 80 ,formatter: function(value, options, row){
				var str = value[0].foodOriginal;
				return str;
			}},
			{ label: '单价', name: 'detailList', index: '', width: 80 ,formatter:function(value, options, row){
				var str = '￥'+value[0].price;
				return str; 
			}},
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 100 }, 			
			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 50 ,formatter: function(value, options, row){
				var str = row.packfood;
				if (row.packfood==1) {
					str = '<span class="label label-danger" >'+row.sumtotal+'</span>';
				}else{
					str = '<span class="label label-danger" >'+row.totalprice+'</span>';
				}
				return str;
			}},
			{ label: '支付方式', name: 'payType', index: 'pay_type', width: 50 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-success">线下支付</span>';
					break;
				case 1:
					str = '<span class="label label-success">支付宝</span>';
					break;
				case 2:
					str = '<span class="label label-success">微信</span>';
					break;
				case 3:
					str = '<span class="label label-warning">个人余额对公</span>';
					break;
				case 4:
					str = '<span class="label label-success">个人余额对私</span>';
					break;
				case 5:
					str = '<span class="label label-warning">集体余额对公</span>';
					break;
				default:
					str = '<span class="label label-warning">未设置</span>';
					break;
				}
				return str;
			}},
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPagerOD",
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
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			realname:"",
			mobile:"",
			orderStatus:"1",
			payStatus:"",
			createTimeStart:"",
			createTimeEnd:"",
			ordercode:""
		},
		showList: true,
		title: null,
		dcfwOrder: {},
		packageuid:null,
		showfinishall:false
	},
//	watch:{
//		'q.realname': function(newValue,oldVaule){
//			alert(oldVaule+'-->'+newValue);
//		}
//	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dcfwOrder = {};
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
			var url = vm.dcfwOrder.id == null ? "../../dcfworder/save" : "../../dcfworder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dcfwOrder),
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
				    url: "../../dcfworder/delete",
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
			$.get("../../dcfworder/info/"+id, function(r){
                vm.dcfwOrder = r.dcfwOrder;
            });
		},
		finished: function(type){
			var uid = null;
			if(type == 0 ){
				//全部
			}else{
				//个人
				uid = vm.packageuid;
			}
			var url = "../../dcfworder/finish"
				$.ajax({
					type: "POST",
					url: url,
					contentType : "application/x-www-form-urlencoded",
					data: {"uid":uid},
					success: function(r){
						if(r.errCode === 0){
							alert('操作成功', function(index){
								vm.reload();
								if(1 == type ){
									orderDetail(uid,1);
								}
								
							});
						}else{
							alert(r.msg);
						}
					}
				});
			
		},
		reload: function (event) {
			vm.showList = true;
			vm.q.createTimeStart = $("#dtp_input1").val();
			vm.q.createTimeEnd = $("#dtp_input2").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"realname":vm.q.realname,
					"mobile":vm.q.mobile,
					"orderStatus":vm.q.status,
					"payStatus":vm.q.payStatus,
					"createTimeStart":vm.q.createTimeStart,
					"createTimeEnd":vm.q.createTimeEnd,
					"ordercode":vm.q.ordercode
					},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
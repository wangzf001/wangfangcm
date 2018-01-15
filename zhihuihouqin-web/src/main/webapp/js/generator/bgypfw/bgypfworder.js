function cancelReason(idStr,content){
	alert(idStr+content);
}
function setDistributer(oid,deliverytime){
	//查询所有可以配送的配送人员
	$.ajax({
		type: "POST",
	    url: "../../deliveryman/queryDmanList",
	    data: JSON.stringify({type:8,deliverytime:deliverytime}),  
	    success: function(r){
	    	if(r.errCode === 0){
				vm.dmanlist = r.page.list;
				layer.open({
					type: 1,
					offset: '50px',
					skin: 'layui-layer-molv',
					title: "订单详情",
					area: ['280px', '160px'],
					shade: 0,
					shadeClose: false,
					content: $("#menuLayer1"),
					btn: ['确定', '取消'],
					btn1: function (index) {
						//分配接单员
						$.ajax({
							type: "POST",
						    url: "../../deliveryman/setDeliveryman",
						    data: JSON.stringify({type:8,oid:oid,did:vm.did}),
						    success: function(r){
						    	$("#jqGrid").trigger("reloadGrid");
						    	layer.close(index);
						    }});
			        }
				});
			}else{
				alert(r.msg);
			}
		}
	});
//	alert(oid+deliverytime);
	
}
function orderDetail(value){
//		alert(value);
		var pageOD = $("#jqGridOD").jqGrid('getGridParam','page');
		$("#jqGridOD").jqGrid('setGridParam',{
			postData:{
				"oid":value
				},
	        page:pageOD
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
				layer.close(index);
	        }
		});
	}
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../bgypfworder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单号', name: 'code', index: 'code', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 80 ,formatter: function(value, options, row){
				var str = '￥'+value;
				return str;
			}}, 			
			{ label: '送货地址', name: 'address', index: 'addressid', width: 80 ,formatter:function(value, options, row){
				var str = '<span class="btn btn-warning" onclick="cancelReason(\'收货人姓名：'+value.realname+'<br/>\',\'收货人手机：'+value.mobile+'\')" >'+value.address+'</span>';
				return str; 
			}}, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 80 },
			{ label: '期望送达时间', name: 'expertarrivaltme', index: 'expertarrivaltme', width: 80 ,formatter:function(value, options, row){
				var str = "";
				if (value==""||value==null||value=="null") {
					str = "尽快送达";
				}else{
					str = value;
				}
				return str; 
			}}, 
			{ label: '下单人姓名', name: 'username', index: 'username', width: 80 }, 			
			{ label: '预留电话', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '支付方式', name: 'paytype', index: 'paytype', width: 50 ,formatter: function(value, options, row){
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
					if (row.payStatus==1) {
						str = '<span class="btn btn-warning" onclick="setDistributer(\''+row.id+'\',\''+row.expertarrivaltme+'\')" >分配配送员</span>';
					}else{
						str = '<span class="label label-default">已下单</span>';
					}
					break;
				case 2:
					str = '<span class="label label-default">配送中</span>';
					break;
				case 3:
					str = '<span class="label label-success">已完成</span>';
					break;
				case 4:
					str = '<span class="label label-success">已评价</span>';
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
			{ label: '订单详情', name: '', index: '', width: 100 ,formatter:function(value, options, row){
				var str = '<button class="btn  btn-success" onclick="orderDetail('+options.rowId+')">订单详情</button>';
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
        url: '../../bgypfworderdetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商品名', name: 'sku', index: 'skuid', width: 80 ,formatter: function(value, options, row){
				var str = '<span class="label label-success">'+value.skuname+'</span>';
				return str;
			}},
			{ label: '商品图片', name: 'sku', index: 'skuid', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value.mainimg+'" style="width:100px;height:100px"/>';
				return str;
			}},
			{ label: '数量', name: 'count', index: 'count', width: 80 }, 			
			{ label: '单价', name: 'price', index: 'price', width: 80 ,formatter: function(value, options, row){
				var str = '￥'+value;
				return str;
			}}, 			
			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 80 ,formatter: function(value, options, row){
				var str = '￥'+value;
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
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			realname:"",
			mobile:"",
			status:"",
			payStatus:"",
			createTimeStart:"",
			createTimeEnd:"",
			ordercode:""
		},
		showList: true,
		title: null,
		bgypfwOrder: {},
		dmanlist:[],
		did:""
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bgypfwOrder = {};
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
			var url = vm.bgypfwOrder.id == null ? "../../bgypfworder/save" : "../../bgypfworder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.bgypfwOrder),
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
				    url: "../../bgypfworder/delete",
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
			$.get("../../bgypfworder/info/"+id, function(r){
                vm.bgypfwOrder = r.bgypfwOrder;
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
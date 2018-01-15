function cancelReason(idStr,content){
	alert(idStr+content);
}
function updateToStatus(oid,status){
	vm.gxdfworderlog = {
			orderid:oid,
			status:status
	};
	vm.updateLog();
}
function orderDetail(value){
//		alert(value);
	    vm.detailorderid=value;
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
	gettypelist();
    $("#jqGrid").jqGrid({
        url: '../../gxdfworder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '用户名', name: 'nickname', index: 'nickname', width: 80 },
            { label: '联系方式', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '订单号', name: 'code', index: 'code', width: 80 },
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '订单状态', name: 'status', index: 'status', width: 105 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">已预约</span>';
					break;
				case 2:
					str = '<span class="label label-warning">洗涤中</span>';
					break;
				case 3:
					str = '<span class="label label-warning">已完成</span>';
					break;
				case 4:
					str = '<span class="label label-info">待评价</span>';
					break;
				case 5:
					str = '<span class="btn btn-danger" onclick="cancelReason(\''+row.cancelReasonids+'\',\''+row.reasonContent+'\')" >已取消(查看原因)</span>';
				case 6:
					str = '<span class="label label-info">已删除</span>';
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '订单更新', name: 'logStatus',  width: 105 ,formatter: function(value, options, row){
				var toStatus = value + 1;
				var str = value;
				if(1 == row.payStatus ){
					switch (toStatus) {
					case 3:
						str = "去清洗";
						break;
					case 4:
						str = "洗涤完成";
						break;
					case 5:
						str = '已付款并取走';
						toStatus = 6;
						break;
					case 7:
						return '<span class="label label-info">已付款</span>';
						break;
					case 8:
						return '<span class="label label-info">已评价</span>';
						break;
					default:
						break;
					}
				}else{
					return str = '<span class="label label-info">未支付</span>';
				}
				return '<span class="btn btn-warning" onclick="updateToStatus(\''+row.id+'\',\''+toStatus+'\')" >'+str+'</span>';
			}},
			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 80 }, 			
			{ label: '审核状态', name: 'checkstatus', index: 'checkstatus', width: 80 ,formatter:function(value, options, row){
				return 1 == value?"已通过":"待审核"; 
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
        url: '../../gxdfworderdetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '衣服类型', name: 'clothName', index: 'goodsid', width: 80 },
			{ label: '数量', name: 'count', index: 'count', width: 80 }, 			
			{ label: '单价', name: 'price', index: 'price', width: 80 ,formatter:function(value, options, row){
				var str = '￥'+value;
				return str; 
			}}, 			
			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 80 ,formatter:function(value, options, row){
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
			status:1,
			payStatus:"",
			createTimeStart:"",
			createTimeEnd:"",
			ordercode:""
		},
		showList: true,
		detailorderid:null,
		title: null,
		gxdfwOrder: {},
		gxdfwOrderdetail:{},
		showdetailList:true,
		typelist:[],
		gxdfworderlog:{
			status:""
		}
		
	},
	watch:{
		'gxdfwOrderdetail.clothestypeid': function(newValue,oldValue){
			if (newValue!='' ) {
				$.get("../../gxdfwclothestype/info/"+newValue, function(r){
			        var price = r.gxdfwClothestype.price;
			        vm.gxdfwOrderdetail.price = price;
			        updatetotal(price,null,null,1);
			    });
			}else{
				vm.itemlist = [];
			}
		},
		'gxdfwOrderdetail.count': function(newValue,oldValue){
			if (newValue!='' ) {
				updatetotal(null,newValue,null,2);
			}else{
				vm.itemlist = [];
			}
		},
		'gxdfwOrderdetail.price': function(newValue,oldValue){
			if (newValue!='' ) {
				updatetotal(null,null,newValue,3);
			}else{
				vm.itemlist = [];
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
			vm.gxdfwOrder = {};
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
			var url = vm.gxdfwOrder.id == null ? "../../gxdfworder/save" : "../../gxdfworder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.gxdfwOrder),
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
				    url: "../../gxdfworder/delete",
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
			$.get("../../gxdfworder/info/"+id, function(r){
                vm.gxdfwOrder = r.gxdfwOrder;
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
		},
		getLogInfo: function (event){
			
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$.get("../../gxdfworderlog/info/"+id, function(r){
                vm.gxdfworderlog = r.gxdfwOrderLog;
                layer.open({
    				type: 1,
    				offset: '50px',
    				skin: 'layui-layer-molv',
    				title: "订单日志状态",
    				area: ['500px', '200px'],
    				shade: 0,
    				shadeClose: false,
    				content: $("#menuLayer2"),
    				btn: ['修改', '取消'],
    				btn1: function (index) {
    					vm.updateLog();
    					layer.close(index);
    		        }
    			});
            });
			
		},
		updateLog: function (event) {
			//更新log
			$.ajax({
				type: "POST",
			    url: "../../gxdfworderlog/update",
			    data: JSON.stringify(vm.gxdfworderlog),
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
		addDetail: function (event) {
			vm.showdetailList = false;
            vm.title = "新增";
            vm.gxdfwOrderdetail={clothestypeid:0,count:0,price:0}
		},
		updateDetail: function (event) {
			var id = getSelectedRowByGid($("#jqGridOD"));
			if(id == null){
				return ;
			}
			vm.showdetailList = false;
			vm.title = "修改";
			
			vm.getInfoDetail(id)
		},
		saveOrUpdateDetail: function (event) {
			vm.gxdfwOrderdetail.orderid= vm.detailorderid;
			var url = vm.gxdfwOrderdetail.id == null ? "../../gxdfworderdetail/save" : "../../gxdfworderdetail/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.gxdfwOrderdetail),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadDetail();
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		delDetail: function (event) {
			var ids = getSelectedRowsByGid($("#jqGridOD"));
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../gxdfworderdetail/delete",
				    data: JSON.stringify(ids),
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
			});
		},
		getInfoDetail: function(id){
			$.get("../../gxdfworderdetail/info/"+id, function(r){
                vm.gxdfwOrderdetail = r.gxdfwOrderdetail;
            });
		},
		checkDetail: function(id){
			var url = "../../gxdfworder/checkDetail"
				var orderid = vm.detailorderid ;
				$.ajax({
					type: "POST",
				    url: url,
				    contentType : "application/x-www-form-urlencoded",
				    data: {"orderid":orderid},
				    success: function(r){
				    	if(r.errCode === 0){
							alert('操作成功', function(index){
								vm.reloadDetail();
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			
		},
		reloadDetail: function (event) {
			vm.showdetailList = true;
			var page = $("#jqGridOD").jqGrid('getGridParam','page');
			$("#jqGridOD").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});

function gettypelist(){
	$.get("../../gxdfwclothestype/typelist", function(r){
        vm.typelist = r.typelist;
    });
}

function updatetotal(oprice,count,nprice,type){
	switch(type){
	case 1: vm.gxdfwOrderdetail.totalprice = vm.gxdfwOrderdetail.count*oprice;break;
	case 2: vm.gxdfwOrderdetail.totalprice = vm.gxdfwOrderdetail.price*count;break;
	case 3: vm.gxdfwOrderdetail.totalprice = vm.gxdfwOrderdetail.count*nprice;break;
	
	}
}
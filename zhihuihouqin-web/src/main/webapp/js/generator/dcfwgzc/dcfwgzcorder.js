function cancelReason(idStr,content){
	alert(idStr+content);
}
function fixmeal(types,start,end){
	var strs= new Array(); //定义一数组
	strs=types.split(","); //字符分割 
	var typestr = "";
	for (var i = 0; i < strs.length; i++) {
		if (strs[i]==1) {
			typestr += "午餐";
		}else{
			typestr += "晚餐";
		}
		if (i!=strs.length-1) {
			typestr += "+";
		}
	}
	alert("餐食类型："+typestr+"</br>预约开始时间："+start+"</br>预约结束时间："+end);
}
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../dcfwgzcorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户id', name: 'uid', index: 'uid', width: 80 }, 			
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 }, 			
//			{ label: '总价', name: 'totalprice', index: 'totalprice', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '预留电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '真实姓名', name: 'realname', index: 'realname', width: 80 },
			{ label: '预定数量/份数', name: 'count', index: 'count', width: 80 },
			{ label: '固定餐类型', name: 'fixedmealtype', index: 'fixedmealtype', width: 80,formatter: function(value, options, row){
				var str = "";
				if (value==1) {
					str = "午";
				}else if(value==2){
					str = "晚";
				}else if(value==""||value==null||value=="null"){
					str ="普通餐"
				}
				return str;
			}},
			{ label: '固定餐开始时间', name: 'fixedmealstartdate', index: 'fixedmealstartdate', width: 80 },
			{ label: '固定餐结束时间', name: 'fixedmealenddate', index: 'fixedmealenddate', width: 80 },
			{ label: '送货地址', name: 'address', index: 'address_id', width: 80 },
			{ label: '订单类型', name: 'type', index: 'type', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">普通餐</span>';
					break;
				case 2:
					str = '<span class="btn btn-success" onclick="fixmeal(\''+row.fixedmealtype+'\',\''+row.fixedmealstartdate+'\',\''+row.fixedmealenddate+'\')" >固定餐</span>'
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '付款状态', name: 'payStatus', index: 'pay_status', width: 80 ,formatter: function(value, options, row){
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
			{ label: '支付方式', name: 'payType', index: 'pay_type', width: 80 ,formatter: function(value, options, row){
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
			{ label: '订单状态', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">已预约</span>';
					break;
				case 3:
					str = '<span class="label label-warning">已完成</span>';
					break;
				case 4:
					str = '<span class="label label-info">已评价</span>';
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
		dcfwGzcOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dcfwGzcOrder = {};
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
			var url = vm.dcfwGzcOrder.id == null ? "../../dcfwgzcorder/save" : "../../dcfwgzcorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dcfwGzcOrder),
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
				    url: "../../dcfwgzcorder/delete",
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
			$.get("../../dcfwgzcorder/info/"+id, function(r){
                vm.dcfwGzcOrder = r.dcfwGzcOrder;
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
		finished: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../../dcfwgzcorder/updateBatch",
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
		},
		exportExcel: function (event) {
			var str = "?";
			var mIds = getSelectedRows();
			if(mIds == null){
				return ;
			}else{
				for (var i = 0; i < mIds.length; i++) {
					if (i!=mIds.length-1) {
						str += "mIds="+mIds[i]+"&";
					}else{
						str += "mIds="+mIds[i];
					}
				}
			}
			
			location.href = "../../dcfwgzcorder/exportExcel"+str;
		}
	}
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tpayinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'uid', index: 'uid', width: 80 }, 			
			{ label: '订单id', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '支付类型', name: 'paytype', index: 'paytype', width: 80,formatter: function(value, options, row){
				if(value == 1){
					return "支付宝";
				}else if(value == 2){
					return "微信";
				}else if(value == 3){
					return "个人余额对私";
				}else if(value == 5){
					return "集体余额对公";
				}else{
					return "未知";
				}
			}}, 			
			{ label: '订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）', name: 'ordertype', index: 'ordertype', width: 80 }, 			
			{ label: '支付状态 ', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				if(value == 0){
					return "付款中";
				}else if(value == 1){
					return "成功";
				}else if(value == 2){
					return "失败";
				} else{
					return "未知";
				}
			}}, 			
			{ label: '支付金额', name: 'paymoney', index: 'paymoney', width: 80 }, 			
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 }, 			
			{ label: '支付订单号', name: 'transactionId', index: 'transaction_id', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '支付时间', name: 'paytime', index: 'paytime', width: 80 }			
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
		showList: true,
		title: null,
		tPayinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tPayinfo = {};
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
			var url = vm.tPayinfo.id == null ? "../../tpayinfo/save" : "../../tpayinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tPayinfo),
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
				    url: "../../tpayinfo/delete",
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
			$.get("../../tpayinfo/info/"+id, function(r){
                vm.tPayinfo = r.tPayinfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
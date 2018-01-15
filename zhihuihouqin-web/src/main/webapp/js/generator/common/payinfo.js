$(function () {
    $("#jqGrid").jqGrid({
        url: '../../payinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'uid', index: 'uid', width: 80 }, 			
			{ label: '订单id', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '支付类型 ', name: 'paytype', index: 'paytype', width: 80 ,formatter: function(value, options, row){
				if(value === 1){
					return '支付宝';
				}else if(value === 2){
					return '微信';
				}else if(value === 3){
					return '个人余额对私';
				}else if(value === 5){
					return '集体余额对公';
				}else{
					return '现金';
				}
				
			}}, //（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）	
			{ label: '订单类型', name: 'ordertype', index: 'ordertype', width: 80 ,formatter: function(value, options, row){
				if(value === 1){
					return '网络报修';
				}else if(value === 2){
					return '医疗服务';
				}else if(value === 3){
					return '图书借阅';
				}else if(value === 4){
					return '餐费充值';
				}else if(value === 5){
					return '团队活动';
				}else if(value === 6){
					return '预约理发';
				}else if(value === 7){
					return '干洗店';
				}else if(value === 8){
					return '办公用品';
				}else if(value === 9){
					return '订餐';
				}else if(value === 10){
					return '营养套餐';
				}else if(value === 11){
					return '信息公布';
				}else if(value === 12){
					return '意见反馈';
				}else if(value === 13){
					return '健康资讯';
				}else if(value === 14){
					return '专家坐诊';
				}else if(value === 15){
					return '工作餐';
				}else if(value === 16){
					return '干洗';
				}else if(value === 17){
					return '医疗服务预约挂号挂号单号';
				}else if(value === 18){
					return '订水服务';
				}else{
					return '未知';
					
				}
				
			}},//(0:付款中，1;成功，2：失败）
			{ label: '支付状态 ', name: 'status', index: 'status', width: 80,formatter: function(value, options, row){
				if(value === 0){
					return '付款中';
				}else if(value === 1){
					return '成功';
				}else if(value === 2){
					return '失败';
				}else{
					return '未支付';
				}
				
			} }, 			
			{ label: '支付金额(单位：元)', name: 'paymoney', index: 'paymoney', width: 80 ,formatter: function(value, options, row){
				if(row.paytype == 2){
					return value/100;
				}
				return value;
			}}, 			
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
		payinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.payinfo = {};
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
			var url = vm.payinfo.id == null ? "../../payinfo/save" : "../../payinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.payinfo),
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
				    url: "../../payinfo/delete",
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
			$.get("../../payinfo/info/"+id, function(r){
                vm.payinfo = r.payinfo;
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
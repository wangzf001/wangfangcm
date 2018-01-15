$(function () {
    $("#jqGrid").jqGrid({
        url: '../../userwalleorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'uid', index: 'uid', width: 80 }, 			
			{ label: '金额', name: 'money', index: 'money', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '支付类型 1: 支付宝，2：微信', name: 'paytype', index: 'paytype', width: 80 ,formatter: function(value, options, row){
				if(value == 1){
					return "支付宝";
				}else if(value == 2){
					return "微信";
				}else{
					return "未知";
				}
			}}, 			
			{ label: '订单状态 1:下单，2：已支付，3：失败', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				if(value == 1){
					return "下单";
				}else if(value == 2){
					return "已支付";
				}else if(value == 3){
					return "失败";
				}else{
					return "未知";
				}
			}}, 			
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 }			
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
		userWalleorder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userWalleorder = {};
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
			var url = vm.userWalleorder.id == null ? "../../userwalleorder/save" : "../../userwalleorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.userWalleorder),
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
				    url: "../../userwalleorder/delete",
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
			$.get("../../userwalleorder/info/"+id, function(r){
                vm.userWalleorder = r.userWalleorder;
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
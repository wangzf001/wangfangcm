$(function () {
    $("#jqGrid").jqGrid({
        url: '../../purchaseaccounlog/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'uid', index: 'uid', width: 80 }, 			
			{ label: '采购账号id', name: 'accountid', index: 'accountid', width: 80 }, 			
			{ label: '订单id', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '订单类型 待完善', name: 'ordertype', index: 'ordertype', width: 80 }, 			
			{ label: '详情', name: 'detail', index: 'detail', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '金额', name: 'money', index: 'money', width: 80 }, 			
			{ label: '订单类型名称', name: 'ordertypename', index: 'ordertypename', width: 80 }, 			
			{ label: '使用类型 1: 收入，2： 支出', name: 'usetype', index: 'usetype', width: 80 }			
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
		purchaseAccounlog: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.purchaseAccounlog = {};
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
			var url = vm.purchaseAccounlog.id == null ? "../../purchaseaccounlog/save" : "../../purchaseaccounlog/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.purchaseAccounlog),
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
				    url: "../../purchaseaccounlog/delete",
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
			$.get("../../purchaseaccounlog/info/"+id, function(r){
                vm.purchaseAccounlog = r.purchaseAccounlog;
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
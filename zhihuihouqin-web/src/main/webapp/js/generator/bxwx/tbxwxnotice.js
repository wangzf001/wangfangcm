$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tbxwxnotice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '发送方类型 1: 用户，2 ： 维修人员', name: 'utype', index: 'utype', width: 80 }, 			
			{ label: '发送方id', name: 'postid', index: 'postid', width: 80 }, 			
			{ label: '接收方id', name: 'getid', index: 'getid', width: 80 }, 			
			{ label: '内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '接收方类型 1: 用户，2 ： 维修人员', name: 'gettype', index: 'gettype', width: 80 }, 			
			{ label: '发送方阅读状态 0 未读，1：已读', name: 'postreadstatus', index: 'postreadstatus', width: 80 }, 			
			{ label: '接收方阅读状态  0 未读，1：已读', name: 'getreadstatus', index: 'getreadstatus', width: 80 }, 			
			{ label: '订单id', name: 'orderid', index: 'orderid', width: 80 }			
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
		tBxwxNotice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tBxwxNotice = {};
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
			var url = vm.tBxwxNotice.id == null ? "../../tbxwxnotice/save" : "../../tbxwxnotice/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tBxwxNotice),
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
				    url: "../../tbxwxnotice/delete",
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
			$.get("../../tbxwxnotice/info/"+id, function(r){
                vm.tBxwxNotice = r.tBxwxNotice;
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
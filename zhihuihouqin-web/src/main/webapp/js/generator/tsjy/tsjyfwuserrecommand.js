$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tsjyfwuserrecommand/recommandlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '作者', name: 'author', index: 'author', width: 80 }, 			
			{ label: '出版社', name: 'publisher', index: 'publisher', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'username', width: 80 ,sortable:false}, 			
			{ label: '手机号', name: 'mobile', index: 'mobile', width: 80 ,sortable:false}, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 },
			{ label: '处理状态', name: 'status', index: 'status', width: 80  , formatter: function(value, options, row){
				var str = '';
				switch (value) {
				
				case 1:
					str = '未处理';
					break;
				case 2:
					str = '已处理';
					break;
				default:
					break;
				}
				return str;
			}}	,			
			{ label: '操作', name: 'status', index: 'status', width: 80  , formatter: function(value, options, row){
				var str = '';
				switch (value) {
				
				case 1:
					str = '<a class="label label-info" onclick="deal(\''+row.id+'\')" >处理</a>';
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
		showList: true,
		title: null,
		tsjyfwUserrecommand: {},
		q:{
			status:"",
			username:"",
			mobile:"",
			starttime:"",
			endtime:"",
			title:""
			
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tsjyfwUserrecommand = {};
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
			var url = vm.tsjyfwUserrecommand.id == null ? "../../tsjyfwuserrecommand/save" : "../../tsjyfwuserrecommand/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tsjyfwUserrecommand),
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
				    url: "../../tsjyfwuserrecommand/delete",
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
			$.get("../../tsjyfwuserrecommand/info/"+id, function(r){
                vm.tsjyfwUserrecommand = r.tsjyfwUserrecommand;
            });
		},
		reload: function (event) {
		
		vm.showList = true;
		vm.q.starttime = $("#dtp_input1").val();
		vm.q.endtime = $("#dtp_input2").val();
		var page = $("#jqGrid").jqGrid('getGridParam','page');
		$("#jqGrid").jqGrid('setGridParam',{
			postData:{
				"status":vm.q.status,
				"starttime":vm.q.starttime,
				"endtime":vm.q.endtime,
				"username":vm.q.username,
				"title":vm.q.title,
				"mobile":vm.q.mobile,
				},
            page:page
        }).trigger("reloadGrid");
		
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});

function deal(id){
	vm.tsjyfwUserrecommand.status = 2;
	vm.tsjyfwUserrecommand.id=id;
	vm.saveOrUpdate();
}
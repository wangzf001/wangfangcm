$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tdhdactivitysign/list',
        datatype: "json",
        colModel: [			
			{ label: 'asId', name: 'asId', index: 'as_id', width: 50, key: true },
			{ label: '', name: 'aId', index: 'a_id', width: 80 }, 			
			{ label: '', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '', name: 'asAvatar', index: 'as_avatar', width: 80 }, 			
			{ label: '', name: 'asUsername', index: 'as_username', width: 80 }, 			
			{ label: '', name: 'asMebile', index: 'as_mebile', width: 80 }			
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
		tdhdActivitysign: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tdhdActivitysign = {};
		},
		update: function (event) {
			var asId = getSelectedRow();
			if(asId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(asId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tdhdActivitysign.asId == null ? "../../tdhdactivitysign/save" : "../../tdhdactivitysign/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tdhdActivitysign),
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
			var asIds = getSelectedRows();
			if(asIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tdhdactivitysign/delete",
				    data: JSON.stringify(asIds),
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
		getInfo: function(asId){
			$.get("../../tdhdactivitysign/info/"+asId, function(r){
                vm.tdhdActivitysign = r.tdhdActivitysign;
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
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../buildingtype/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'typeId', index: 'type_id', width: 50, key: true },
			{ label: '建筑类型名称', name: 'typeName', index: 'type_name', width: 80 }			
        ],
		viewrecords: true,
        height: 585,
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
		buildingType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.buildingType = {};
		},
		update: function (event) {
			var typeId = getSelectedRow();
			if(typeId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(typeId)
		},
		saveOrUpdate: function (event) {
			var url = vm.buildingType.typeId == null ? "../../buildingtype/save" : "../../buildingtype/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.buildingType),
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
			var typeIds = getSelectedRows();
			if(typeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../buildingtype/delete",
				    data: JSON.stringify(typeIds),
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
		getInfo: function(typeId){
			$.get("../../buildingtype/info/"+typeId, function(r){
                vm.buildingType = r.buildingType;
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
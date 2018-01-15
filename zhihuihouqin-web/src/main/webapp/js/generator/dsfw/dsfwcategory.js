$(function () {
    $("#jqGrid").jqGrid({
        url: '../../dsfwcategory/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '规格分类名称', name: 'skucataname', index: 'skucataname', width: 80 }, 			
//			{ label: '规格分类父id 若为0 表示一级分类', name: 'pskucataid', index: 'pskucataid', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 },	
			{ label: '押金', name: 'needdeposit', index: 'needdeposit', width: 80 ,formatter: function(value, options, row){
				var str = "";
				switch (value) {
				case 1:
					str = "需要";
					break;
				case 2:
					str = "不需要";
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
		dsfwCategory: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dsfwCategory = {};
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
			var url = vm.dsfwCategory.id == null ? "../../dsfwcategory/save" : "../../dsfwcategory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dsfwCategory),
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
				    url: "../../dsfwcategory/delete",
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
			$.get("../../dsfwcategory/info/"+id, function(r){
                vm.dsfwCategory = r.dsfwCategory;
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
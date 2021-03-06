$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tdhdactivityimg/list',
        datatype: "json",
        colModel: [			
			{ label: 'aiId', name: 'aiId', index: 'ai_id', width: 50, key: true },
			{ label: '', name: 'aId', index: 'a_id', width: 80 }, 			
			{ label: '', name: 'aiImgPath', index: 'ai_img_path', width: 80 }			
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
		tdhdActivityimg: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tdhdActivityimg = {};
		},
		update: function (event) {
			var aiId = getSelectedRow();
			if(aiId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(aiId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tdhdActivityimg.aiId == null ? "../../tdhdactivityimg/save" : "../../tdhdactivityimg/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tdhdActivityimg),
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
			var aiIds = getSelectedRows();
			if(aiIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tdhdactivityimg/delete",
				    data: JSON.stringify(aiIds),
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
		getInfo: function(aiId){
			$.get("../../tdhdactivityimg/info/"+aiId, function(r){
                vm.tdhdActivityimg = r.tdhdActivityimg;
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
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tsjyfwbookfavorcounview/list',
        datatype: "json",
        colModel: [			
			{ label: 'bookid', name: 'bookid', index: 'bookid', width: 50, key: true },
			{ label: '', name: 'count', index: 'count', width: 80 }			
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
		tsjyfwBookFavorcounview: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tsjyfwBookFavorcounview = {};
		},
		update: function (event) {
			var bookid = getSelectedRow();
			if(bookid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(bookid)
		},
		saveOrUpdate: function (event) {
			var url = vm.tsjyfwBookFavorcounview.bookid == null ? "../../tsjyfwbookfavorcounview/save" : "../../tsjyfwbookfavorcounview/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tsjyfwBookFavorcounview),
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
			var bookids = getSelectedRows();
			if(bookids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tsjyfwbookfavorcounview/delete",
				    data: JSON.stringify(bookids),
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
		getInfo: function(bookid){
			$.get("../../tsjyfwbookfavorcounview/info/"+bookid, function(r){
                vm.tsjyfwBookFavorcounview = r.tsjyfwBookFavorcounview;
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
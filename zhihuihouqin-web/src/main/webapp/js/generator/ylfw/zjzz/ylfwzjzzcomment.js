$(function () {
	var ids = [];
    $("#jqGrid").jqGrid({
        url: '../../../ylfwzjzzcomment/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '图片', name: 'imglist', index: 'imglist', width: 80 ,formatter: function(value, options, row){
				var str = JSON.stringify(value);
				var str = '<ul id="jq'+row.id+'" style="list-style-type: none;margin:0; ">';
				ids.push(row.id);
				$.each(value,function(index,item){
	        		str += '<li style="float:left;"><img alt="" src="'+item+'" class="img-thumbnail" style="width:40px;height:40px"/></li>';
	        	});
				str += '</ul>';
				return str;
			}}, 
			{ label: '评价内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '订单编号', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '用户编号', name: 'uid', index: 'uid', width: 80 }, 			
			{ label: '医生编号', name: 'doctorid', index: 'doctorid', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '总分', name: 'score', index: 'score', width: 80 }, 			
			{ label: '是否匿名(1:是，0：不是)', name: 'anonymous', index: 'anonymous', width: 80 }, 			
			{ label: '服务态度', name: 'servicescore', index: 'servicescore', width: 80 }			
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
	    	$.each(ids,function(index,item){
	    		$('#jq'+item).viewer();
	    	});
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
		ylfwZjzzComment: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.ylfwZjzzComment = {};
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
			var url = vm.ylfwZjzzComment.id == null ? "../../../ylfwzjzzcomment/save" : "../../../ylfwzjzzcomment/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.ylfwZjzzComment),
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
				    url: "../../../ylfwzjzzcomment/delete",
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
			$.get("../../../ylfwzjzzcomment/info/"+id, function(r){
                vm.ylfwZjzzComment = r.ylfwZjzzComment;
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
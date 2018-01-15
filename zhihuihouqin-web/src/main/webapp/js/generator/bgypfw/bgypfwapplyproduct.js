function reason(content){
	alert("&nbsp;&nbsp;&nbsp;<b style='color:red;'>"+content+"</b>");
}
function check(id,content){
	var str = content;
	layer.confirm(str,{
	    btn: ['通过','不通过'], //按钮
	    shade: false ,
	    btn1: function(index, layero){
	    	vm.bgypfwApplyproduct = {
					id:id,
					status:3
			};
	    	vm.saveOrUpdate();
	    	layer.close(index);
	    },
	    btn2: function(index, layero){
	    	vm.bgypfwApplyproduct = {
					id:id,
					status:4
			};
	    	layer.open({
	    		type: 1,
	    		offset: '50px',
	    		skin: 'layui-layer-molv',
	    		title: "不通过原因",
	    		area: ['400px', '320px'],
	    		shade: 0,
	    		shadeClose: false,
	    		content: $("#menuLayer"),
	    		btn: ['提交', '取消'],
	    		btn1: function (index) {
	    			vm.saveOrUpdate();
	    			layer.close(index);
	            }
	    	});
	    }
	});
}
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../bgypfwapplyproduct/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '申请人姓名', name: 'username', index: 'username', width: 80 }, 			
			{ label: '电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '商品名称', name: 'productname', index: 'productname', width: 80 }, 			
			{ label: '商品数量', name: 'productcount', index: 'productcount', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '申请状态', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">未审核</span>';
					break;
				case 2:
					var content = "<table>";
					content += "<tr><td>申请人姓名:</td><td>&nbsp;&nbsp;"+row.username+"</td></tr>";
					content += "<tr><td>电话:</td><td>&nbsp;&nbsp;"+row.mobile+"</td></tr>";
					content += "<tr><td>商品名称:</td><td>&nbsp;&nbsp;"+row.productname+"</td></tr>";
					content += "<tr><td>商品数量:</td><td>&nbsp;&nbsp;"+row.productcount+"</td></tr>";
					content += "<tr><td>备注:</td><td>&nbsp;&nbsp;"+row.remark+"</td></tr>";
					content += "</table>";
					str = '<span class="btn btn-warning" onclick="check('+options.rowId+',\''+content+'\')" >审核中</span>';
					break;
				case 3:
					str = '<span class="label label-success">审核通过</span>';
					break;
				case 4:
					str = '<span class="btn btn-danger" onclick="reason(\''+row.failedReason+'\')" >不通过(查看原因)</span>';
					break;
				default:
					break;
				}
				return str;
			}}			
			/*{ label: '用户', name: 'uid', index: 'uid', width: 100 ,formatter:function(value, options, row){
				var str = '<button class="btn  btn-success" onclick="queryUser('+value+')">实际下单用户</button>';
				return str;
			}}*/
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
		q:{
			keyword: "",
			status: ""
		},
		showList: true,
		title: null,
		bgypfwApplyproduct: {}		
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bgypfwApplyproduct = {};
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
			var url = vm.bgypfwApplyproduct.id == null ? "../../bgypfwapplyproduct/save" : "../../bgypfwapplyproduct/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.bgypfwApplyproduct),
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
				    url: "../../bgypfwapplyproduct/delete",
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
			$.get("../../bgypfwapplyproduct/info/"+id, function(r){
                vm.bgypfwApplyproduct = r.bgypfwApplyproduct;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					"keyword": vm.q.keyword,
					"status": vm.q.status
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
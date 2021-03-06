$(function () {
	getrolesortlist();
	setroletypelist();
    $("#jqGrid").jqGrid({
        url: '../sys/role/list',
        datatype: "json",
        colModel: [			
			{ label: '角色ID', name: 'roleId', index: "role_id", width: 45, key: true },
			{ label: '角色名称', name: 'roleName', index: "role_name", width: 75 },
			{ label: '备注', name: 'remark', width: 100 },
			{ label: '创建时间', name: 'createTime', index: "create_time", width: 80},
			{ label: '角色类型', name: 'rolesort', index: "rolesort", width: 80,  formatter: function(value, options, row){
				var str="";
				if(value != null){
					switch(value){
					case 1: str = "司局管理员";break;
					case 2: str = "处室管理员";break;
					case 10: str = "服务部门管理员";break;
					}
					
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

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;
	
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			roleName: null
		},
		roletypelist:null,
		rolesortlist:[],
		showList: true,
		title:null,
		role:{roletype:null}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.role = {type:1};
			vm.getMenuTree(null);
		},
		update: function () {
			var roleId = getSelectedRow();
			if(roleId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
            vm.getMenuTree(roleId);
		},
		del: function (event) {
			var roleIds = getSelectedRows();
			if(roleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/role/delete",
				    data: JSON.stringify(roleIds),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getRole: function(roleId){
            $.get("../sys/role/info/"+roleId, function(r){
            	vm.role = r.role;
                
                //勾选角色所拥有的菜单
    			var menuIds = vm.role.menuIdList;
    			for(var i=0; i<menuIds.length; i++) {
    				var node = ztree.getNodeByParam("menuId", menuIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		saveOrUpdate: function (event) {
			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				menuIdList.push(nodes[i].menuId);
			}
			vm.role.menuIdList = menuIdList;
			debugger;
			alert(vm.role.roletype);
			var url = vm.role.roleId == null ? "../sys/role/save" : "../sys/role/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.role),
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
		getMenuTree: function(roleId) {
			//加载菜单树
			$.get("../sys/menu/perms", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				//展开所有节点
				ztree.expandAll(true);
				
				if(roleId != null){
					vm.getRole(roleId);
				}
			});
	    },
	    reload: function (event) {
	    	vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'roleName': vm.q.roleName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

function getrolesortlist(){
	debugger
	$.get("../sys/role/rolesortlist", function(r){
		console.log($)
		vm.rolesortlist = r.rolesortlist;
	});
}

function setroletypelist(){
	if(vm.roletypelist == null){
		 $.get("../service/roletypelist", function(r){
         	vm.roletypelist = r.roletypelist;
 		});
	}
}
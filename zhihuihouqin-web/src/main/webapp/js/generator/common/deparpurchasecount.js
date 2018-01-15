$(function () {
	getdepartlist();
	
    $("#jqGrid").jqGrid({
        url: '../../deparpurchasecount/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '部门名称', name: 'departname', index: 'departname', width: 80,sortable:false }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '可用余额', name: 'remain', index: 'remain', width: 80 }, 			
			{ label: '支付密码', name: 'sourcepaypass', index: 'sourcepaypass', width: 80 }, 			
			{ label: '采购账号类型id', name: 'typeid', index: 'typeid', width: 80 ,sortable:false}, 			
			{ label: '采购账号类型', name: 'purchasetype', index: 'purchasetype', width: 80 ,sortable:false}, 			
			{ label: '总金额', name: 'total', index: 'total', width: 80 }	,		
			{ label: '额度列表', name: '', index: 'total', width: 80,sortable:false, formatter: function(value, options, row){
				var str='';
				var typestr="";
				var type = row.servicecode;
				if(type == '8'){
					typestr="到各处室查看";
					str += '<a class="label label-warning">'+typestr+'</a><br/>';
				}else{
					typestr = "人员额度列表";
					str += '<a class="label label-primary" onclick="getlimitlist(\''+row.id+'\')" >'+typestr+'</a><br/>';
				}
				return str;
			}},			
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
        	addtypelist();
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $("#jqlimitGrid").jqGrid({
        url: '../../userpurchaselimit/limitlist',
        datatype: "json",
        colModel: [			
			{ label: 'uid', name: 'uid', index: 'uid', width: 50, key: true },
			{ label: '账户类型id', name: 'purchasetypeid', index: 'purchasetypeid', width: 80 },	
			{ label: '用户名', name: 'username', width: 75,sortable: false  },
			{ label: '真实姓名', name: 'realname', index: 'realname', width: 80 },
			{ label: '单位', name: 'departname', index: 'departname', width: 80 },	
			{ label: '账户类型', name: 'purchasetypename', index: 'purchasetypename', width: 80 },	
			{ label: '额度', name: 'highlimit', index: 'highlimit', width: 80 }, 			
			{ label: '剩余', name: 'remain', index: 'remain', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridlimitPager",
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
        	$("#jqlimitGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    
    $("#jqdepartlimitGrid").jqGrid({
    	url: '../../userpurchaselimit/depart',
    	datatype: "json",
    	colModel: [			
    	           { label: 'uid', name: 'uid', index: 'uid', width: 50, key: true },
    	           { label: '账户类型id', name: 'purchasetypeid', index: 'purchasetypeid', width: 80 },	
    	           { label: '用户名', name: 'username', width: 75,sortable: false  },
    	           { label: '真实姓名', name: 'realname', index: 'realname', width: 80 },
    	           { label: '单位', name: 'departname', index: 'departname', width: 80 },	
    	           { label: '账户类型', name: 'purchasetypename', index: 'purchasetypename', width: 80 },	
    	           { label: '额度', name: 'highlimit', index: 'highlimit', width: 80 }, 			
    	           { label: '剩余', name: 'remain', index: 'remain', width: 80 }			
    	           ],
    	           viewrecords: true,
    	           height: 385,
    	           rowNum: 10,
    	           rowList : [10,30,50],
    	           rownumbers: true, 
    	           rownumWidth: 25, 
    	           autowidth:true,
    	           multiselect: false,
    	           pager: "#jqGriddepartlimitPager",
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
    	        	   $("#jqdepartlimitGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
    	           }
    });
});
var departIndex = null;
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		showupdate:false,
		title: null,
		deparpurchaseCount: {total:0,remain:0},
		departlist:null,
		typelist:null,
		showlimitList:true,
		purchaseid:null,
		purtype:null,
		uid :null,
		userPurchaseLimit:{total:0,remain:0},
		departlimitlist:[],
		q:{
			type:""
		}
		
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showupdate= false;
			vm.showList = false;
			vm.title = "新增";
			vm.deparpurchaseCount = {total:0,remain:0};
		},
		update: function () {
			vm.showupdate = true;
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.deparpurchaseCount.id == null ? "../../deparpurchasecount/save" : "../../deparpurchasecount/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.deparpurchaseCount),
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
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../deparpurchasecount/delete",
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
			$.get("../../deparpurchasecount/info/"+id, function(r){
                vm.deparpurchaseCount = r.deparpurchaseCount;
            });
		},
		reload: function (event) {
			vm.showupdate= false;
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					"type":vm.q.type
					},
                page:page
            }).trigger("reloadGrid");
		},
		querylimit: function () {
			vm.reloadlimit();
		},
		
		updatelimit: function (event) {
			var uid = getSelectedRowByGid($("#jqlimitGrid"));
			if(uid == null){
				return ;
			}
			vm.showlimitList = false;
            vm.title = "修改";
            
            vm.getInfolimit(uid);
		},
		saveOrUpdatelimit: function (event) {
			vm.userPurchaseLimit.purchasetypeid=vm.purtype;
			vm.userPurchaseLimit.uid =vm.uid;
			vm.userPurchaseLimit.type = 1;
			var url = vm.userPurchaseLimit.id == null ? "../../userpurchaselimit/save" : "../../userpurchaselimit/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.userPurchaseLimit),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadlimit();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		dellimit: function (event) {
			var typeid=vm.purtype;
			var ids = getSelectedRowsByGid($("#jqlimitGrid"));
			if(ids == null){
				return ;
			}
			var idstr = "";
			for(var i = 0 ;i < ids.length;i++){
				if(i == ids.length-1){
					idstr+=ids[i];
				}else{
					idstr+=ids[i]+",";
				}
			}
			vm.userPurchaseLimit.ids = idstr;
			vm.userPurchaseLimit.purchasetypeid = typeid;
			confirm('确定要删除选中的记录？', function(){
//				$.post("../../userpurchaselimit/udelete",{"ids":idstr,"typeid":typeid},function(r){
//					if(r.errCode == 0){
//						alert('操作成功', function(index){
//							$("#jqGridlimit").trigger("reloadGrid");
//						});
//					}else{
//						alert(r.msg);
//					}
//					
//				});
				
				$.ajax({
					type: "POST",
				    url: "../../userpurchaselimit/udelete",
				    data: JSON.stringify(vm.userPurchaseLimit),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								vm.reloadlimit();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfolimit: function(uid){
			var type = vm.purtype;
			$.get("../../userpurchaselimit/uinfo",{"uid":uid,"typeid":type}, function(r){
				vm.uid =uid;
				vm.userPurchaseLimit = r.userPurchaseLimit;
            });
		},
		reloadlimit: function (event) {
			vm.showlimitList = true;
			var page = $("#jqlimitGrid").jqGrid('getGridParam','page');
			$("#jqlimitGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		departlimit: function (event) {
			purchaseid = vm.purchaseid  ;
			var pageOD = $("#jqdepartlimitGrid").jqGrid('getGridParam','page');
			$("#jqdepartlimitGrid").jqGrid('setGridParam',{
				postData:{
					"purchaseid":purchaseid,
					"type":1
				},
				page:pageOD
			}).trigger("reloadGrid");
		     
			departIndex = layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "分配列表",
				area: ['800px', '450px'],
				shade: 0,
				shadeClose: false,
				content: $("#departlimitDiv"),
				btn: ['关闭'],
				btn1: function (index) {
					
					layer.close(index);
				}
			});
		},
		ensuredepartlimit: function (event) {
			$.ajax({
				type: "POST",
				contentType : "application/x-www-form-urlencoded",
			    url: "../../userpurchaselimit/confirmDepart",
			    data: {"purchaseId":purchaseid, "type":1},
			    success: function(result){
			    	layer.close(departIndex);
				}
			});
		}
	}
});


function getdepartlist(){
	$.get("../../depart/getdepartlist", function(r){
		vm.departlist = r.departlist;
    });
	
}
function addtypelist(){
	$.get("../../purchasetype/typelist", function(r){
		vm.typelist = r.typelist;
    });
}


function getlimitlist(purchaseid){
	vm.purchaseid = purchaseid;
	var rowData = $("#jqGrid").jqGrid("getRowData",purchaseid);
	var type = rowData.typeid;
	vm.purtype=type;
	var pageOD = $("#jqlimitGrid").jqGrid('getGridParam','page');
	$("#jqlimitGrid").jqGrid('setGridParam',{
		postData:{
			"purchaseid":purchaseid,
			"type":1
		},
		page:pageOD
	}).trigger("reloadGrid");
     
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "额度列表",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#showlimitDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
		}
	});
}


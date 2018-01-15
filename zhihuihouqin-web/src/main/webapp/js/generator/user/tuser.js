var array = [];
function showcarinfo(uid){
	vm.usercaruid=uid;
	var pageOD = $("#jqGridOD").jqGrid('getGridParam','page');
	$("#jqGridOD").jqGrid('setGridParam',{
		postData:{
			"uid":uid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "车辆信息",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#carinfoDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
	
}



function showrightinfo(uid){
	vm.uid = uid;
	var pageOD = $("#jqGridRightOD").jqGrid('getGridParam','page');
	$("#jqGridRightOD").jqGrid('setGridParam',{
		postData:{
			"uid":uid
		},
		page:pageOD
	}).trigger("reloadGrid");
     
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "角色信息",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#rightinfoDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
		}
	});
	
}

$(function () {
    $('.form_datetime3').datetimepicker({
         language:  'zh-CN',
         format: 'yyyy-mm-dd',
         startView: 2,
         minView:2,
         maxView:2,
     });
    
	setpositionListandnationlist();
    $("#jqGrid").jqGrid({
        url: '../../tuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '真实姓名', name: 'realname', index: 'realname', width: 80 },
			{ label: '用户名', name: 'username', index: 'username', width: 120 },
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 140 }, 			
			{ label: '办公室座机', name: 'tel', index: 'tel', width: 80 }, 			
			{ label: '楼栋', name: 'buildname', index: 'buildname', width: 80 },
			{ label: '房间号', name: 'roomnum', index: 'roomnum', width: 80 }, 				
			{ label: '司局', name: 'departname', index: 'departname', width: 80 }, 				
			{ label: '处室', name: 'officename', index: 'officename', width: 80 }, 				
			{ label: '截止日期', name: 'deadline', index: 'deadline', width: 120 }, 				
			{ label: '头像', name: 'photo', index: 'photo', formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},			
			{ label: '职位', name: 'positionName', index: 'positionName', width: 80 }, 			
			{ label: '认证状态', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-default">未审核</span>';
					break;
				case 1:
					str = '<span class="label label-primary">待审核</span>';
					break;
				case 2:
					str = '<span class="label  label-success">已通过</span>';
					break;
				case 3:
					str = '<span class="label label-warning">不通过</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 
			{ label: '是否有效 ', name: 'valid', index: 'valid', width: 80,  formatter: function(value, options, row){
				return value==1?"有效":"无效";
			}},		
			{ label: '其他', name: 'carinfo', index: 'carinfo', width: 80 ,sortable:false,formatter: function(value, options, row){
				var str = "";
				if(row.hascumanager ==1){
					str+='<a class="label label-info" onclick="showcarinfo(\''+row.id+'\')" >车辆信息</a><br/>'
					+'<a class="label label-info" onclick="vm.getAuthInfo(\''+row.id+'\')" >认证信息</a><br/>';
				}
				if(row.haspumanager == 1){
					str += '<a class="label label-info" onclick="showrightinfo(\''+row.id+'\')" >角色信息</a><br/>';
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
    
    
    
    $("#jqGridOD").jqGrid({
        url: '../../tcarusercarinfo/carinfolist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 150, key: true },
			{ label: '车量类型', name: 'cartype', index: 'cartype', width: 150 },
			{ label: '车牌', name: 'carcode', index: 'carcode', width: 150 },
			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPagerOD",
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
        	$("#jqGridOD").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $("#jqGridRightOD").jqGrid({
    	url: '../../baseuserrole/userrolelist',
    	datatype: "json",
    	colModel: [	
					{ label: 'id', name: 'id', index: 'id', width: 200, key: true },
					{ label: '角色名称', name: 'name', index: 'name', width: 200 ,formatter: function(value, options, row){
						if(row.status == 1){
							array.push(row.id);
						}
						return value;
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
    	           pager: "#jqGridRightPagerOD",
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
    	        	   $("#jqGridRightOD").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
    	           },loadComplete: function () {
    	        	     for(var k=0; k<array.length; k++) {
    	        	    	 $("#jqGridRightOD").jqGrid("setSelection",array[k]);
    	        	     }
    	        	     array = [];
    	           }  
    });
    
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/9999',
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.errCode == 0){
            	vm.tUser.photo = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		showauth : true,
		showRight :true,
		showcarinfo:true,
		officelist:null,
		title: null,
		q:{
			username:"",
			realname:"",
			departid:"",
			officeid:""
		},
		tUser: {},
		tUserAuth:{},
		baseUserRole:{},
		baseFrontroles:{},
		tCarUsercarinfo:{},
		rolelist:{},
		uid:null,//设置角色uid
		usercaruid:null,//设置车辆uid
		userauthuid : null ,//设置认证uid
		nationlist:{},
		positionlist : null,
		departlist:{},
		userid:null,
		userPurchaseLimit:{},
		purchasetype:null,
		purchaseuid:null
		
		
	},
	watch:{
		'tUser.departid': function(newValue,oldValue){
			if (newValue!='') {
				$.get("../../tuser/getofficelist?id="+newValue, function(r){
					console.log($)
					vm.officelist = r.officelist;
				});
			}else{
				vm.officelist = [];
			}
			
		},
	'q.departid': function(newValue,oldValue){
		if (newValue!='') {
			$.get("../../tuser/getofficelist?id="+newValue, function(r){
				console.log($)
				vm.officelist = r.officelist;
			});
		}else{
			vm.officelist = [];
		}
		
	}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tUser = {
					photo:null
			};
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
			vm.tUser.deadline = $("#dtp_input3").val();
			var url = vm.tUser.id == null ? "../../tuser/save" : "../../tuser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tUser),
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
		setfthirdadmin: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			var url = "../../office/setfthirdadmin" ;
			$.get(url+"?uid="+id, function(r){
				if(r.errCode === 0){
					alert('操作成功', function(index){
						vm.reload();
					});
				}else{
					alert(r.msg);
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
				    url: "../../tuser/delete",
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
			$.get("../../tuser/info/"+id, function(r){
                vm.tUser = r.tUser;
                $("#dtp_input3").val(vm.tUser.deadline);
            });
		},
		touptateuserpass: function(id){
			vm.userid = vm.tUser.id;
			layer.open({
        		type: 1,
        		offset: '50px',
        		skin: 'layui-layer-molv',
        		title: "修改密码",
        		area: ['800px', '450px'],
        		shade: 0,
        		shadeClose: false,
        		content: $("#showuserpass"),
        		btn: ['确定'],
        		btn1: function (index) {
        			layer.close(index);
                }
        	});
		},
		uptateuserpass: function(){
			vm.tUser.id = vm.userid;
			var url ="../../tuser/updatepass";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tUser),
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
		updateAuth : function(typeid){
			//0: add ,2: scuess ,3: fail
			var url = vm.tUserAuth.id == null ? "../../tuserauth/save" : "../../tuserauth/update";
			if(typeid == 2){
				url = "../../tuserauth/setauthscuess";
			}else if(typeid == 3){
				url = "../../tuserauth/updateAuthStatus";
			}
			vm.tUserAuth.uid = vm.userauthuid;
			vm.tUserAuth.status = typeid;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tUserAuth),
			    success: function(r){
			    	if(r.errCode === 0){
			    		if(vm.tUserAuth.id == null){
			    			vm.tUserAuth.id = r.tUserAuth.id;
			    		}
			    		alert('操作成功');
			    		vm.reload();
					}else{
						alert(r.msg);
					}
				}
			});
						
		},
		delauth: function (event) {
			if(vm.tUserAuth.id == null){
				return ;
			}
			var ids =[];
			ids.push(vm.tUserAuth.id);
			confirm('确定要删除记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tuserauth/delete",
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"username":vm.q.username,
					"realname":vm.q.realname,
					"departid":vm.q.departid,
					"officeid":vm.q.officeid
					
					},
                page:page
            }).trigger("reloadGrid");
			
		},
		getAuthInfo: function(uid){
			vm.userauthuid = uid;
			$.get("../../tuserauth/userauthinfo?uid="+uid, function(r){
                vm.tUserAuth = r.tUserAuth;
                vm.nationlist = r.nationlist;
                vm.positionlist = r.positionlist;
            	vm.departlist = r.departlist;
    			vm.officelist = r.officelist;
    			
            	layer.open({
            		type: 1,
            		offset: '50px',
            		skin: 'layui-layer-molv',
            		title: "认证信息",
            		area: ['800px', '450px'],
            		shade: 0,
            		shadeClose: false,
            		content: $("#showauth"),
            		btn: ['确定'],
            		btn1: function (index) {
            			layer.close(index);
                    }
            	});
        		
            });
		},
		reloaduserCar: function (event) {
			vm.showcarinfo = true;
			var page = $("#jqGridOD").jqGrid('getGridParam','page');
			$("#jqGridOD").jqGrid('setGridParam',{ 
				page:page
			}).trigger("reloadGrid");
		},
		getusercarInfo: function(id){
			$.get("../../tcarusercarinfo/info/"+id, function(r){
                vm.tCarUsercarinfo = r.tCarUsercarinfo;
                vm.showcarinfo = false;
            });
		},
		updateUserRight: function(event){
			 var ids = $("#jqGridRightOD").jqGrid('getDataIDs'); 
			 var selectids = $("#jqGridRightOD").getGridParam("selarrrow");
			 if(selectids == null || ids == null){
				 alert("请至少选择一操作行");return ;
			 }else{
				 $.ajax({
					 type: "POST",
					 contentType : "application/x-www-form-urlencoded",
					 url: "../../baseuserrole/updateuserrole",
					 data: {"ids":getidsStr(ids),"selectids":getidsStr(selectids),"uid":vm.uid},
					 success: function(r){
						 if(r.errCode == 0){
							 alert('操作成功')
						 }else{
							 alert(r.msg);
						 }
					 }
				 });
				 
				 
			 }
		},
		adduserCar: function(event){
			vm.showcarinfo = false;
			vm.title = "新增";
			vm.tCarUsercarinfo = {};
		},
		updateuserCar: function(event){
			var id = $("#jqGridOD").getGridParam("selrow");
			if(id == null){
				return ;
			}
			vm.showcarinfo = false;
            vm.title = "修改";
            
            vm.getusercarInfo(id);
            
		},
		deluserCar: function(event){
			var ids = $("#jqGridOD").getGridParam("selarrrow");
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tcarusercarinfo/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								vm.reloaduserCar();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdateuserCar: function (event) {
			
			var url = vm.tUser.id == null ? "../../tcarusercarinfo/save" : "../../tcarusercarinfo/update";
			vm.tCarUsercarinfo.uid = vm.usercaruid;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tCarUsercarinfo),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloaduserCar();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		managelimit: function(){
			var id = getSelectedRowByGid($("#jqGridRightOD"));
			if(id == null){
				return ;
			}
			vm.purchaseuid = vm.uid;
			vm.purchasetype = id;
			vm.userPurchaseLimit.purchasetypeid = vm.purchasetype;
			vm.userPurchaseLimit.uid= vm.purchaseuid;
				vm.title = "管理";
				vm.getInfolimit1();
				layer.open({
            		type: 1,
            		offset: '50px',
            		skin: 'layui-layer-molv',
            		title: "对公额度管理",
            		area: ['800px', '450px'],
            		shade: 0,
            		shadeClose: false,
            		content: $("#showlimit"),
            		btn: ['确定'],
            		btn1: function (index) {
            			layer.close(index);
                    }
            	});
		},
		dellimit: function () {
			var ids = [];
			ids.push(vm.userPurchaseLimit.id);
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../userpurchaselimit/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdatelimit: function(){
			vm.userPurchaseLimit.purchasetypeid = vm.purchasetype;
			vm.userPurchaseLimit.uid= vm.purchaseuid;
			var url = vm.userPurchaseLimit.id == null ? "../../userpurchaselimit/save" : "../../userpurchaselimit/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.userPurchaseLimit),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.getInfolimit();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getInfolimit: function(id){
			$.get("../../userpurchaselimit/info/"+id, function(r){
				if(r.userPurchaseLimit == null || r.userPurchaseLimit == ''){
					vm.userPurchaseLimit={};
				}else{
					vm.userPurchaseLimit = r.userPurchaseLimit;
				}
            });
		},
		getInfolimit1: function(){
			var url = "../../userpurchaselimit/info1";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.userPurchaseLimit),
			    success: function(r){
			    	if(r.userPurchaseLimit == null || r.userPurchaseLimit == ''){
						vm.userPurchaseLimit={};
					}else{
						vm.userPurchaseLimit = r.userPurchaseLimit;
					}
				}
			});
			
			
		}
		
		
		
		
		
	}
});

function getidsStr(ids){
	var idstr="";
	 for(var i=0;i<ids.length;i++){
		 if(i == 0){
			 idstr+=ids[i];
		 }else{
			 idstr+=","+ids[i];
		 }
	 }
	 return idstr;
}

function setpositionListandnationlist(){
		$.get("../../tuser/getpnlist", function(r){
			vm.positionlist = r.positionlist;
			vm.nationlist = r.nationlist;
			vm.departlist = r.departlist;
			vm.officelist = r.officelist;
			
		});
}
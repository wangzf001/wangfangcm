var array = [];
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tbxwxmender/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '登录编号', name: 'loginid', index: 'loginid', width: 80 }, 			
			{ label: '真实姓名', name: 'realname', index: 'realname', width: 80 }, 
			//{ label: '密码', name: 'password', index: 'password', width: 80 }, 
			{ label: '部门编号', name: 'departid', index: 'departid', width: 80 }, 			
			{ label: '头像', name: 'photo', index: 'photo', width: 80 , formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},						
			{ label: '状态 ', name: 'valid', index: 'valid', width: 80,  formatter: function(value, options, row){
				return value==1?"有效":"无效";
			}},				
			{ label: '电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '分数', name: 'score', index: 'score', width: 80 }, 			
			{ label: '接单量', name: 'count', index: 'count', width: 80 }	,		
			{ label: '擅长信息', name: 'favorinfo', index: 'favorinfo', width: 80 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="showfavorinfo(\''+row.id+'\')" >擅长信息</a>';
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
    
    $("#jqGridFavor").jqGrid({
        url: '../../tbxwxmenderitem/menderitemlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 400, key: true },
			{ label: '名称', name: 'name', index: 'name', width: 400 ,formatter: function(value, options, row){
				if(row.status == 1){
					array.push(row.id);
				}
				return value;
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
        pager: "#jqGridFavorPager",
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
        	$("#jqGridFavor").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },loadComplete: function(){
   	     for(var k=0; k<array.length; k++) {
	    	 $("#jqGridFavor").jqGrid("setSelection",array[k]);
	     }
	     array = [];
        } 
    });
    
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/1',
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
            	vm.tBxwxMender.photo = r.url;
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
		showFavor:true,
		departlist:null,
		menderid:null,
		title: null,
		tBxwxMender: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			setdepartlist();
			vm.title = "新增";
			vm.tBxwxMender = {
					photo:null
			};
			
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			setdepartlist();
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.tBxwxMender.id == null ? "../../tbxwxmender/save" : "../../tbxwxmender/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tBxwxMender),
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
				    url: "../../tbxwxmender/delete",
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
			$.get("../../tbxwxmender/info/"+id, function(r){
                vm.tBxwxMender = r.tBxwxMender;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		updateFavor: function (event) {
			 var ids = $("#jqGridFavor").jqGrid('getDataIDs'); 
			 var selectids = $("#jqGridFavor").getGridParam("selarrrow");
			 if(selectids == null || ids == null){
				 alert("请至少选择一操作行");return ;
			 }else{
				 $.ajax({
					 type: "POST",
					 contentType : "application/x-www-form-urlencoded",
					 url: "../../tbxwxmenderitem/updateFavor",
					 data: {"ids":getidsStr(ids),"selectids":getidsStr(selectids),"menderid":vm.menderid},
					 success: function(r){
						 if(r.errCode == 0){
							 alert('操作成功')
						 }else{
							 alert(r.msg);
						 }
					 }
				 });
				 
				 
			 }
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

function showfavorinfo(menderid){
	vm.menderid = menderid;
	var pageOD = $("#jqGridRightOD").jqGrid('getGridParam','page');
	$("#jqGridFavor").jqGrid('setGridParam',{
		postData:{
			"menderid":menderid
		},
		page:pageOD
	}).trigger("reloadGrid");
     
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "擅长信息",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#showFavorDIV"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
		}
	});
}


function setdepartlist(){
	$.get("../../depart/getdepartlist", function(r){
       vm.departlist = r.departlist;
    });
}
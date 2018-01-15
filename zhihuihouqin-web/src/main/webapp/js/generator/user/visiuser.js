$(function () {
    $("#jqGrid").jqGrid({
        url: '../../visiuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '来访人姓名', name: 'vname', index: 'vname', width: 80 }, 			
			{ label: '来访人手机号', name: 'vmobile', index: 'vmobile', width: 80 }, 			
			{ label: '来访人身份证号', name: 'vidnum', index: 'vidnum', width: 80 }, 			
			{ label: '随行人数', name: 'vpnum', index: 'vpnum', width: 80 }, 			
			{ label: '受访人房间号', name: 'roomnum', index: 'roomnum', width: 80 }, 			
			{ label: '受访人员姓名', name: 'realname', index: 'realname', width: 80 }, 			
			{ label: '受访人员司局', name: 'departname', index: 'realname', width: 80 }, 			
			{ label: '受访人员处室', name: 'officename', index: 'realname', width: 80 }, 			
			{ label: '受访人办公电话', name: 'phonenum', index: 'phonenum', width: 80 }, 			
			{ label: '来访时间', name: 'visittime', index: 'visittime', width: 80 }, 			
			{ label: '订单状态'  , name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch(value){
				case 1 : str = '已预约';break;
				case 3: str = '待评论';break;
				case 3: str = '已评论';break;
				case 5: str = '已取消';break;
				}
				return str;
			}}, 			
			{ label: '管理员审核', name: 'checked', index: 'checked', width: 80 ,formatter: function(value, options, row){
				var str = '';
				if('' == value || null == value){
					str = '待审核';
				}
				switch(value){
				case 0 : str = '待审核';break;
				case 1: str = '通过';break;
				case 2: str = '不通过';break;
				}
				return str;
			}}, 			
			{ label: '被访人审核', name: 'visitchecked', index: 'visitchecked', width: 80 ,formatter: function(value, options, row){
				var str = '';
				if('' == value || null == value){
					str = '待审核';
				}
				switch(value){
				case 0 : str = '待审核';break;
				case 1: str = '通过';break;
				case 2: str = '不通过';break;
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $("#jqdetailGrid").jqGrid({
    	url: '../../visiuser/bylist',
    	datatype: "json",
    	colModel: [			
    	           { label: 'id', name: 'id', index: 'id', width: 50, key: true },
    	           { label: '姓名', name: 'name', index: 'name', width: 80 }, 			
    	           { label: '身份证号', name: 'idcard', index: 'idcard', width: 80 }			
    	          		
    	           ],
    	           viewrecords: true,
    	           height: 385,
    	           rowNum: 10,
    	           rowList : [10,30,50],
    	           rownumbers: true, 
    	           rownumWidth: 25, 
    	           autowidth:true,
    	           multiselect: true,
    	           pager: "#jqdetailGridPager",
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
    	        	   $("#jqdetailGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
    	           }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		failurereason:null,
		title: null,
		visiuser: {},
		q:{
			realname:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.visiuser = {};
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
			var url = vm.visiuser.id == null ? "../../visiuser/save" : "../../visiuser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.visiuser),
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
				    url: "../../visiuser/delete",
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
		check: function (type) {
			var ids = getSelectedRows();
			var url = "../../visiuser/check";
			if(ids == null){
				return ;
			}
			if(2 == type ){
				url ="../../visiuser/uncheck"
			}
			confirm('确定此操作吗？', function(){
				$.ajax({
					type: "POST",
					url: url,
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
		uncheck: function (type) {
			var ids = getSelectedRows();
			var url = "../../visiuser/uncheck";
			if(ids == null){
				return ;
			}
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "不通过原因",
				area: ['800px', '300px'],
				shade: 0,
				shadeClose: false,
				content: $("#reasonDIV"),
				btn: ['确定'],
				btn1: function (index) {
					var idstr = "";
					for(var i =0 ;i < ids.length;i++){
						if(i == 0){
							idstr+=ids[i]
						}else{
							idstr+=","+ids[i];
						}
					}
					layer.close(index);
					var json = {"ids":idstr,"reason":vm.failurereason};
					$.ajax({
						type: "POST",
						url: url,
						data: JSON.stringify(json),
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
		        }
			});
		},
		getInfo: function(id){
			$.get("../../visiuser/info/"+id, function(r){
                vm.visiuser = r.visiuser;
            });
		},
		reload: function (event) {
			vm.showList = true;
			
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                postData:{
					"realname":vm.q.realname,
					},
            }).trigger("reloadGrid");
		},
		bylist: function (event) {
			debugger
			var id = getSelectedRow();
			var pageOD = $("#jqdetailGrid").jqGrid('getGridParam','page');
			$("#jqdetailGrid").jqGrid('setGridParam',{
				postData:{
					"vuid":id
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
				content: $("#detaillist"),
				btn: ['确定'],
				btn1: function (index) {
					layer.close(index);
		        }
			});
			
		}
	}
});
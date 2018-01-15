$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tdhdactivity/list',
        datatype: "json",
        colModel: [			
			{ label: '活动id', name: 'aId', index: 'a_id', width: 50, key: true },
			{ label: '标题', name: 'aTitle', index: 'a_title', width: 80 }, 			
			{ label: '创建时间', name: 'aCreateTime', index: 'a_createtime', width: 150 }, 			
			{ label: '时间', name: 'aTime', index: 'a_time', width: 80 }, 			
			{ label: '地点', name: 'aPlace', index: 'a_place', width: 80 }, 			
			{ label: '电话', name: 'aContactMobile', index: 'a_contact_mobile', width: 80 }, 			
			{ label: '民族', name: 'aNation', index: 'a_nation', width: 80 }		,	
			{ label: '缩略图', name: 'aImg', index: 'a_img', width: 80, formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},				
			{ label: '活动状态', name: 'aStatus', index: 'a_status', width: 80, formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-primary">进行中</span>';
					break;
				case 2:
					str = '<span class="label  label-success">已结束</span>';
					break;
				default:
					break;
				}
				return str;
			
			}},
			
			{ label: '审核状态', name: 'aIsChecked', index: 'a_is_checked', width: 80, formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-primary">待审核</span>';
					break;
				case 1:
					str = '<span class="label  label-success">已通过</span>';
					break;
				case -1:
					str = '<span class="label label-warning">不通过</span>';
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '报名人员', name: 'rightinfo', index: 'rightinfo', width: 80 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="showuserlist(\''+row.aId+'\')" >报名人员</a>';
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
    
    $("#jqGridUser").jqGrid({
        url: '../../tdhdactivitysign/list',
        datatype: "json",
        colModel: [			
					{ label: '编号', name: 'id', index: 'id', width: 50,sortable:false, key: true },
					{ label: '用户名', name: 'username', index: 'username', width: 150,sortable:false},
					{ label: '电话', name: 'mobile', index: 'mobile', width: 150 ,sortable:false}, 			
					{ label: '民族', name: 'nation', index: 'nation', width: 150 ,sortable:false},
					{ label: '性别', name: 'sex', index: 'sex', width: 150 ,sortable:false},
					{ label: '年龄', name: 'age', index: 'age', width: 150 ,sortable:false},
					{ label: '头像', name: 'photo', index: 'photo', width: 150 ,sortable:false, formatter: function(value, options, row){
                        var str = '<img alt="" src="'+value+'" class="img-thumbnail" style="width:50px;height:50px" alt="暂无头像"/>';
                        return str;
                    }},
					{ label: '司局', name: 'depart', index: 'depart', width: 150 ,sortable:false},
					{ label: '处室', name: 'office', index: 'office', width: 150 ,sortable:false},
					{ label: '职位', name: 'postion', index: 'postion', width: 150 ,sortable:false}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridUserPager",
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
        	$("#jqGridUser").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/5',
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
            	vm.tdhdActivity.aImg = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#uploadPic', {
        action: '../../sys/oss/upload/5',
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
        	debugger
            if(r.errCode == 0){
                img= r.url,
            	vm.tdhdActivity.imgEntityList.push(img);
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
		title: null,
		reasonlist:null,
		failurereason:"",
		failurereasonid:"",
		tdhdActivity: {
			aImg:null,
			imgEntityList:[]
		},
		sort:1,
		activityid:null,
		useractivityid:null,
		q:{
			tdhdActivity:{
				title:"",
				status:"",
				checkstatus:"",
				starttime:"",
				endtime:"",
				username:""
			},
			signUser:{
				mobile:"",
				username:"",
				activityid:""
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
			vm.tdhdActivity = {
					aImg:null,
					imgEntityList:[]
			};
		},
		update: function (event) {
			var aId = getSelectedRow();
			if(aId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(aId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tdhdActivity.aId == null ? "../../tdhdactivity/save" : "../../tdhdactivity/update";
			var str = "";
			if(vm.tdhdActivity.imgEntityList.length > 0){
				var array = vm.tdhdActivity.imgEntityList;
				for(var i = 0 ; i < array.length ; i++){
					if(i != 0){
						str+=",";
					}
					str+=array[i];
				}
				vm.tdhdActivity.aPhoto = str;
			}
			
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tdhdActivity),
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
			var aIds = getSelectedRows();
			if(aIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tdhdactivity/delete",
				    data: JSON.stringify(aIds),
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
		getInfo: function(aId){
			$.get("../../tdhdactivity/info/"+aId, function(r){
                vm.tdhdActivity = r.tdhdActivity;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.q.tdhdActivity.starttime = $("#dtp_input1").val();
			vm.q.tdhdActivity.endtime = $("#dtp_input2").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"status":vm.q.tdhdActivity.status,
					"starttime":vm.q.tdhdActivity.starttime,
					"endtime":vm.q.tdhdActivity.endtime,
					"username":vm.q.tdhdActivity.username,
					"checkstatus":vm.q.tdhdActivity.checkstatus,
					"title":vm.q.tdhdActivity.title,
					},

                page:page
            }).trigger("reloadGrid");
		
		},
		delPic: function () {
			vm.tdhdActivity.imgEntityList.pop();
		},
		queryUser: function(){
			vm.showList = true;
			var page = $("#jqGridUser").jqGrid('getGridParam','page');
			$("#jqGridUser").jqGrid('setGridParam',{ 
				postData:{
					"activityid":vm.useractivityid,
					"mobile":vm.q.signUser.mobile,
					"username":vm.q.signUser.username,
				
					},
                page:page
            }).trigger("reloadGrid");
		},
		exportExcel: function (event) {
			var str = "?";
			var mIds = getSelectedRowsByGid($("#jqGridUser"));
			if(mIds == null){
				return ;
			}else{
				for (var i = 0; i < mIds.length; i++) {
					if (i!=mIds.length-1) {
						str += "mIds="+mIds[i]+"&";
					}else{
						str += "mIds="+mIds[i];
					}
				}
			}
			
			location.href = "../../tdhdactivitysign/exportExcel"+str;
		},
		check: function(status){
			
			if(status == -1){
				addreasonlist();
				layer.open({
					type: 1,
					offset: '50px',
					skin: 'layui-layer-molv',
					title: "审核列表",
					area: ['800px', '300px'],
					shade: 0,
					shadeClose: false,
					content: $("#reasonDIV"),
					btn: ['确定'],
					btn1: function (index) {
						layer.close(index);
						vm.saveprocessor(status);
			        }
				});
				
			}else{
				vm.saveprocessor(status);
			}
		},
		saveprocessor: function(status){
			vm.tdhdActivity.id =vm.activityid;
			vm.tdhdActivity.failurereasonid = vm.failurereasonid;
			vm.tdhdActivity.failurereason = vm.failurereason;
			vm.tdhdActivity.aIsChecked =status;
			var url = "../../tdhdactivity/updateCheck";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tdhdActivity),
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
		}
	}
});

function showuserlist(aid){
	vm.useractivityid=aid;
	var pageOD = $("#jqGridUser").jqGrid('getGridParam','page');
	$("#jqGridUser").jqGrid('setGridParam',{
		postData:{
			"activityid":aid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "人员信息",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#UserDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
}
function addreasonlist(){
	$.get("../../tdhdactivity/reasonlist", function(r){
		vm.reasonlist = r.reasonlist;
    });
	
}
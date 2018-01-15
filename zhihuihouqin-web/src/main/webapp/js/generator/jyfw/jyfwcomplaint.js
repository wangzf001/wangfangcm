function feedback(value){
//		alert(value);
		var pageOD = $("#jqGridFB").jqGrid('getGridParam','page');
		$("#jqGridFB").jqGrid('setGridParam',{
			postData:{
				"cid":value
				},
	        page:pageOD
	    }).trigger("reloadGrid");
		vm.jyfwFeedbackMsg = {
			complaintId: value,
			utype:"2",
			readstatus:"0"
		};
		layer.open({
			type: 1,
			offset: '50px',
			skin: 'layui-layer-molv',
			title: "订单详情",
			area: ['800px', '450px'],
			shade: 0,
			shadeClose: false,
			content: $("#menuLayer"),
			btn: ['确定', '取消'],
			btn1: function (index) {
				layer.close(index);
	        }
		});
	}
$(function () {
	var ids = [];
    $("#jqGrid").jqGrid({
        url: '../../jyfwcomplaint/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '投诉内容', name: 'complaintContent', index: 'complaint_content', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'uid', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (row.anonymous) {
				case 0:
					str = value;
					break;
				case 1:
					str = '<span class="label label-danger">匿名</span>';
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '用户头像', name: 'photo', index: 'uid', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},
			{ label: '标签id', name: 'tagName', index: 'tagid', width: 80 ,formatter: function(value, options, row){
				var str = '<span class="label label-success">'+value+'</span>';
				return str;
			}}, 			
			{ label: '建议内容', name: 'suggestContent', index: 'suggest_content', width: 80 }, 			
			{ label: '是否匿名', name: 'anonymous', index: 'anonymous', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-default">非匿名</span>';
					break;
				case 1:
					str = '<span class="label label-danger">匿名</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '图片', name: 'imgs', index: 'imgs', width: 80 ,formatter: function(value, options, row){
				var str = JSON.stringify(value);
				var str = '<ul id="jq'+row.id+'">';
				ids.push(row.id);
				$.each(value,function(index,item){
	        		str += '<li style="float:left;"><img alt="" src="'+item+'" style="width:40px;height:40px"/></li>';
	        	});
				str += '</ul>';
				return str;
			}},
			{ label: '反馈', name: '', index: '', width: 100 ,formatter:function(value, options, row){
				var str = '<button class="btn  btn-success" onclick="feedback('+options.rowId+')">反馈</button>';
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
        	$.each(ids,function(index,item){
        		$('#jq'+item).viewer();
        	});
        }
    });
    $("#jqGridFB").jqGrid({
        url: '../../jyfwfeedbackmsg/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'id', name: 'id', index: 'id', width: 50, key: true },*/
			{ label: '发送方', name: 'utype', index: 'utype', width: 50 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-danger">用户</span>';
					break;
				case 2: 
					str = '<span class="label label-success">我</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '发送内容', name: 'content', index: 'content', width: 150 }, 			
			{ label: '发送时间', name: 'createtime', index: 'createtime', width: 150 }, 			
			{ label: '阅读状态', name: 'readstatus', index: 'readstatus', width: 50 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-danger">为读</span>';
					break;
				case 1: 
					str = '<span class="label label-success">已读</span>';
					break;
				default:
					break;
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
        multiselect: false,
        pager: "#jqGridPagerFB",
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
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username:""
		},
		showList: true,
		title: null,
		jyfwComplaint: {},
		jyfwFeedbackMsg:{
			utype:"2",
			readstatus:"0",
			complaintId:""
				}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.jyfwComplaint = {};
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
			var url = vm.jyfwComplaint.id == null ? "../../jyfwcomplaint/save" : "../../jyfwcomplaint/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.jyfwComplaint),
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
				    url: "../../jyfwcomplaint/delete",
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
			$.get("../../jyfwcomplaint/info/"+id, function(r){
                vm.jyfwComplaint = r.jyfwComplaint;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"username":vm.q.username
					},
                page:page
            }).trigger("reloadGrid");
		},
		reloadFB: function (event) {
			vm.showList = true;
			var page = $("#jqGridFB").jqGrid('getGridParam','page');
			$("#jqGridFB").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		},
		exportExcel: function (event) {
			var str = "?";
			var mIds = getSelectedRows();
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
			
			location.href = "../../jyfwcomplaint/exportExcel"+str;
		},
		responseToUser: function(){
			$.ajax({
				type: "POST",
			    url: "../../jyfwfeedbackmsg/save",
			    data: JSON.stringify(vm.jyfwFeedbackMsg),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadFB();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		}
	}
});
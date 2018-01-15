$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tsjyfworder/orderlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '图书id', name: 'bookid', index: 'bookid', width: 80 }, 			
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 }	,		
			{ label: '借阅人姓名', name: 'uname', index: 'uname', width: 80 }, 			
			{ label: '借阅人电话', name: 'umobile', index: 'umobile', width: 80 }, 			
			{ label: '图书名称', name: 'bookname', index: 'bookname', width: 80 }, 			
			{ label: '借阅状态', name: 'status', index: 'status', width: 80  , formatter:function(value, options, row){
				var str = "";
				switch(value){
				case 1: str='<span class="label label-primary">已预订</span>';break;
				case 3: str='<span class="label label-success">已归还</span>';break;
				case 4: str='<span class="label label-default">待评价</span>';break;
				case 5: str='<span class="label label-warning">已取消</span>';break;
				case 2: str='<span class="label label-info">已借阅</span>';break;
		
				}
				return str;
			}}, 			
			{ label: '下单时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '预约取书时间', name: 'invitegetbooktime', index: 'invitegetbooktime', width: 80 }	,
			{ label: '其他',  width: 160 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="detaillist(\''+row.id+'\')" >借阅信息</a> &nbsp;&nbsp;'
				+'<a class="label label-info" onclick="comment(\''+row.id+'\')" >评论信息</a>'
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
    
    $("#jqGridComment").jqGrid({
        url: '../../tsjyfwcomment/list',
        datatype: "json",
        colModel: [			
					{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
					{ label: '图片', name: 'imgs', index: 'imgs', width: 80 }, 			
					{ label: '评价内容', name: 'content', index: 'content', width: 80 }, 			
					{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
					{ label: '服务分数', name: 'servicescore', index: 'servicescore', width: 80 }		,
					{ label: '总分', name: 'score', index: 'score', width: 80 }	,	
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridCommentPager",
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
        	$("#jqGridComment").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tsjyfwOrder: {},
		bookname:"",
		showComment:true,
		commentorderid:null,
		tsjyfwComment:{},
		
		q:{
			order:{
				status:"",
				ordercode:"",
				starttime:"",
				endtime:"",
				username:"",
				mobile:"",
				bookname:""
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
			vm.tsjyfwOrder = {};
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
			var url = vm.tsjyfwOrder.id == null ? "../../tsjyfworder/save" : "../../tsjyfworder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tsjyfwOrder),
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
				    url: "../../tsjyfworder/delete",
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
			$.get("../../tsjyfworder/info/"+id, function(r){
                vm.tsjyfwOrder = r.tsjyfwOrder;
                vm.bookname = r.bookname;
            });
		},
		reload: function (event) {
			
			vm.showList = true;
			vm.q.order.starttime = $("#dtp_input1").val();
			vm.q.order.endtime = $("#dtp_input2").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"status":vm.q.order.status,
					"starttime":vm.q.order.starttime,
					"endtime":vm.q.order.endtime,
					"username":vm.q.order.username,
					"bookname":vm.q.order.bookname,
					"ordercode":vm.q.order.ordercode,
					"mobile":vm.q.order.mobile
					},
                page:page
            }).trigger("reloadGrid");
			
			
		},
		updateComment: function (event) {
			var id = getSelectedRowByGid($("#jqGridComment"));
			if(id == null){
				return ;
			}
			vm.showComment = false;
            vm.title = "查看";
            
            vm.getInfoComment(id)
		},getInfoComment: function(id){
			$.get("../../tsjyfwcomment/commnetinfo/"+id, function(r){
                vm.tsjyfwComment = r.tsjyfwComment;
            });
		},
	}
});

function detaillist(id){
	vm.showList = false;
    vm.title = "查看";
    vm.getInfo(id)
}


function comment(oid){
	vm.showComment= true;
	vm.commentorderid=oid;
	var pageOD = $("#jqGridComment").jqGrid('getGridParam','page');
	$("#jqGridComment").jqGrid('setGridParam',{
		postData:{
			"orderid":oid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "评价列表",
		area: ['800px', '600px'],
		shade: 0,
		shadeClose: false,
		content: $("#CommentDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
}
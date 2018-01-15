$(function () {
	gettypelist();
	getitemlist(null);
    $("#jqGrid").jqGrid({
        url: '../../lffworder/orderlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 }, 						
			{ label: '用户', name: 'username', index: 'c.name', width: 80 }, 						
			{ label: '理发师', name: 'barbername', index: 'b.name', width: 80 }, 						
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 ,formatter: function(value, options, row){
				var str = '￥'+value;
				return str;
			}}, 				
			{ label: '订单状态', name: 'status', index: 'status', width:80 , formatter:function(value, options, row){
				var str = "";
				switch(value){
				case 1: str='<span class="label label-primary">已预约</span> &nbsp;&nbsp;<a class="label label-info" onclick="jd(\''+row.id+'\')" >接单</a> ';break;
				case 2: str='<span class="label label-info">服务中</span>';break;
				case 3: str='<span class="label label-success">已完成</span>';break;
				case 4: str='<span class="label label-default">待评价</span>';break;
				case 5: str='<span class="label label-warning">已取消</span>';break;
		
				}
				return str;
			}},
			{ label: '支付状态', name: 'paystatus', index: 'paystatus', width: 80, formatter:function(value, options, row){
				return value==1?"已支付":"未支付";
			}},
			{ label: '审核状态', name: 'checkstatus', index: 'checkstatus', width: 80, formatter:function(value, options, row){
				return value==1?"已审核":"待审核";
			}},
			{ label: '其他',  width: 160 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="detaillist(\''+row.id+'\')" >订单信息</a> &nbsp;&nbsp;'
				+'<a class="label label-info" onclick="comment(\''+row.id+'\')" >评论信息</a>';
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
    
    
    $("#jqGridDetail").jqGrid({
        url: '../../lffworderdetail/detaillist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '类型名称', name: 'typename', index: 'typename', width: 80 }, 			
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 ,formatter: function(value, options, row){
				var str = '￥'+value;
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
        pager: "#jqGridDetailPager",
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
        	$("#jqGridDetail").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $("#jqGridComment").jqGrid({
        url: '../../lffwcomment/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '评价内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '总分', name: 'score', index: 'score', width: 80 }, 			
			{ label: '服务分数', name: 'servicescore', index: 'servicescore', width: 80 }, 			
			{ label: '是否匿名(1:是，0：不是)', name: 'anonymous', index: 'anonymous', width: 80,formatter: function(value, options, row){
				return value == 1?"是":"否";
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
		showComment : true,
		title: null,
		lffwOrder: {},
		detailorderid:null,
		commentorderid:null,
		noticeorderid:null,
		showDetail: true,
		lffwComment:{},
		showDetail:true,
		typelist:null,
		itemlist:null,
		lffwOrderdetail:{},
		q:{
			order:{
				status:"",
				starttime:"",
				endtime:"",
				ordercode:"",
				username:"",
				barbername:"",
				paystatus:""
			}
		}
	},
	watch:{
		'lffwOrderdetail.itemtypeid': function(newValue,oldValue){
			if (newValue!='' ) {
				getitemlist(newValue);
			}else{
				vm.itemlist = [];
			}
		},
	'lffwOrderdetail.itemid': function(newValue,oldValue){
		if (newValue!='' ) {
			$.get("../../lffwserviceitem//info/"+newValue, function(r){
				vm.lffwOrderdetail.price= r.lffwServiceitem.price;
			});
			
		}else{
			vm.itemlist = [];
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
			vm.lffwOrder = {};
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
			var url = vm.lffwOrder.id == null ? "../../lffworder/save" : "../../lffworder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.lffwOrder),
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
				    url: "../../lffworder/delete",
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
			$.get("../../lffworder/info/"+id, function(r){
                vm.lffwOrder = r.lffwOrder;
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
					"barbername":vm.q.order.barbername,
					"ordercode":vm.q.order.ordercode,
					"paystatus":vm.q.order.paystatus
					},
                page:page
            }).trigger("reloadGrid");
			
		},updateComment: function (event) {
			var id = getSelectedRowByGid($("#jqGridComment"));
			if(id == null){
				return ;
			}
			vm.showComment = false;
            vm.title = "修改";
            
            vm.getInfoComment(id)
		},getInfoComment: function(id){
			$.get("../../lffwcomment/commnetinfo/"+id, function(r){
                vm.lffwComment = r.lffwComment;
            });
		},
		
		
		checkDetail: function(orderid){
			var url = "../../lffworder/checkDetail"
			var orderid = vm.detailorderid ;
			$.ajax({
				type: "POST",
			    url: url,
			    contentType : "application/x-www-form-urlencoded",
			    data: {"orderid":orderid},
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadDetail();
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		
		
		
		addDetail: function (event) {
			vm.showDetail = false;
            vm.title = "修改";
            
            vm.lffwOrderdetail={itemtypeid:null,itemid:null,price:null};
		},
		updateDetail: function (event) {
			var id = getSelectedRowByGid($("#jqGridDetail"));
			if(id == null){
				return ;
			}
			vm.showDetail = false;
			vm.title = "修改";
			
			vm.getInfoDetail(id)
		},
		saveOrUpdateDetail: function (event) {
			vm.lffwOrderdetail.orderid = vm.detailorderid;
			var url = vm.lffwOrderdetail.id == null ? "../../lffworderdetail/save" : "../../lffworderdetail/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.lffwOrderdetail),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadDetail();
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		delDetail: function (event) {
			var ids = getSelectedRowsByGid($("#jqGridDetail"));
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../lffworderdetail/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqGridDetail").trigger("reloadGrid");
							});
							vm.reload();
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfoDetail: function(id){
			$.get("../../lffworderdetail/info/"+id, function(r){
                vm.lffwOrderdetail = r.lffwOrderdetail;
            });
		},
		reloadDetail: function (event) {
			vm.showDetail = true;
			var page = $("#jqGridDetail").jqGrid('getGridParam','page');
			$("#jqGridDetail").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
		
		
		
	}
});

function jd(id){
	var url = "../../lffworder/jd"
		$.ajax({
			type: "POST",
			url: url,
			contentType : "application/x-www-form-urlencoded",
			data: {"orderid":id},
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
function gettypelist(){
	$.get("../../lffwserviceitemtype/typelist", function(r){
        vm.typelist = r.typelist;
    });
}

function getitemlist(id){
	if(undefined == id ){
		id = '';
	}
	$.get("../../lffwserviceitem/itemlist?typeid="+id, function(r){
		vm.itemlist = r.itemlist;
	});
}

function detaillist(oid){
	vm.detailorderid=oid;
	var pageOD = $("#jqGridDetail").jqGrid('getGridParam','page');
	$("#jqGridDetail").jqGrid('setGridParam',{
		postData:{
			"orderid":oid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		maxmin: true,
		skin: 'layui-layer-molv',
		title: "订单详情列表",
		area: ['650px', '350px'],
		fixed: false,
		shade: 0,
		shadeClose: false,
		content: $("#DetailDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
 
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
		title: "订单详情列表",
		area: ['650px', '350px'],
		fixed: false,
		shade: 0,
		shadeClose: false,
		content: $("#CommentDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
}
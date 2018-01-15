function cancelReason(content){
	alert(content);
}
function check(id,content){
	var str = content;
	layer.confirm(str,{
	    btn: ['通过','不通过'], //按钮
	    shade: false ,
	    btn1: function(index, layero){
	    	vm.hysfwOrder = {
					id:id,
					checkstatus:2
			};
	    	vm.saveOrUpdate();
	    	layer.close(index);
	    },
	    btn2: function(index, layero){
	    	vm.hysfwOrder = {
					id:id,
					checkstatus:3
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
function seeRemarks(content){
    layer.alert(content,{title:'备注详情',closeBtn:0});
}
$(function () {
	var ids = [];
    $("#jqGrid").jqGrid({
        url: '../../hysfworder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 40, key: true },
            { label: '订单号', name: 'ordercode', index: 'ordercode', width: 80 },
            { label: '用户名', name: 'username', index: 'username', width: 80 },
            //{ label: '设备列表', name: 'equipmentListStr', index: 'equipmentids', width: 80 },
			//{ label: '服务列表', name: 'serviceListStr', index: 'serviceids', width: 80 },
            { label: '电话', name: 'mobile', index: 'mobile', width: 80 },
            { label: '会议室', name: 'roomname', index: 'roomname', width: 80 },
            { label: '会议名称', name: 'conferencename', index: 'conferencename', width: 80 },
            //{ label: '参会领导(逗号分隔)', name: 'attentdleader', index: 'attentdleader', width: 80 },
            { label: '会议类型', name: 'conferencetype', index: 'conferencetype', width: 50 },
			{ label: '图片', name: 'imglist', index: 'imglist', width: 80 ,formatter: function(value, options, row){
				var str = JSON.stringify(value);
				var str = '<ul id="jq'+row.id+'" style="list-style-type: none;margin:0; ">';
				ids.push(row.id);
				$.each(value,function(index,item){
	        		str += '<li style="float:left;"><img alt="" src="'+item+'" class="img-thumbnail viewer-toggle" style="width:40px;height:40px"/></li>';
	        	});
				str += '</ul>';
				return str;
			}}, 
			{ label: '参会人数', name: 'attendnum', index: 'attendnum', width: 50 },
            { label: '备注', name: 'remark', index: 'remark', width: 50 ,formatter:function(value,options,row){
            	if(value != null || value != ''){
                    return '<span class="btn btn-success" onclick="seeRemarks(\'' + value +'\')" >查看</span>';
                }else{
                    return '<span class="label label-success">暂无备注</span>';
				}
			}},
            { label: '创建时间', name: 'createtime', index: 'createtime', width: 80 },
            //{ label: '用户id', name: 'uid', index: 'uid', width: 80 },
			{ label: '订单状态', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">已下单</span>';
					break;
				case 2:
					str = '<span class="label label-default">服务中</span>';
					break;
				case 3:
					str = '<span class="label label-success">已完成</span>';
					break;
				case 4:
					str = '<span class="label label-success">已评价</span>';
					break;
				case 5:
					str = '<span class="btn btn-danger" onclick="cancelReason(\''+row.reasoncontent+'\')" >已取消(查看原因)</span>';
					break;
				case 6:
					str = '<span class="label label-default" >已删除</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			//{ label: '', name: 'reasoncontent', index: 'reasoncontent', width: 80 }, 			
			{ label: '审核状态', name: 'checkstatus', index: 'checkstatus', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					var content = "<table>";
					content += "<tr><td>申请人姓名:</td><td>&nbsp;&nbsp;"+row.username+"</td></tr>";
					content += "<tr><td>电话:</td><td>&nbsp;&nbsp;"+row.mobile+"</td></tr>";
					content += "</table>";
					str = '<span class="btn btn-warning" onclick="check('+options.rowId+',\''+content+'\')" >审核中</span>';
					break;
				case 2:
					str = '<span class="label label-success">审核通过</span>';
					break;
				case 3:
					str = '<span class="btn btn-success" onclick="cancelReason(\''+row.failedcontent+'\')">审核不通过</span>';
					break;
				default:
					break;
				}
				return str;
			}}
			//{ label: '审核失败原因', name: 'failedcontent', index: 'failedcontent', width: 80 }
        ],
		viewrecords: true,
        height: 585,
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		hysfwOrder: {},
		q:{
			realname:"",
			mobile:"",
			status:"",
			payStatus:"",
			createTimeStart:"",
			createTimeEnd:"",
			ordercode:""
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.hysfwOrder = {};
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
			var url = vm.hysfwOrder.id == null ? "../../hysfworder/save" : "../../hysfworder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.hysfwOrder),
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
				    url: "../../hysfworder/delete",
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
			$.get("../../hysfworder/info/"+id, function(r){
                vm.hysfwOrder = r.hysfwOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.q.createTimeStart = $("#startTime").val();
			vm.q.createTimeEnd = $("#endTime").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"realname":vm.q.realname,
					"mobile":vm.q.mobile,
					"orderStatus":vm.q.status,
					"createTimeStart":vm.q.createTimeStart,
					"createTimeEnd":vm.q.createTimeEnd,
					"ordercode":vm.q.ordercode
					},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
$(function () {
	var ids = [];
    $("#jqGrid").jqGrid({
        url: '../../dsfwcomment/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户编号', name: 'uid', index: 'uid', width: 80 },
			{ label: '用户名', name: 'username', index: 'username', width: 80 },
			{ label: '联系方式', name: 'mobile', index: 'mobile', width: 80 },
			{ label: '用户头像', name: 'photo', index: 'photo', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},
			{ label: '图片', name: 'imgsArr', index: 'imgs', width: 80 ,formatter: function(value, options, row){
				var str = JSON.stringify(value);
				var str = '<ul id="jq'+row.id+'">';
				ids.push(row.id);
				$.each(value,function(index,item){
	        		str += '<li style="float:left;"><img alt="" src="'+item+'" style="width:40px;height:40px"/></li>';
	        	});
				str += '</ul>';
				return str;
			}},
			{ label: '评价内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '订单编号', name: 'orderid', index: 'orderid', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '总分', name: 'score', index: 'score', width: 80 }, 			
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
			}}
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
		dsfwComment: {},
		mapkeylist:[
		            {value:"id",name:"id"},
					{value:"imgs",name:"图片"},
					{value:"content",name:"评价内容"},
					{value:"orderid",name:"订单编号"},
					{value:"uid",name:"用户编号"},
					{value:"supportid",name:"上传人编号"},
					{value:"createtime",name:"创建时间"},
					{value:"score",name:"总分"},
					{value:"anonymous",name:"是否匿名1是0不是"},
					{value:"username",name:"用户名"},
					{value:"photo",name:"用户头像"}
		            ],
		mapkeys:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dsfwComment = {};
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
			var url = vm.dsfwComment.id == null ? "../../dsfwcomment/save" : "../../dsfwcomment/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dsfwComment),
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
				    url: "../../dsfwcomment/delete",
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
			$.get("../../dsfwcomment/info/"+id, function(r){
                vm.dsfwComment = r.dsfwComment;
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
		exportExcel: function (event) {
			var str = "?";
			var mIds = getSelectedRows();
			if(mIds == null){
				return ;
			}else{
				for (var i = 0; i < mIds.length; i++) {
					str += "mIds="+mIds[i]+"&";
				}
			}
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "请选择要导出的项",
				area: ['450px', '160px'],
				shade: 0,
				shadeClose: false,
				content: $("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					if (vm.mapkeys.length==0) {
						alert("请选择一个字段");
						return ;
					}
					for (var i = 0; i < vm.mapkeys.length; i++) {
						if (i!=vm.mapkeys.length-1) {
							str += "mapkeys="+vm.mapkeys[i]+"&";
						}else{
							str += "mapkeys="+vm.mapkeys[i];
						}
					}
					location.href = "../../dsfwcomment/exportExcel"+str;
					layer.close(index);
		        }
			});
			
		}
	}
});
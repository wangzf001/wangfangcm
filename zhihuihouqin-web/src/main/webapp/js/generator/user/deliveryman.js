$(function () {
    $("#jqGrid").jqGrid({
        url: '../../deliveryman/manlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '用户头像', name: 'photo', index: 'photo', width: 80, formatter: function(value, options, row){
                var str = '<img alt="" src="'+value+'" class="img-thumbnail" style="width:80px;height:80px"/>';
                return str;
            } },
            { label: '用户名', name: 'username', index: 'username', width: 80 },
            { label: '真实姓名', name: 'realname', index: 'realname', width: 80 },
            { label: '手机号', name: 'mobile', index: 'mobile', width: 80 },
            //{ label: '密码', name: 'password', index: 'password', width: 80 },
            { label: '接单量', name: 'ordernum', index: 'ordernum', width: 80 },
            { label: '配送员类型', name: 'servicetypeid', index: 'servicetypeid', width: 80,formatter: function(value, options, row){
                var str = '';
                if(value == 8){
                    str = '办公用品';
				}else if(value == 18){
                    str = '订水服务';
                }else if(value == 7){
                    str = '会议室预定';
                }else {
                	str = '其他';
				}
                return str;
            } },
            { label: '区域id（预留）', name: 'regionId', index: 'region_id', width: 80 },
            { label: '是否有效', name: 'valid', index: 'valid', width: 80,formatter:function (value,option,row) {
				if(value == 1){
					return '有效';
				}
				return '无效';
            } },
            { label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }
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
		title: null,
		deliveryman: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.deliveryman = {};
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
			var url = vm.deliveryman.id == null ? "../../deliveryman/save" : "../../deliveryman/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.deliveryman),
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
				    url: "../../deliveryman/delete",
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
			$.get("../../deliveryman/info/"+id, function(r){
                vm.deliveryman = r.deliveryman;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
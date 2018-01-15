$(function () {
    $("#jqGrid").jqGrid({
        url: '../../healthyinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '资讯来源', name: 'source', index: 'source', width: 80 }, 			
			{ label: '其他',  width: 160 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="comment(\''+row.id+'\')" >评论信息</a>';
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
        url: '../../infocomment/commentlist',
        datatype: "json",
        colModel: [			
                   { label: '评论人', name: 'realname', index: 'realname', width: 100 }, 		
                   { label: '评论时间', name: 'createtime', index: 'createtime', width:150  }, 			
               	{ label: '评论内容', name: 'content', index: 'content', width: 500 }		
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
    
    
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/13',
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
            	vm.healthyinfo.imgs = r.url;
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
		healthyinfo: {},
		showComment:true,
		infoid:null,
		q:{
			order:{
				title:"",
				starttime:"",
				endtime:""
				
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
			vm.healthyinfo = {
					imgs:null
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
			var url = vm.healthyinfo.id == null ? "../../healthyinfo/save" : "../../healthyinfo/update";
			var ue = UE.getEditor('myEditor');
			//对编辑器的操作最好在编辑器ready之后再做
			ue.ready(function() {
			    //获取html内容，返回: <p>hello</p>
			    var html = ue.getContent();
			    vm.healthyinfo.content = html;
			    
			});
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.healthyinfo),
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
				    url: "../../healthyinfo/delete",
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
			$.get("../../healthyinfo/info/"+id, function(r){
                vm.healthyinfo = r.healthyinfo;
                //对编辑器的操作最好在编辑器ready之后再做
                var ue = UE.getEditor('myEditor');
    			ue.ready(function() {
    				ue.setContent(r.healthyinfo.content);
    			});
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.q.starttime = $("#dtp_input1").val();
			vm.q.endtime = $("#dtp_input2").val();
			
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					"title":vm.q.title,
					"starttime":vm.q.starttime,
					"endtime":vm.q.endtime,
					},
                page:page
            }).trigger("reloadGrid");
		}
		
	}
});


function comment(oid){
	vm.showComment= true;
	vm.infoid=oid;
	var pageOD = $("#jqGridComment").jqGrid('getGridParam','page');
	$("#jqGridComment").jqGrid('setGridParam',{
		postData:{
			"infoid":oid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "评论列表",
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
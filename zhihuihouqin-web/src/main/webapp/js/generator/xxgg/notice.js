function orderDetail(){
//		alert(123);
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
    $("#jqGrid").jqGrid({
        url: '../../notice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '分类', name: 'typeName', index: 'typeid', width: 80 }, 			
			{ label: '标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '来源', name: 'source', index: 'source', width: 80 }, 			
			{ label: '内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '图片', name: 'img', index: 'img', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}}, 			
			{ label: '静态文件地址', name: 'url', index: 'url', width: 80 }, 			
			{ label: '是否推荐', name: 'isRecommend', index: 'is_recommend', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-default">非推荐</span>';
					break;
				case 1:
					str = '<span class="label label-danger">推荐</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '收藏数', name: 'favorNum', index: 'favor_num', width: 80 }			
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
    $.get("../../noticetype/getNoticetypeList", function(r){
		console.log($)
		vm.noticetypeList = r.noticetypeList;
	});
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/9',
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
            	vm.notice.img = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			keyword:"",
			createTimeStart:"",
			createTimeEnd:"",
			typeid:""
		},
		noticetypeList:[],
		showList: true,
		title: null,
		notice: {img:""}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.notice = {isRecommend:0,img:""};
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
			var url = vm.notice.id == null ? "../../notice/save" : "../../notice/update";
			var ue = UE.getEditor('myEditor');
			//对编辑器的操作最好在编辑器ready之后再做
			ue.ready(function() {
			    //获取html内容，返回: <p>hello</p>
			    var html = ue.getContent();
			    vm.notice.content = html;
			    
			});
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.notice),
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
				    url: "../../notice/delete",
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
			$.get("../../notice/info/"+id, function(r){
                vm.notice = r.notice;
                //对编辑器的操作最好在编辑器ready之后再做
                var ue = UE.getEditor('myEditor');
    			ue.ready(function() {
    				ue.setContent(r.notice.content);
    			});
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.q.createTimeStart = $("#dtp_input1").val();
			vm.q.createTimeEnd = $("#dtp_input2").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"keyword":vm.q.keyword,
					"createTimeStart":vm.q.createTimeStart,
					"createTimeEnd":vm.q.createTimeEnd,
					"typeid":vm.q.typeid
					},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
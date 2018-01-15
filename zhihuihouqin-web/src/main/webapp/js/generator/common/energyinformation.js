$(function () {
    $("#jqGrid").jqGrid({
        url: '../../energyinformation/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '资讯标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '摘要', name: 'abstracts', index: 'abstracts', width: 80 }, 			
			{ label: '资讯内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '图片', name: 'img', index: 'img', width: 80 ,formatter: function(value, options, row){
                var str = '<img alt="" src="'+value+'" class="img-thumbnail" style="width:100px;height:100px"/>';
                return str;
            } },
			{ label: '收藏量', name: 'favorNum', index: 'favor_num', width: 80 }, 			
			{ label: '是否推荐', name: 'isRecommend', index: 'is_recommend', width: 80,formatter: function(value, options, row){
				if (value){
					return '推荐';
				}else{
                    return '不推荐';
				}
            }  },
			{ label: '资讯来源', name: 'sourceName', index: 'source_name', width: 80 }, 			
			{ label: '来源地址', name: 'sourceUrl', index: 'source_url', width: 80 }, 			
			{ label: '创建时间', name: 'creatTime', index: 'creat_time', width: 80 }			
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
                vm.energyInformation.img = r.url;
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
		energyInformation: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.energyInformation = {};
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
			var url = vm.energyInformation.id == null ? "../../energyinformation/save" : "../../energyinformation/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.energyInformation),
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
				    url: "../../energyinformation/delete",
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
			$.get("../../energyinformation/info/"+id, function(r){
                vm.energyInformation = r.energyInformation;
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
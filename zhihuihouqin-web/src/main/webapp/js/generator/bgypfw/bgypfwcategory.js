$(function () {
    $("#jqGrid").jqGrid({
        url: '../../bgypfwcategory/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '分类名称', name: 'cname', index: 'cname', width: 80 }, 			
			{ label: '父分类', name: 'pname', index: 'pid', width: 80 ,formatter: function(value, options, row){
				var str = '';
				if (value==null||value=='') {
				}else{
					str = '<span class="label label-danger">'+value+'</span>';
				}
				return str;
			}}, 			
			{ label: '分类级别', name: 'cgrade', index: 'cgrade', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-danger">一级分类</span>';
					break;
				case 2:
					str = '<span class="label label-success">二级分类</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '分类图片', name: 'img', index: 'img', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $.get("../../bgypfwcategory/queryCategoryList/1", function(r){
		console.log($)
		vm.categoryList = r.categoryList;
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
            	vm.bgypfwCategory.img = r.url;
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
		bgypfwCategory: {},
		categoryList:[]
	},
	watch: {
		'bgypfwCategory.cgrade': function(newValue,oldValue){
			if (newValue==1) {
				$("#pid").hide(100);
			}else{
				$("#pid").show(100);
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
			vm.bgypfwCategory = {cgrade:1,img:""};
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
			var url = vm.bgypfwCategory.id == null ? "../../bgypfwcategory/save" : "../../bgypfwcategory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.bgypfwCategory),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reload();
							getfirstlist();
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
				    url: "../../bgypfwcategory/delete",
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
			$.get("../../bgypfwcategory/info/"+id, function(r){
                vm.bgypfwCategory = r.bgypfwCategory;
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

function getfirstlist(){
	  $.get("../../bgypfwcategory/queryCategoryList/1", function(r){
			console.log($)
			vm.categoryList = r.categoryList;
		});
	
}

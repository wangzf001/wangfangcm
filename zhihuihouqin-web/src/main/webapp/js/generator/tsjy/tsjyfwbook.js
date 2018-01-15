$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tsjyfwbook/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'title', width: 150 }, 			
			{ label: '作者', name: 'author', index: 'author', width: 80 }, 			
			{ label: '出版社', name: 'publisher', index: 'publisher', width: 80 }, 				
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 150 }, 			
			{ label: '封面', name: 'img', index: 'img', width: 80 , formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},				
			{ label: '分数', name: 'score', index: 'score', width: 80 }, 			
			{ label: '收藏量', name: 'favorcount', index: 'favorcount', width: 80 }, 			
			{ label: '借阅量', name: 'borrowcount', index: 'borrowcount', width: 80 }, 			
			{ label: '状态', name: 'isdel', index: 'isdel', width: 80  , formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-primary">在架</span>';
					break;
				case 1:
					str = '<span class="label  label-info">下架</span>';
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '借阅状态', name: 'bollowstatus', index: 'bollowstatus', width: 80 , formatter: function(value, options, row){
				var str = '';
				switch (value) {
				
				case 1:
					str = '<span class="label label-primary">在架上</span>';
					break;
				case 2:
					str = '<span class="label  label-success">已借出</span>';
					break;
				default:
					break;
				}
				return str;
			}}		
			/*,
			{ label: '其他', name: 'carinfo', index: 'carinfo', width: 80 ,sortable:false,formatter: function(value, options, row){
				var str = "";
				str += '<a class="label label-info" onclick="copylist(\''+row.id+'\')" >正副本</a><br/>';
				return str;
			}}*/
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
    
   /* $("#jqGridbookcopy").jqGrid({
        url: '../../tsjyfwbookcopy/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '搜索词', name: 'searchid', index: 'searchid', width: 80 }, 			
			{ label: '借阅量', name: 'borrowcount', index: 'borrowcount', width: 80 }, 
			{ label: '是否正本 ', name: 'original', index: 'original', width: 80 , formatter: function(value, options, row){
				var str = value == 1?"是":"否";
				return str;
			}},		 			
			{ label: '借阅状态', name: 'borrowstatus', index: 'borrowstatus', width: 80 , formatter: function(value, options, row){
				var str = '';
				switch (value) {
				
				case 1:
					str = '<span class="label label-primary">在架上</span>';
					break;
				case 2:
					str = '<span class="label  label-success">已借出</span>';
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
        multiselect: true,
        pager: "#jqGridbookcopyPager",
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
    });*/
    
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/3',
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
            	vm.tsjyfwBook.img = r.url;
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
		tsjyfwBook: {},
		catalist:null,
		showbookcopyList:true,
		tsjyfwBookCopy: {},
		copybookid:null
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			setcatalist();
			vm.showList = false;
			vm.title = "新增";
			vm.tsjyfwBook = {
					img:null
			};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			setcatalist();
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			debugger
			var url = vm.tsjyfwBook.id == null ? "../../tsjyfwbook/save" : "../../tsjyfwbook/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tsjyfwBook),
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
				    url: "../../tsjyfwbook/delete",
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
			$.get("../../tsjyfwbook/info/"+id, function(r){
                vm.tsjyfwBook = r.tsjyfwBook;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		querycopy: function () {
			vm.reloadcopy();
		},
		addcopy: function(){
			vm.showbookcopyList = false;
			vm.title = "新增";
			vm.tsjyfwBookCopy = {borrowstatus:0};
			vm.tsjyfwBookCopy.bookid = vm.copybookid;
		},
		updatecopy: function (event) {
			var id = getSelectedRowByGid($("#jqGridbookcopy"));
			if(id == null){
				return ;
			}
			vm.showbookcopyList = false;
            vm.title = "修改";
            
            vm.getcopyInfo(id)
		},
		saveOrUpdatecopy: function (event) {
			vm.tsjyfwBookCopy.bookid = vm.copybookid;
			var url = vm.tsjyfwBookCopy.id == null ? "../../tsjyfwbookcopy/save" : "../../tsjyfwbookcopy/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tsjyfwBookCopy),
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
		delcopy: function (event) {
			var ids = getSelectedRowsByGid($("#jqGridbookcopy"));
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tsjyfwbookcopy/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqGridbookcopy").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getcopyInfo: function(id){
			$.get("../../tsjyfwbookcopy/info/"+id, function(r){
                vm.tsjyfwBookCopy = r.tsjyfwBookCopy;
            });
		},
		reloadcopy: function (event) {
			vm.showbookcopyList = true;
			var page = $("#jqGridbookcopy").jqGrid('getGridParam','page');
			$("#jqGridbookcopy").jqGrid('setGridParam',{ 
				postData:{
					"bookid":vm.copybookid,
					
					},
                page:page
            }).trigger("reloadGrid");
		}
		
	}
});

function setcatalist(){
	if(vm.catalist != null){
		return ;
	}
	$.get("../../tsjyfwbooktype/typelist", function(r){
	       vm.catalist = r.catalist;
	    });
	
}

function copylist(uid){
	vm.copybookid=uid;
	var pageOD = $("#jqGridbookcopy").jqGrid('getGridParam','page');
	$("#jqGridbookcopy").jqGrid('setGridParam',{
		postData:{
			"bookid":uid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "车辆信息",
		area: ['800px', '450px'],
		shade: 0,
		shadeClose: false,
		content: $("#showbookcopy"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
	
}
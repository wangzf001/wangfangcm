$(function () {
    $("#jqGrid").jqGrid({
        url: '../../dsfwgoods/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商品名', name: 'name', index: 'name', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 }, 			
			{ label: '图片', name: 'img', index: 'img', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '分类名', name: 'cname', index: 'cid', width: 80 }			
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
    $.get("../../dsfwcategory/queryCategoryList", function(r){
		console.log($)
		vm.cataList = r.cataList;
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
            	vm.dsfwGoods.img = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#uploadExcel', {
    	action: '../../dsfwgoods/inportExcel',
    	name: 'file',
    	autoSubmit:true,
    	responseType:"json",
    	onSubmit:function(file, extension){
    		if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))){
    			alert('只支持xls、xlsx的Excel文档！');
    			return false;
    		}
    	},
    	onComplete : function(file, r){
    		if(r.errCode == 0){
    			alert("导入成功");
    			vm.reload();
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
			createTimeEnd:""
		},
		cataList: [],
		showList: true,
		title: null,
		dsfwGoods: {
			img:""
		},
		mapkeylist:[
		            {value:"id",name:"id"},
					{value:"name",name:"商品名"},
					{value:"price",name:"价格"},
					{value:"img",name:"图片"},
					{value:"createtime",name:"创建时间"},
					{value:"cname",name:"分类名"},
					{value:"cid",name:"分类id"}
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
			vm.dsfwGoods = {
					img:"",
					cid:""
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
			var url = vm.dsfwGoods.id == null ? "../../dsfwgoods/save" : "../../dsfwgoods/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dsfwGoods),
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
				    url: "../../dsfwgoods/delete",
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
			$.get("../../dsfwgoods/info/"+id, function(r){
                vm.dsfwGoods = r.dsfwGoods;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"keyword":vm.q.keyword,
					"createTimeStart":vm.q.createTimeStart,
					"createTimeEnd":vm.q.createTimeEnd
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
					location.href = "../../dsfwgoods/exportExcel"+str;
					layer.close(index);
		        }
			});
			
		}
	}
});
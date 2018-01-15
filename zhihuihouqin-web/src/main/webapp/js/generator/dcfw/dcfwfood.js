$(function () {
    $("#jqGrid").jqGrid({
        url: '../../dcfwfood/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '净菜名', name: 'name', index: 'name', width: 80 }, 			
			{ label: '食材', name: 'original', index: 'original', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 }, 			
			{ label: '图片', name: 'img', index: 'img', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}}, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '剩余/总', name: 'remain', index: 'remain', width: 80 ,formatter: function(value, options, row){
				var str = '<span class="label label-success">'+row.remain+'/'+row.totalcount+'</span>';
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
            	vm.dcfwFood.img = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#uploadExcel', {
    	action: '../../dcfwfood/inportExcel',
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
		addStatus:0,
		showList: true,
		title: null,
		dcfwFood: {
			img:"",
			remain:"",
			totalcount:""
		}
	},
	watch:{
		'dcfwFood.totalcount': function(newValue,oldValue){
			if (vm.addStatus==1) {
				vm.dcfwFood.remain = newValue;
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
			vm.dcfwFood = {
					img:"",
					remain:"",
					totalcount:""
			};
			vm.addStatus = 1;
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
            vm.addStatus = 0;
		},
		saveOrUpdate: function (event) {
			var url = vm.dcfwFood.id == null ? "../../dcfwfood/save" : "../../dcfwfood/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dcfwFood),
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
				    url: "../../dcfwfood/delete",
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
			$.get("../../dcfwfood/info/"+id, function(r){
                vm.dcfwFood = r.dcfwFood;
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
					if (i!=mIds.length-1) {
						str += "mIds="+mIds[i]+"&";
					}else{
						str += "mIds="+mIds[i];
					}
				}
			}
			
			location.href = "../../dcfwfood/exportExcel"+str;
		}
	}
});
function orderDetail(value){
//		alert(value);
	    vm.skuproductid = value;
		var pageOD = $("#jqGridKC").jqGrid('getGridParam','page');
		$("#jqGridKC").jqGrid('setGridParam',{
			postData:{
				"productId":value
				},
	        page:pageOD
	    }).trigger("reloadGrid");
		vm.bgypfwSkuid = {
				productid:value,
				mainimg:"",
				firstcataid:""
				
		};
		layer.open({
			type: 1,
			offset: '50px',
			skin: 'layui-layer-molv',
			title: "订单详情",
			area: ['690px', '550x'],
			shade: 0,
			shadeClose: false,
			content: $("#menuLayer"),
			end: function () {
				vm.reload();
            }
		});
	/*	$.get("../../bgypfwskufirstcata/getCataList", function(r){
			console.log($)
			vm.cataList = r.cataList;
		});*/
	}

function productattr(value){
//		alert(value);
	vm.attrproductid=value; 
	var pageOD = $("#jqproductattrGrid").jqGrid('getGridParam','page');
	$("#jqproductattrGrid").jqGrid('setGridParam',{
		postData:{
			"productid":value
		},
		page:pageOD
	}).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "订单详情",
		area: ['690px', '550x'],
		shade: 0,
		shadeClose: false,
		content: $("#productattr"),
		end: function () {
			vm.reload();
		}
	});
	/*$.get("../../bgypfwskufirstcata/getCataList", function(r){
		console.log($)
		vm.cataList = r.cataList;
	});*/
}
$(function () {
	var ids = [];
    $("#jqGrid").jqGrid({
        url: '../../bgypfwproduct/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 20, key: true },
			{ label: '商品名称', name: 'productname', index: 'productname', width: 50 }, 			
			{ label: '商品图片', name: 'productImg', index: 'mainimg', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}}, 			
			{ label: '价格范围', name: 'pricerange', index: 'pricerange', width: 80 },
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '上架状态', name: 'status', index: 'status', width: 40 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-success">上架</span>';
					break;
				case 2:
					str = '<span class="label label-default">下架</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			/*{ label: '上架时间', name: 'onsaleTime', index: 'onsale_time', width: 80 },*/
			{ label: '二级分类', name: 'categoryTwoName', index: 'category_two_id', width: 50 ,formatter: function(value, options, row){
				var str = '<span class="label label-success">'+value+'</span>';
				return str;
			}},
			{ label: '一级分类', name: 'categoryOneName', index: 'category_one_id', width: 50 ,formatter: function(value, options, row){
				var str = '<span class="label label-danger">'+value+'</span>';
				return str;
			}}, 			
			{ label: '收藏数', name: 'favorNum', index: 'favor_num', width: 40 },
			{ label: '供货单位名', name: 'supplierName', index: 'supplier_name', width: 50 },
			{ label: '商品编号', name: 'productCode', index: 'product_code', width: 80 },
			{ label: '轮播图片', name: 'imgEntityList', index: 'product_code', width: 120 ,formatter: function(value, options, row){
				var str = JSON.stringify(value);
				var str = '<ul id="jq'+row.id+'">';
				ids.push(row.id);
				$.each(value,function(index,item){
	        		str += '<li style="float:left;"><img alt="" src="'+item.img+'" style="width:40px;height:40px"/></li>';
	        	});
				str += '</ul>';
				return str;
			}},
			{ label: '库存', name: '', index: '', width: 50 ,formatter:function(value, options, row){
				var str = '<button class="btn  btn-success" onclick="orderDetail('+options.rowId+')">库存</button><br/>';
				str+='<button class="btn  btn-success" onclick="productattr('+options.rowId+')">属性</button>';
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
        	$.each(ids,function(index,item){
        		$('#jq'+item).viewer();
        	}); 
        }
    });
    $("#jqGridKC").jqGrid({
        url: '../../bgypfwskuid/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '规格名称', name: 'skuname', index: 'skuname', width: 80 }, 			
			{ label: '首图', name: 'mainimg', index: 'mainimg', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}}, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 ,formatter: function(value, options, row){
				var str = '￥'+value;
				return str;
			}}, 			
			{ label: '规格分类名', name: 'cataName', index: 'firstcataid', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '上架状态', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-success">上架</span>';
					break;
				case 2:
					str = '<span class="label label-default">下架</span>';
					break;
				case 3:
					str = '<span class="label label-warning">上架待审核</span>';
					break;
				case 4:
					str = '<span class="label label-danger">上架不通过</span>';
					break;
				case 5:
					str = '<span class="label label-warning">下架待审核</span>';
					break;
				case 6:
					str = '<span class="label label-danger">下架不通过</span>';
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '库存', name: 'store', index: 'store', width: 80 ,formatter: function(value, options, row){
				var str = '';
				if (value<=10) {
					str = '<span class="label label-danger">'+value+'</span>';
				}else if (value>10 && value<=100) {
					str = '<span class="label label-warning">'+value+'</span>';
				}else{
					str = '<span class="label label-success">'+value+'</span>';
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
        pager: "#jqGridPagerKC",
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
        }
    });
    
    $("#jqproductattrGrid").jqGrid({
        url: '../../bgypfwproducattrcata/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '属性分类', name: 'cataname', index: 'cataname', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqproductattrGridPager",
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
        	$("#jqproductattrGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    $.get("../../bgypfwcategory/queryCategoryList/1", function(r){
		console.log($)
		vm.categoryOneList = r.categoryList;
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
            	vm.bgypfwSkuid.mainimg = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#uploadPic', {
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
//            	alert(r.url);
            	var img = {
                		img: r.url,
                		sort:vm.sort++
                	};
            	vm.bgypfwProduct.imgEntityList.push(img);
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#uploadExcel', {
    	action: '../../bgypfwproduct/inportExcel',
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
			minprice:"",
			maxprice:"",
			categoryOneId:"",
			categoryTwoId:"",
			createTimeStart:"",
			createTimeEnd:""
		},
		qKC:{
			keyword:"",
			firstcataid:""
		},
		showListKC: true,
		attrlist:[],
		attrproductid:null,
		skuproductid:null,
		showList: true,
		showproductattrList:true,
		bgypfwProducattrcata:{},
		title: null,
		bgypfwSkuid: {catainfolist:[]},
		bgypfwProduct: {failedreason:""},
		categoryOneList:[],
		categoryTwoList:[],
		categoryTwoList2:[],
		cataList:[],
		sort:1,
		categoryTwoId:""
	},
	watch:{
		'q.categoryOneId': function(newValue,oldValue){
			deubgger
			if (newValue!='') {
				$.get("../../bgypfwcategory/queryCategoryListByPid/"+newValue, function(r){
					console.log($)
					vm.categoryTwoList = r.categoryList;
				});
			}else{
				vm.categoryTwoList = [];
			}
			
		},
		'bgypfwProduct.categoryOneId': function(newValue,oldValue){
			if (newValue!='') {
				$.get("../../bgypfwcategory/queryCategoryListByPid/"+newValue, function(r){
					console.log($)
					vm.categoryTwoList2 = r.categoryList;
					vm.bgypfwProduct.categoryTwoId = vm.categoryTwoId;
					vm.categoryTwoId = "";
				});
			}else{
				vm.bgypfwProduct.categoryTwoId = "";
				vm.categoryTwoList2 = [];
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
			vm.bgypfwProduct = {
					categoryOneId:"",
					categoryTwoId:"",
					imgEntityList:[]
			};
			vm.categoryTwoId = "";
			vm.sort = 1;
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
            attrlist();
		},
		saveOrUpdate: function (event) {
			var url = vm.bgypfwProduct.id == null ? "../../bgypfwproduct/save" : "../../bgypfwproduct/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.bgypfwProduct),
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
				    url: "../../bgypfwproduct/delete",
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
			$.get("../../bgypfwproduct/info/"+id, function(r){
                vm.bgypfwProduct = r.bgypfwProduct;
                vm.categoryTwoId = r.bgypfwProduct.categoryTwoId;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"keyword":vm.q.keyword,
					"minprice":vm.q.minprice,
					"maxprice":vm.q.maxprice,
					"categoryOneId":vm.q.categoryOneId,
					"categoryTwoId":vm.q.categoryTwoId,
					"createTimeStart":vm.q.createTimeStart,
					"createTimeEnd":vm.q.createTimeEnd
					},
                page:page
            }).trigger("reloadGrid");
		},
		delPic: function () {
			vm.bgypfwProduct.imgEntityList.pop();
			if (vm.bgypfwProduct.imgEntityList.length!='') {
				vm.sort = vm.bgypfwProduct.imgEntityList.length+1;
			}else{
				vm.sort = 1;
			}
		},
		queryKC: function () {
			vm.reloadKC();
		},
		addKC: function(){
			vm.showListKC = false;
			vm.bgypfwSkuid={catainfolist:[]};
			getcatalist();
		},
		updateKC: function (event) {
			var ids = $('#jqGridKC').jqGrid('getGridParam','selarrrow');
			if(ids.length!=1){
				alert("请选择一个项进行修改");
				return ;
			}
			vm.showListKC = false;
            vm.getInfoKC(ids[0]);
		},
		saveOrUpdateKC: function (event) {
			vm.bgypfwSkuid.productid = vm.skuproductid;
			var url = vm.bgypfwSkuid.id == null ? "../../bgypfwskuid/save" : "../../bgypfwskuid/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.bgypfwSkuid),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadKC();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		delKC: function (event) {
			var ids = $('#jqGridKC').jqGrid('getGridParam','selarrrow');
			if(ids.length == ''){
				alert("请选择要删除的项");
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../bgypfwskuid/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqGridKC").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfoKC: function(id){
			$.get("../../bgypfwskuid/info/"+id, function(r){
                vm.bgypfwSkuid = r.bgypfwSkuid;
            });
		},
		reloadKC: function (event) {
			vm.showListKC = true;
			var page = $("#jqGridKC").jqGrid('getGridParam','page');
			$("#jqGridKC").jqGrid('setGridParam',{
				postData:{
					"keyword":vm.qKC.keyword,
					"firstcataid":vm.qKC.firstcataid
					},
                page:page
            }).trigger("reloadGrid");
		},
		sale: function (status){
			var ids = $('#jqGridKC').jqGrid('getGridParam','selarrrow');
			if(ids.length == ''){
				alert("请选择要上架的商品");
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../../bgypfwskuid/updateSaleStatus",
			    data: JSON.stringify({"ids":ids,"status":status}),  
		        dataType:"json",
			    success: function(r){
					if(r.errCode == 0){
						alert('操作成功', function(index){
							$("#jqGridKC").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		setMainKC: function (event){
			var ids = $('#jqGridKC').jqGrid('getGridParam','selarrrow');
			if(ids.length != 1){
				alert("请选择要上架的商品");
				return ;
			}
			$.ajax({
				type: "POST",
				url: "../../bgypfwproduct/setMainKC",
				data: JSON.stringify({"id":ids[0],"productId":vm.bgypfwSkuid.productid}),  
				dataType:"json",
				success: function(r){
					if(r.errCode == 0){
						alert('操作成功', function(index){
							$("#jqGridKC").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		check: function (event){
			var ids = $('#jqGridKC').jqGrid('getGridParam','selarrrow');
			if(ids.length == ''){
				alert("请选择要审核的商品");
				return ;
			}
			var str = "请选择是否通过上下架审核";
			layer.confirm(str,{
			    btn: ['通过','不通过'], //按钮
			    shade: false ,
			    btn1: function(index, layero){
//			    	vm.checkOrder(1,ids);
			    	vm.sendCheck(ids,1);
			    	layer.close(index);
			    },
			    btn2: function(index, layero){
			    	layer.open({
			    		type: 1,
			    		offset: '50px',
			    		skin: 'layui-layer-molv',
			    		title: "不通过原因",
			    		area: ['400px', '320px'],
			    		shade: 0,
			    		shadeClose: false,
			    		content: $("#menuLayer2"),
			    		btn: ['提交', '取消'],
			    		btn1: function (index) {
			    			vm.sendCheck(ids,0);
			    			layer.close(index);
			            }
			    	});
			    }
			});
		},
		sendCheck:function(ids,check){
			$.ajax({
				type: "POST",
				url: "../../bgypfwskuid/check",
				data: JSON.stringify({"ids":ids,"check":check,"failedreason":vm.bgypfwSkuid.failedreason}),  
				dataType:"json",
				success: function(r){
					if(r.errCode == 0){
						alert('操作成功', function(index){
							$("#jqGridKC").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		
		queryattr: function () {
			vm.reloadattr();
		},
		addattr: function(){
			attrlist();
			vm.showproductattrList = false;
			vm.title = "新增";
			vm.bgypfwProducattrcata = {};
		},
		updateattr: function (event) {
			var id = getSelectedRowByGid($("#jqproductattrGrid"));
			if(id == null){
				return ;
			}
			vm.showproductattrList = false;
            vm.title = "修改";
            attrlist();
            vm.getInfoattr(id)
		},
		saveOrUpdateattr: function (event) {
			var url = vm.bgypfwProducattrcata.id == null ? "../../bgypfwproducattrcata/save" : "../../bgypfwproducattrcata/update";
			vm.bgypfwProducattrcata.productid = vm.attrproductid;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.bgypfwProducattrcata),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadattr();
							attrlist();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		delattr: function (event) {
			var ids = getSelectedRowsByGid($("#jqproductattrGrid"));
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../bgypfwproducattrcata/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqproductattrGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfoattr: function(id){
			$.get("../../bgypfwproducattrcata/info/"+id, function(r){
                vm.bgypfwProducattrcata = r.bgypfwProducattrcata;
            });
		},
		reloadattr: function (event) {
			vm.showproductattrList = true;
			var page = $("#jqproductattrGrid").jqGrid('getGridParam','page');
			$("#jqproductattrGrid").jqGrid('setGridParam',{ 
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

            location.href = "../../bgypfwproduct/exportExcel"+str;
        }
	}
	});

function attrlist(){
	var productid = vm.attrproductid;
	$.get("../../bgypfwproducattrcata/notattrlist?notproductid="+productid, function(r){
        vm.attrlist = r.attrlist;
    });
}

function getcatalist(){
	var productid = vm.skuproductid;
	$.get("../../bgypfwproducattrcata/catalist?productid="+productid, function(r){
        vm.bgypfwSkuid.catainfolist = r.catalist;
    });
}
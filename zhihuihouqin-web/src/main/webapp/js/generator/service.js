$(function () {
    $("#jqGrid").jqGrid({
        url: '../service/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '服务名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '小图标', name: 'smallimg', index: 'smallimg', width: 80 }, 			
			{ label: '头图', name: 'topphoto', index: 'topphoto', width: 80 }, 			
			{ label: '详情图片', name: 'detailphoto', index: 'detailphoto', width: 80 }, 			
			//{ label: '服务分类id', name: 'servicetypeid', index: 'servicetypeid', width: 80 }, 			
			//{ label: '客服电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			//{ label: '服务价格', name: 'price', index: 'price', width: 80 }, 			
			//{ label: '服务地址', name: 'place', index: 'place', width: 80 }, 			
			//{ label: '营业时间', name: 'businesshour', index: 'businesshour', width: 80 }, 			
			//{ label: '店名', name: 'tradename', index: 'tradename', width: 80 }, 			
			{ label: '唯一标识', name: 'sign', index: 'sign', width: 80 }
			//{ label: '挂号费', name: 'ghprice', index: 'ghprice', width: 80 }, 			
			//{ label: '是否对公账户', name: 'ispubaccount', index: 'ispubaccount', width: 80 }, 			
			//{ label: '工作餐固定餐', name: 'dcfwgzcfixedprice', index: 'dcfwgzcfixedprice', width: 80 }, 			
			//{ label: '工作餐', name: 'dcfwgzcprice', index: 'dcfwgzcprice', width: 80 }, 			
			//{ label: '取餐地点', name: 'dcfwtokenplace', index: 'dcfwtokenplace', width: 80 }, 			
			//{ label: '可预约时间段(周X+周X-12:00~13:30以这种类型输入)', name: 'dcfwtokenordertime', index: 'dcfwtokenordertime', width: 80 }			
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
        action: '../sys/oss/upload/9',
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
            	vm.service.smallimg = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#upload2', {
    	action: '../sys/oss/upload/9',
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
    			vm.service.detailphoto = r.url;
    		}else{
    			alert(r.msg);
    		}
    	}
    });
    new AjaxUpload('#uploadPic', {
        action: '../sys/oss/upload/9',
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
            	vm.service.imgEntityList.push(img);
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
			keyword:""
		},
		showList: true,
		title: null,
		service: {
			imgEntityList:[],
			orderweek:[],
			starttime:"",
			endtime:""
		},
		orderweek:[],
		sort:1
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.service = {
					smallimg:"",
					imgEntityList:[],
					orderweek:[]
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
			var url = vm.service.id == null ? "../service/save" : "../service/update";
			var ue = UE.getEditor('myEditor');
			//对编辑器的操作最好在编辑器ready之后再做
			ue.ready(function() {
			    //获取html内容，返回: <p>hello</p>
			    var html = ue.getContent();
			    vm.service.remind = html;
			    
			});
			vm.service.orderweek = vm.orderweek;
			vm.service.starttime = $("#startTime").val();
			vm.service.endtime = $("#endTime").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.service),
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
				    url: "../service/delete",
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
			$.get("../service/info/"+id, function(r){
				vm.service = r.service;
                //对编辑器的操作最好在编辑器ready之后再做
                var ue = UE.getEditor('myEditor');
    			ue.ready(function() {
    				ue.setContent(r.service.remind!=null?r.service.remind:"请输入");
    			});
    			vm.orderweek = vm.service.orderweek;
    			$("#startTime").val(vm.service.starttime);
    			$("#endTime").val(vm.service.endtime);
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"keyword":vm.q.keyword
					},
                page:page
            }).trigger("reloadGrid");
		},
		delPic: function () {
			vm.service.imgEntityList.pop();
			if (vm.service.imgEntityList.length!='') {
				vm.sort = vm.service.imgEntityList.length+1;
			}else{
				vm.sort = 1;
			}
		}
	}
});
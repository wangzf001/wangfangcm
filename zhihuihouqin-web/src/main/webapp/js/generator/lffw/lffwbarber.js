$(function () {
	
    $("#jqGrid").jqGrid({
        url: '../../lffwbarber/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '头像', name: 'photo', index: 'photo', width: 120, formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},					
			{ label: '姓名', name: 'name', index: 'name', width: 80 }, 			
			{ label: '分数', name: 'score', index: 'score', width: 80 }, 			
			{ label: '电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '是否有效', name: 'valid', index: 'valid', width: 80 , formatter: function(value, options, row){
				return value==1?"有效":"无效";
			}},
			{ label: '作品', name: 'work', index: 'work', width: 80 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="vm.worklist(\''+row.id+'\')" >作品</a>';
			}}, 
			{ label: '计划', name: 'authinfo', index: 'authinfo', width: 80 ,sortable:false,formatter: function(value, options, row){
				return '<a class="label label-info" onclick="vm.schedulelist(\''+row.id+'\')" >计划</a>';
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
    
    $("#jqGridWork").jqGrid({
        url: '../../lffwbarberworks/list',
        datatype: "json",
        colModel: [			
               	{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
    			{ label: '列表页图片', name: 'smallimg', index: 'smallimg', width: 120 ,formatter: function(value, options, row){
    				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
    				return str;
    			}},		
    			{ label: '详情页图片', name: 'detailimg', index: 'detailimg', width: 120,formatter: function(value, options, row){
    				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
    				return str;
    			}},	 			
    			{ label: '简介', name: 'brief', index: 'brief', width: 120 }, 			
    			{ label: '价格', name: 'price', index: 'price', width: 80 }, 			
    			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 150}, 			
    			{ label: '作品名称', name: 'name', index: 'name', width: 120 }, 			
    			{ label: '显示首页', name: 'showonindex', index: 'showonindex', width: 80,formatter: function(value, options, row){
    				var val="";
    				if(value ==1 ){
    					val="显示";
    				}else{
    					val = "不显示"
    				}
    				return val;
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
        pager: "#jqGridPagerOD",
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
        	$("#jqGridWork").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $("#jqGridSchedule").jqGrid({
        url: '../../lffwbarberschedule/schedulelist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 100, key: true },
			{ label: '预约日期', name: 'scheduledate', index: 'scheduledate', width: 200 }, 			
			{ label: '开始时间', name: 'starttime', index: 'starttime', width: 200 }, 			
			{ label: '结束时间', name: 'endtime', index: 'endtime', width: 200}, 			
			{ label: '预约状态 ', name: 'status', index: 'status', width: 200,formatter: function(value, options, row){
				return value==1?"已约":"未约";
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
        pager: "#jqGridSchedulePager",
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
        	$("#jqGridSchedule").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/6',
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
            	vm.lffwBarber.photo = r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#upload1', {
    	action: '../../sys/oss/upload/6',
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
    			vm.lffwBarberWorks.smallimg = r.url;
    		}else{
    			alert(r.msg);
    		}
    	}
    });
    
    new AjaxUpload('#upload2', {
    	action: '../../sys/oss/upload/6',
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
    			vm.lffwBarberWorks.detailimg= r.url;
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
		showwork : true,
		showSchedule : true,
		title: null,
		schedulebarberid:null,
		workbarberid : null,
		positionlist:{},
		lffwBarber: {},
		lffwBarberWorks:{},
		lffwBarberSchedule:{},
		periodlist:{},
		q:{
			schedule:{
				status:"",
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
			addpositionlist();
			vm.showList = false;
			vm.title = "新增";
			vm.lffwBarber = {
					photo:null
			};
		},
		update: function (event) {
			addpositionlist();
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.lffwBarber.id == null ? "../../lffwbarber/save" : "../../lffwbarber/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.lffwBarber),
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
				    url: "../../lffwbarber/delete",
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
			$.get("../../lffwbarber/info/"+id, function(r){
                vm.lffwBarber = r.lffwBarber;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		worklist: function (id){
			vm.workbarberid= id;
			worklist(id);
		},
		schedulelist: function(id){
			vm.schedulebarberid = id;
			schedulelist(id);
		},
		querywork: function () {
			vm.reloadwork();
		},
		addwork: function(){
			vm.showwork = false;
			vm.title = "新增";
			vm.lffwBarberWorks = {
					detailimg:"",
					smallimg:""
			};
		},
		updatework: function (event) {
			var id = getSelectedRowByGid($("#jqGridWork"));
			if(id == null){
				return ;
			}
			vm.showwork = false;
            vm.title = "修改";
            
            vm.getInfowork(id)
		},
		saveOrUpdatework: function (event) {
			vm.lffwBarberWorks.barberid=vm.workbarberid;
			var url = vm.lffwBarberWorks.id == null ? "../../lffwbarberworks/save" : "../../lffwbarberworks/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.lffwBarberWorks),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadwork();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		delwork: function (event) {
			var ids = getSelectedRowsByGid($("#jqGridWork"));
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../lffwbarberworks/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqGridWork").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfowork: function(id){
			$.get("../../lffwbarberworks/info/"+id, function(r){
                vm.lffwBarberWorks = r.lffwBarberWorks;
            });
		},
		reloadwork: function (event) {
			vm.showwork = true;
			var page = $("#jqGridWork").jqGrid('getGridParam','page');
			$("#jqGridWork").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		querySchedule: function () {
			vm.reloadSchedule();
		},
		addSchedule: function(){
			vm.title = "新增";
			vm.lffwBarberSchedule = {};
			vm.getperiodList();
			vm.showSchedule = false;
			
		},
		updateSchedule: function (event) {
			var id = getSelectedRowByGid($("#jqGridSchedule"));
			if(id == null){
				return ;
			}
			vm.showSchedule = false;
            vm.title = "修改";
            
            vm.getInfoSchedule(id)
		},
		saveOrUpdateSchedule: function (event) {
			vm.lffwBarberSchedule.barberid = vm.schedulebarberid;	
			vm.lffwBarberSchedule.startdate = $("#dtp_input3").val();
			vm.lffwBarberSchedule.enddate = $("#dtp_input4").val();
			if(vm.lffwBarberSchedule.startdate == '' || vm.lffwBarberSchedule.enddate == ''){
				alert("请设置日期");return
			}
			vm.lffwBarberSchedule.periodCountList = getPeriodCountList();
			if(vm.lffwBarberSchedule.periodCountList.length == 0 ){
				alert("请选择时间并添加人数");
				return
			}
			var url = vm.lffwBarberSchedule.id == null ? "../../lffwbarberschedule/save" : "../../lffwbarberschedule/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.lffwBarberSchedule),
			    success: function(r){
			    	if(r.errCode === 0){
						alert('操作成功', function(index){
							vm.reloadSchedule();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		delSchedule: function (event) {
			var ids = getSelectedRowsByGid($("#jqGridSchedule"));
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../lffwbarberschedule/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.errCode == 0){
							alert('操作成功', function(index){
								$("#jqGridSchedule").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfoSchedule: function(id){
			$.get("../../lffwbarberschedule/info/"+id, function(r){
                vm.lffwBarberSchedule = r.lffwBarberSchedule;
            });
		},
		reloadSchedule: function (event) {
			vm.showSchedule = true;
			vm.q.schedule.starttime = $("#dtp_input1").val();
			vm.q.schedule.endtime = $("#dtp_input2").val();
			var page = $("#jqGridSchedule").jqGrid('getGridParam','page');
			$("#jqGridSchedule").jqGrid('setGridParam',{
				postData:{
					"status":vm.q.schedule.status,
					"starttime":vm.q.schedule.starttime,
					"endtime":vm.q.schedule.endtime
					},
                page:page
            }).trigger("reloadGrid");
			
		},
		getperiodList: function(){
			
			$.get("../../period/periodlist", function(r){
                vm.periodlist = r.periodlist;
                // addperiod(vm.periodlist);
            });
		}
		
		
	}
});

function addpositionlist(){
	$.get("../../lffwposition/getplist", function(r){
	       vm.positionlist = r.positionlist;
	    });
}

function worklist(uid){
	var pageOD = $("#jqGridWork").jqGrid('getGridParam','page');
	$("#jqGridWork").jqGrid('setGridParam',{
		postData:{
			"barberid":uid
			},
        page:pageOD
    }).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "作品列表",
		area: ['900px', '600px'],
		shade: 0,
		shadeClose: false,
		content: $("#workDiv"),
		btn: ['确定'],
		btn1: function (index) {
			layer.close(index);
        }
	});
	
}

function schedulelist(uid){
	var pageOD = $("#jqGridSchedule").jqGrid('getGridParam','page');
	$("#jqGridSchedule").jqGrid('setGridParam',{
		postData:{
			"barberid":uid
		},
		page:pageOD
	}).trigger("reloadGrid");
	
	layer.open({
		type: 1,
		offset: '50px',
		skin: 'layui-layer-molv',
		title: "计划列表",
		area: ['900px', '600px'],
		shade: 0,
		shadeClose: false,
		content: $("#scheduleDiv"),
		btn: ['确定'],
		zIndex:0,
		btn1: function (index) {
			layer.close(index);
		}
	});
	

}
function addperiod(list){
	var div = $("#scheduledivid");
	var str = ' <table  width="100%"><th></th><th>时间段</th><th>人数(默认1)</th>';
	
	for(var i = 0 ;i < list.length;i++){
		var obj = list[i];
		str+='<tr><td><input type="checkbox" name="period" id="'+obj.id+'"></td><td>'+obj.starttime+':00-'+obj.endtime+':00</td><td><input id="input_id_'+obj.id+'" size="5" ></td></tr>'
	}
	str+='</table>';
	div.html(str);
	
}

function getPeriodCountList(){
	var array = [];
		for(var i = 0 ; i < 3;i++){
			var j = i+1;
			var period={};
			period.periodtypeid = j;
			period.starttime = $("#period_"+j+"_starttime").val();
			period.endtime = $("#period_"+j+"_endtime").val();
			period.count = $("#period_"+j+"_count").val();
			if('' == period.count ){
				period.count =1;
			}
			if(period.starttime != '' && period.endtime != ''){
				array.push(period);
			}
			
		}
	return array;
}
$(function () {
    $("#jqGrid").jqGrid({
        url: '../../../ylfwzjzzexpert/expertlist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '医生名称', name: 'realname', index: 'realname', width: 80 }, 			
			{ label: '职称', name: 'positionname', index: 'positionname', width: 80 }, 			
			{ label: '电话', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '医院', name: 'hospitalname', index: 'hospitalname', width: 80,sortable:false }, 			
			{ label: '创建时间', name: 'createtime', index: 'createtime', width: 80 }, 			
			{ label: '工作时间', name: 'worktime', index: 'worktime', width: 80 }, 			
			{ label: '总预约', name: 'invitetotalcount', index: 'invitetotalcount', width: 80 ,sortable:false  }, 			
			{ label: '可预约', name: 'remaincount', index: 'remaincount', width: 80 ,sortable:false }, 			
			{ label: '是否有效 ', name: 'valid', index: 'valid', width: 80 , formatter: function(value, options, row){
				return value==1?"有效":"无效";
			}},					
			{ label: '图片', name: 'photo', index: 'photo', width: 80, formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
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
    
    $("#jqGridSchedule").jqGrid({
        url: '../../../ylfwzjzzdoctorschedule/schedulelist',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 80, key: true },
			{ label: '预约日期', name: 'scheduledate', index: 'scheduledate', width: 150}, 			
			{ label: '开始时间', name: 'starttime', index: 'starttime', width: 140 }, 			
			{ label: '结束时间', name: 'endtime', index: 'endtime', width: 140 }, 			
			{ label: '预约状态 ', name: 'status', index: 'status', width: 140,formatter: function(value, options, row){
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
        action: '../../../sys/oss/upload/14',
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
            	vm.ylfwZjzzExpert.photo = r.url;
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
		ylfwZjzzExpert:{},
		positionlist:null,
		scheduledoctiorid:null,
		showSchedule :true,
		ylfwZjzzDoctorSchedule:{},
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
			vm.showList = false;
			vm.title = "新增";
			vm.ylfwZjzzExpert = {
					photo : null
			};
			setpositionlist();
			setperiodlist();
			
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			setpositionlist();
			setperiodlist();
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.ylfwZjzzExpert.id == null ? "../../../ylfwzjzzexpert/save" : "../../../ylfwzjzzexpert/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.ylfwZjzzExpert),
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
				    url: "../../../ylfwzjzzexpert/delete",
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
			$.get("../../../ylfwzjzzexpert/info/"+id, function(r){
                vm.ylfwZjzzExpert = r.ylfwZjzzExpert;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		schedulelist: function(id){
			vm.scheduledoctiorid = id;
			schedulelist(id);
		},
		querySchedule: function () {
			vm.reloadSchedule();
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
		addSchedule: function(){
			setperiodlist();
			vm.showSchedule = false;
			vm.title = "新增";
			vm.ylfwZjzzDoctorSchedule = {};
		},
		updateSchedule: function (event) {
			setperiodlist();
			var id = getSelectedRowByGid($("#jqGridSchedule"));
			if(id == null){
				return ;
			}
			vm.showSchedule = false;
            vm.title = "修改";
            
            vm.getInfoSchedule(id)
		},
		saveOrUpdateSchedule: function (event) {
			vm.ylfwZjzzDoctorSchedule.doctorid= vm.scheduledoctiorid;
			vm.ylfwZjzzDoctorSchedule.startdate = $("#dtp_input3").val();
			vm.ylfwZjzzDoctorSchedule.enddate = $("#dtp_input4").val();
			if(vm.ylfwZjzzDoctorSchedule.startdate == '' || vm.ylfwZjzzDoctorSchedule.enddate == ''){
				alert("请设置日期");return;
			}
			vm.ylfwZjzzDoctorSchedule.periodCountList = getPeriodCountList();
			if(vm.ylfwZjzzDoctorSchedule.periodCountList.length == 0 ){
				alert("请选择时间并添加人数");
				return;
			}
			
			var url = vm.ylfwZjzzDoctorSchedule.id == null ? "../../../ylfwzjzzdoctorschedule/save" : "../../../ylfwzjzzdoctorschedule/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.ylfwZjzzDoctorSchedule),
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
				    url: "../../../ylfwzjzzdoctorschedule/delete",
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
			$.get("../../../ylfwzjzzdoctorschedule/info/"+id, function(r){
                vm.ylfwZjzzDoctorSchedule = r.ylfwZjzzDoctorSchedule;
            });
		}
		
	}
});

function setpositionlist(){
	$.get("../../../ylfwposition/positionlist", function(r){
	       vm.positionlist = r.positionlist;
	    });
	
}

function setperiodlist(){
	$.get("../../../period/periodlist", function(r){
	       vm.periodlist = r.periodlist;
	       addperiod(vm.periodlist);
	    });
}

function schedulelist(uid){
	var pageOD = $("#jqGridSchedule").jqGrid('getGridParam','page');
	$("#jqGridSchedule").jqGrid('setGridParam',{
		postData:{
			"doctorid":uid
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
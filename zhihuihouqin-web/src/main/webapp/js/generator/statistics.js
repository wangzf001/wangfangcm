$(function() {
	$.get("../statistics/roletypeList", function(r) {
		var type = r.data[0].type;
		for (var i = 0; i < r.data.length; i++) {
			$("#service").append("<option value="+r.data[i].type+">"+r.data[i].name+"</option>");
		}
		$("#jqGrid").jqGrid({
	        url: '../statistics/list',
	        datatype: "json",
	        postData: {type: type},
	        colModel: [			
				{ label: '用户ID', name: 'uid', index: 'uid', width: 50, key: true },
				{ label: '用户名', name: 'userName', index: 'user_name', width: 50, key: true },
				{ label: '差评', name: 'negative', index: 'negative', width: 80 },
				{ label: '中评', name: 'commonly', index: 'commonly', width: 80 },
				{ label: '好评', name: 'good', index: 'good', width: 80 },
				{ label: '服务类型', name: 'type', index: 'type', width: 80 },
	        ],
			viewrecords: true,
	        height: 385,
	        rowNum: 10,
			rowList : [10,30,50],
	        rownumbers: true, 
	        rownumWidth: 25, 
	        autowidth:true,
	        multiselect: false,
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
			var type = $("#service").val();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"user_name":vm.q.user_name,
					"type":type
					},
                page:page
            }).trigger("reloadGrid");
		},
		onChange:function(event){
			var type = event.target!= undefined ? event.target.value:event;
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'type': type}
            }).trigger("reloadGrid");
		}
	}
});
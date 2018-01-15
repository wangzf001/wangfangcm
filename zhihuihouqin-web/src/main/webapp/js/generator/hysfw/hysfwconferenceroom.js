$(function () {
	var ids = [];
    $("#jqGrid").jqGrid({
        url: '../../hysfwconferenceroom/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '会议室名', name: 'name', index: 'name', width: 80 },
			{ label: '容量', name: 'capacity', index: 'capacity', width: 80 ,formatter: function(value, options, row){
				var str = '<span class="label label-success">可容纳'+value+'人</span>';
				return str;
			}},
			{ label: '预定量', name: 'usenumber', index: 'usenumber', width: 80 ,formatter: function(value, options, row){
				var str = '<span class="label label-success">已被预订'+value+'次</span>';
				return str;
			}},
			{ label: '地址', name: 'address', index: 'address', width: 80 },
			{ label: '介绍', name: 'introduce', index: 'introduce', width: 80 },
			{ label: '轮播图', name: 'imgList', index: 'imgs', width: 80 ,formatter: function(value, options, row){
				var str = JSON.stringify(value);
				var str = '<ul id="jq'+row.id+'">';
				ids.push(row.id);
				$.each(value,function(index,item){
	        		str += '<li style="float:left;"><img alt="" src="'+item+'" style="width:40px;height:40px"/></li>';
	        	});
				str += '</ul>';
				return str;
			}},
			{ label: '开放时段', name: 'timeserving', index: 'timeserving', width: 80 ,formatter: function(value, options, row){
				var str = "";
				switch (value) {
				case 1:
					str = '<span class="label label-success">上午</span>';
					break;
				case 2:
					str = '<span class="label label-success">下午</span>';
					break;
				case 3:
					str = '<span class="label label-success">全天</span>';
					break;
				default:
					break;
				}
				return str;
			}},
			{ label: '小图', name: 'photo', index: 'photo', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}},
			{ label: '状态', name: 'status', index: 'status', width: 80 ,formatter: function(value, options, row){
				var str = "";
				switch (value) {
				case 1:
					str = '<span class="label label-success">可预订</span>';
					break;
				case 2:
					str = '<span class="label label-success">使用中</span>';
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
            	vm.hysfwConferenceRoom.photo = r.url;
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
            	vm.hysfwConferenceRoom.imgEntityList.push(img);
            }else{
                alert(r.msg);
            }
        }
    });
    $.get("../../hysfwconferenceservice/getList", function(r){
		console.log($)
		vm.serviceList = r.list;
	});
    $.get("../../hysfwconferenceequipment/getList", function(r){
		console.log($)
		vm.equipmentList = r.list;
	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		hysfwConferenceRoom: {
			serviceList:[],
			equipmentList:[]
		},
		sort:1,
		serviceList:[],
		setServiceList:[],
		equipmentList:[],
		setEquipmentList:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.hysfwConferenceRoom = {
					imgEntityList:[],
					serviceList:[],
					equipmentList:[]
			};
			vm.setServiceList= [];
			vm.setEquipmentList= [];
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
			var url = vm.hysfwConferenceRoom.id == null ? "../../hysfwconferenceroom/save" : "../../hysfwconferenceroom/update";
			vm.hysfwConferenceRoom.serviceList = vm.setServiceList;
			vm.hysfwConferenceRoom.equipmentList = vm.setEquipmentList;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.hysfwConferenceRoom),
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
				    url: "../../hysfwconferenceroom/delete",
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
			$.get("../../hysfwconferenceroom/info/"+id, function(r){
                vm.hysfwConferenceRoom = r.hysfwConferenceRoom;
//                alert(JSON.stringify(r.hysfwConferenceRoom.serviceList));
                vm.setServiceList = r.hysfwConferenceRoom.serviceList;
                vm.setEquipmentList = r.hysfwConferenceRoom.equipmentList;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		delPic: function () {
			vm.hysfwConferenceRoom.imgEntityList.pop();
			if (vm.hysfwConferenceRoom.imgEntityList.length!='') {
				vm.sort = vm.hysfwConferenceRoom.imgEntityList.length+1;
			}else{
				vm.sort = 1;
			}
		},
	}
});
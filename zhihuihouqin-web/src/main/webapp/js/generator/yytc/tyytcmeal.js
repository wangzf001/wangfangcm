$(function () {
    $("#jqGrid").jqGrid({
        url: '../../tyytcmeal/list',
        datatype: "json",
        colModel: [			
			{ label: 'mId', name: 'mId', index: 'm_id', width: 50, key: true },
			{ label: '题目', name: 'mTitle', index: 'm_title', width: 80 }, 			
			{ label: '创建时间', name: 'mCreateTime', index: 'm_create_time', width: 80 }, 			
			{ label: '来源', name: 'mSource', index: 'm_source', width: 80 }, 			
			{ label: '菜单类型', name: 'mType', index: 'm_type', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">今日菜谱</span>';
					break;
				case 2:
					str = '<span class="label label-warning">营养套餐</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '时间类型', name: 'mTimeType', index: 'm_time_type', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 1:
					str = '<span class="label label-default">早餐</span>';
					break;
				case 2:
					str = '<span class="label label-warning">午餐</span>';
					break;
				case 3:
					str = '<span class="label label-info">晚餐</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '推荐', name: 'mRecommend', index: 'm_recommend', width: 80 ,formatter: function(value, options, row){
				var str = '';
				switch (value) {
				case 0:
					str = '<span class="label label-default">未推荐</span>';
					break;
				case 1:
					str = '<span class="label label-info">已推荐</span>';
					break;
				default:
					break;
				}
				return str;
			}}, 			
			{ label: '菜单配图', name: 'mImg', index: 'm_img', width: 80 ,formatter: function(value, options, row){
				var str = '<img alt="" src="'+value+'" style="width:100px;height:100px"/>';
				return str;
			}}, 			
			{ label: '步骤', name: 'stepStr', index: 'm_img', width: 80 }, 			
			{ label: '原料', name: 'ingredientStr', index: 'm_img', width: 80 }, 			
			{ label: '菜单简介', name: 'mContent', index: 'm_content', width: 80 }, 			
			{ label: '浏览人数', name: 'mScanNum', index: 'm_scan_num', width: 80 }, 			
			{ label: '点赞人数', name: 'mPraiseNum', index: 'm_praise_num', width: 80 }, 			
			{ label: '收藏人数', name: 'mFavorNum', index: 'm_favor_num', width: 80 }, 			
			{ label: '摄入热量(卡路里)', name: 'mCalories', index: 'm_calories', width: 80 }			
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
        }
    });
    new AjaxUpload('#upload', {
        action: '../../sys/oss/upload/10',
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
            	if (vm.clickIndex!="") {
            		vm.tYytcMeal.stepList[vm.clickIndex].sImg=r.url;
            		vm.clickIndex="";
				}else{
					vm.tYytcMeal.mImg = r.url;
				}
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#uploadExcel', {
    	action: '../../tyytcmeal/inportExcel',
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
			mTitle:"",
			mType:"",
			mTimeType:""
		},
		clickIndex: "",
		showList: true,
		title: null,
		tYytcMeal: {
			mImg:"",
			ingredientList:[],
			stepList:[],
			mSource:"机关食堂",
			mType:""
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.clickIndex = "";
			vm.tYytcMeal = {
					mImg:"",
					mSource:"机关食堂",
					mType:"",
					ingredientList:[],
					stepList:[]
			};
		},
		addIngredient: function(){
			vm.tYytcMeal.ingredientList.push({iNameNum:""});
		},
		delIngredient: function(){
			if (vm.tYytcMeal.ingredientList.length>0) {
				vm.tYytcMeal.ingredientList.pop();
			}else{
				alert("已经没了");
			}
		},
		addStep: function(){
			vm.tYytcMeal.stepList.push({sContent:"",sImg:"",sSort:vm.tYytcMeal.stepList.length+1});
		},
		delStep: function(){
			if (vm.tYytcMeal.stepList.length>0) {
				vm.tYytcMeal.stepList.pop();
			}else{
				alert("已经没了");
			}
		},
		update: function (event) {
			var mId = getSelectedRow();
			if(mId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(mId);
		},
		saveOrUpdate: function (event) {
			var url = vm.tYytcMeal.mId == null ? "../../tyytcmeal/save" : "../../tyytcmeal/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tYytcMeal),
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
			var mIds = getSelectedRows();
			if(mIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../../tyytcmeal/delete",
				    data: JSON.stringify(mIds),
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
		getInfo: function(mId){
			$.get("../../tyytcmeal/info/"+mId, function(r){
                vm.tYytcMeal = r.tYytcMeal;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					"keyword":vm.q.mTitle,
					"type":vm.q.mType,
					"timeType":vm.q.mTimeType
					},
                page:page
            }).trigger("reloadGrid");
		},
		upload: function (event) {
			vm.clickIndex = $(event.target).attr("value");
			$('input[name="file"]').trigger("click");
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
			
			location.href = "../../tyytcmeal/exportExcel"+str;
		},
		recommend: function (event) {
			var mIds = getSelectedRows();
			if(mIds == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../../tyytcmeal/recommend",
			    data: JSON.stringify(mIds),
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
		}
	}
});
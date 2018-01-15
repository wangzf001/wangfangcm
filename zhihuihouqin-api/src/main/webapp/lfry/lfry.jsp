<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>来访信息登记</title>
	<link rel="stylesheet" href="./assets/css/weui.min.css">
	<link rel="stylesheet" href="./assets/css/jquery-weui.min.css">
	<link rel="stylesheet" href="./assets/css/css.css">
</head>
<body>
	<form id="fmInfo" class="sf-top">
		<div class="weui-cells__title">受访人员信息</div>
		<div class="weui-cells__title">受访人员信息</div>
		<div class="weui-cells weui-cells_form">
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="toName"  placeholder="请输入姓名"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">办公电话</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="toPhone" placeholder="请输入办公电话"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">受访人房间号</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="number" name="toRoomNO" placeholder="请输入受访人房间号"/>
		        </div>
		    </div>
		</div>
		<div class="weui-cells__title">来访人员信息</div>
		<div class="weui-cells weui-cells_form">
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="fromName" placeholder="请输入姓名"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="fromIDCard" placeholder="请输入身份证号"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">电话(手机)</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="fromPhone" placeholder="请输入电话"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">单位</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="fromCompany" placeholder="请输入单位名称"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">来访时间</label></div>
		        <div class="weui-cell__bd">
		            <input id="dateCome" class="weui-input" type="text" name="fromVistTime" placeholder="请输入来访时间"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">随行人数</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="number" name="fromPeopleTotal" placeholder="请输入随行人数"/>
		        </div>
		    </div>
		</div>
		<div class="weui-cells__title">随行人员信息</div>
		<div class="weui-cells weui-cells_form">
		    <div id="fllowers">
		    	<div class="fllower-item">
			    	<div class="weui-cell">
				        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
				        <div class="weui-cell__bd">
				            <input class="weui-input" type="text" name="fromFlName"  placeholder="请输入随行人员姓名"/>
				        </div>
				    </div>
				    <div class="weui-cell">
				        <div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>
				        <div class="weui-cell__bd">
				            <input class="weui-input" type="text" name="fromtoFlIDCard" placeholder="请输入随行人员身份证号"/>
				        </div>
				    </div>
			    </div>
		    </div>
		    <div class="weui-cell">
				<a id="btnAddFllowInfo" href="javascript:;" class="weui-btn weui-btn_default">随行人员增减</a>
		    </div>
		</div>
		<div class="weui-cells__title">短信通知</div>
		<div class="weui-cells weui-cells_form">
		  	<div class="weui-cell weui-cell_switch">
		    	<div class="weui-cell__bd">是否发送短信通知</div>
		    	<div class="weui-cell__ft">
		      		<input class="weui-switch" type="checkbox">
		    	</div>
		  	</div>
		</div>
		<div id="erroWrap" style="display:none!important;">
			<div id="z_erro" class="z-erro"></div>
		</div>
		<div style="height:15px; background-color:#EFEFF4;"></div>
	</form>
	<div class="sf-bottom">
		<a id="btnSubmit" href="javascript:;" class="weui-btn weui-btn_info">提交</a>
	</div>
	<script src="./assets/js/jquery.min.js"></script>
	<script src="./assets/js/jquery-weui.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
	<script>
		$(function(){
			page.init();
		});

		var page = {
			init:function(){
				this.formInit();
			},
			formInit:function(){
				//控件初始化
				$("#dateCome").datetimePicker();
				

				var $fmInfo = $("#fmInfo");
				//验证初始化
				$fmInfo.validate({
					errorLabelContainer:'#z_erro',
				    rules:{
				      	toName:{
				        	required:true
				      	},
				      	toPhone:{
				        	required:true
				      	},
				      	toRoomNO:{
				        	required:true
				      	},
					    fromName:{
				        	required:true
					    },
					    fromIDCard:{
				        	required:true
					    },
					    fromPhone:{
				        	required:true
					    },
					    fromCompany:{
				        	required:true
					    },
					    fromVistTime:{
				        	required:true,
				        	date:true
					    },
					    fromPeopleTotal:{
				        	required:true,
				        	number:true
					    }
				    },
					messages:{
					    toName:{
					        required:'请输入受访者姓名'
					    },
					    toPhone:{
					        required:'请输入受访者办公室电话'
					    },
					    toRoomNO:{
					        required:'请输入受访者房间号'
					    },
					    fromName:{
					        required:'请输入来访者姓名'
					    },
					    fromIDCard:{
					        required:'请输入来访者身份证号'
					    },
					    fromPhone:{
					        required:'请输入来访者电话'
					    },
					    fromCompany:{
					        required:'请输入来访者单位名称'
					    },
					    fromVistTime:{
					        required:'请输入来访时间',
					        date:'来访时间格式错误'
					    },
					    fromPeopleTotal:{
					        required:'请输入随行人数',
					        number:'随行人数为数字格式'
					    }
				    }
				});

				//追加随行人员
				$('#btnAddFllowInfo').click(function(){
					$.actions({
					  	actions:[{
					    	text:"增加随行人员",
					    	className:'color-primary',
					    	onClick:function() {
					      		var html = '<div class="fllower-item">'
								    	+'<div class="weui-cell">'
									        +'<div class="weui-cell__hd"><label class="weui-label">姓名</label></div>'
									        +'<div class="weui-cell__bd">'
									            +'<input class="weui-input" type="text" name="fromFlName"  placeholder="请输入随行人员姓名"/>'
									        +'</div>'
									    +'</div>'
									    +'<div class="weui-cell">'
									        +'<div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>'
									        +'<div class="weui-cell__bd">'
									            +'<input class="weui-input" type="text" name="fromtoFlIDCard" placeholder="请输入随行人员身份证号"/>'
									        +'</div>'
									    +'</div>'
								    +'</div>';
								$('#fllowers').append(html);
					    	}
					  	},{
					    	text:"删减随行人员",
					    	className:'color-primary',
					    	onClick: function() {
					      		$('#fllowers .fllower-item:last-child').remove();
					    	}
					  	}],
					  	onClose:function(){
					  		//alert('d');
					  	}
					});
				});

				//提交表单
				$('#btnSubmit').click(function(){
					if ($fmInfo.valid()){

					}else{
						var erroHtml = $('#erroWrap').html();
						$.alert(erroHtml);
					}
				});
			}
		};
	</script>
</body>
</html>
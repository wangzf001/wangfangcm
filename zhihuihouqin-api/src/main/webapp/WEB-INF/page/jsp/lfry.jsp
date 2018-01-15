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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-weui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
</head>
<body>
	<form id="fmInfo" class="sf-top">
		<input type="hidden" name="operatetype" value="${visitUser.operatetype}">
		<input type="hidden" name="uid" value="${visitUser.uid}">
		<input type="hidden" name="stamp" value="${visitUser.stamp}">
		<input type="hidden" name="id" value="${visitUser.id}">
		<c:if test="${visitUser.operatetype == 1}">
			<div class="weui-cells__title">审核状态</div>
			<div class="weui-cells weui-cells_form">
			    <div class="weui-cell">
			        <div class="weui-cell__hd"><label class="weui-label">被访人审核状态</label></div>
			        <div class="weui-cell__bd">
			            <input class="weui-input" readonly="true" type="text"  value="<c:choose><c:when test="${visitUser.visitchecked == 0 }">待审核</c:when><c:when test="${visitUser.visitchecked == 1 }">通过</c:when><c:when test="${visitUser.visitchecked == 2 }">不通过    ,原因： ${visitUser.visitreason }</c:when></c:choose>" placeholder="被访人审核状态"/>
			        </div>
			    </div>
			    <div class="weui-cell">
			        <div class="weui-cell__hd"><label class="weui-label">管理员审核状态</label></div>
			        <div class="weui-cell__bd">
			        	<input class="weui-input" readonly="true" type="text"  value="<c:choose><c:when test="${visitUser.checked == 0 }">待审核</c:when><c:when test="${visitUser.checked == 1 }">通过</c:when><c:when test="${visitUser.checked == 2 }">不通过  ,原因： ${visitUser.adminreason } </c:when></c:choose>" placeholder="管理员审核状态"/>
			        </div>
			    </div>
			</div>
		
		</c:if>
		
		<div class="weui-cells__title">受访人员信息</div>
		<div class="weui-cells weui-cells_form">
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" readonly="true" type="text" name="realname" value="${visitUser.realname }"  placeholder="请输入姓名"/>
		        </div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">楼号</label></div>
				<div class="weui-cell__bd">
					<input class="weui-input" readonly="true" type="number" name="realname" value="${visitUser.buildnum }"/>
					<select id="buildnum" class="weui_select" name="buildnum">
						<c:forEach var="build" items="${visitUser.buildingEntityList}">
							<c:choose>
								<c:when test="${build.id == visitUser.buildnum}">
									<option value="${build.id}" selected="selected">${build.buildName}</option>
								</c:when>
								<c:otherwise>
									<option value="${build.id}">${build.buildName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>

		</div>
		<div class="weui-cells__title">来访人员信息</div>
		<div class="weui-cells weui-cells_form">
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="vname" value="${visitUser.vname}" placeholder="请输入姓名"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="vidnum" value="${visitUser.vidnum }" placeholder="请输入身份证号"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">电话(手机)</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="vmobile" value="${visitUser.vmobile }" placeholder="请输入电话"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">单位</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" type="text" name="unit" value="${visitUser.unit }" placeholder="请输入单位名称"/>
		        </div>
		    </div>
		    <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">来访时间</label></div>
		        <div class="weui-cell__bd">
		            <input id="dateCome" class="weui-input" type="text" name="visittimeStr" value="${visitUser.visittimeStr }" placeholder="请输入来访时间"/>
		        </div>
		    </div>
		   <div class="weui-cell">
		        <div class="weui-cell__hd"><label class="weui-label">随行人数</label></div>
		        <div class="weui-cell__bd">
		            <input class="weui-input" readonly="true" type="number" name="vpnum" value="<c:choose><c:when test="${visitUser.vpnum == null || visitUser.vpnum == '' }">0</c:when><c:otherwise >${visitUser.vpnum}</c:otherwise></c:choose>" placeholder="请输入随行人数"/>
		        </div>
		    </div>
		</div>
		<div class="weui-cells__title">随行人员信息</div>
		<div class="weui-cells weui-cells_form">
		    <div id="fllowers">
		   		   <c:forEach items="${visitUser.byuserlist }" var="item">
				   		   <div class="fllower-item">
					    	<div class="weui-cell">
						        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
						        <div class="weui-cell__bd">
						            <input class="weui-input" type="text" name="name" value="${item.name }"  placeholder="请输入随行人员姓名"/>
						        </div>
						    </div>
						    <div class="weui-cell">
						        <div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>
						        <div class="weui-cell__bd">
						            <input class="weui-input" type="text" name="idcard" value="${item.idcard }" placeholder="请输入随行人员身份证号"/>
						        </div>
						    </div>
					    </div>
		            </c:forEach>
		    	
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
		      		<input class="weui-switch" type="checkbox" id="notify" name="notify" <c:if test="${visitUser.notify == 1}">checked</c:if>>
		    	</div>
		  	</div>
		</div>
		<div id="erroWrap" style="display:none!important;">
			<div id="z_erro" class="z-erro"></div>
		</div>
		<div style="height:15px; background-color:#EFEFF4;"></div>
	</form>
	<div class="sf-bottom">
		<a id="btnSubmit" href="javascript:;" class="weui-btn weui-btn_info"><c:if test="${visitUser.operatetype == 1}">修改</c:if><c:if test="${visitUser.operatetype == 0}">提交</c:if></a>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-weui.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
	<script>
		$(function(){
			page.init();
			
			$('input.weui-switch[type="checkbox"]').click(function(){
				var $this = $(this);
				var ischecked = $this.attr('checked')===undefined?false:true;
				if(!ischecked){
					$this.attr('checked',true);
				}else{
					$this.removeAttr('checked');
				}
			});

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
				      	realname:{
				        	required:true
				      	},
				      
					    vname:{
				        	required:true
					    },
					    vidnum:{
				        	required:true
					    },
					    vmobile:{
				        	required:true
					    },
					    unit:{
				        	required:true
					    },
					    visittime:{
				        	required:true,
				        	date:true
					    }/* ,
					    vpnum:{
				        	required:true,
				        	number:true
					    } */
				    },
					messages:{
					    realname:{
					        required:'请输入受访者姓名'
					    },
					   
					    vname:{
					        required:'请输入来访者姓名'
					    },
					    vidnum:{
					        required:'请输入来访者身份证号'
					    },
					    vmobile:{
					        required:'请输入来访者电话'
					    },
					    unit:{
					        required:'请输入来访者单位名称'
					    },
					    visittime:{
					        required:'请输入来访时间',
					        date:'来访时间格式错误'
					    }/* ,
					    vpnum:{
					        required:'请输入随行人数',
					        number:'随行人数为数字格式'
					    } */
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
									            +'<input class="weui-input" type="text" name="name"  placeholder="请输入随行人员姓名"/>'
									        +'</div>'
									    +'</div>'
									    +'<div class="weui-cell">'
									        +'<div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>'
									        +'<div class="weui-cell__bd">'
									            +'<input class="weui-input" type="text" name="idcard" placeholder="请输入随行人员身份证号"/>'
									        +'</div>'
									    +'</div>'
								    +'</div>';
								$('#fllowers').append(html);
								updatecount(1);
								
					    	}
					  	},{
					    	text:"删减随行人员",
					    	className:'color-primary',
					    	onClick: function() {
					      		$('#fllowers .fllower-item:last-child').remove();
					      		updatecount(-1);
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
						var visitUser = createvisitUser();
						
						var bylist = new Array();
						$("input[name='name']").each(function(){
							var by = new Object();
							by.name = $(this).val();
							var inp = $(this).parent().parent().next().find("input[name='idcard']");
							by.idcard = inp.val();
							bylist.push(by);
						})
						visitUser.byuserlist = bylist;
						var biz =new Object();
						biz.visitUser = visitUser;
						var url = "${pageContext.request.contextPath}"+"/appuser/user/addforeignuser";
						/* $.post(url,{"biz":biz},function(r){
							if(r.errCode === 0){
								alert("提交成功");
							}else{
								alert(r.msg);
							}
						}) */
						
						$.ajax({
							type: "POST",
							contentType : 'application/json',
						    url: url,
						    data: JSON.stringify(visitUser),
						    success: function(r){
						    	if(r.errCode === 0){
									alert("操作成功") ;
									location.reload();
								}else{
									alert(r.msg);
								}
							}
						});
					}else{
						var erroHtml = $('#erroWrap').html();
						$.alert(erroHtml);
					}
				});
			}
		};
		
		function createvisitUser(){
			var visitUser =new Object();
			visitUser.realname = $("input[name='realname']").val();
			visitUser.vname = $("input[name='vname']").val();
			visitUser.vmobile = $("input[name='vmobile']").val();
			visitUser.vidnum = $("input[name='vidnum']").val();
			visitUser.unit = $("input[name='unit']").val();
			visitUser.stamp = $("input[name='stamp']").val();
			visitUser.uid = $("input[name='uid']").val();
			visitUser.vpnum = $("input[name='vpnum']").val()==''?0:$("input[name='vpnum']").val();
			visitUser.notify = $("input[name='notify']").val() == 'on'?1:0;
			visitUser.operatetype = $("input[name='operatetype']").val();
			visitUser.visittimeStr =$("input[name='visittimeStr']").val();
			visitUser.id =$("input[name='id']").val();
            visitUser.buildnum =$("#buildnum option:selected").val();
			var i = $("input[name='notify']").attr('checked')==undefined?0:1;
			visitUser.notify = i;
			return visitUser;
		}
		
		function updatecount(count){
			debugger
			var co = parseInt(count);
			var rc = $("input[name='vpnum']").val();
			if(rc == null || rc == undefined || rc == ''){
				rc = 0 ;
			}
			var irc = parseInt(rc);
			if(co + irc < 0 ) {
				return ;
			}
			var c = irc+co;
			$("input[name='vpnum']").attr("value",c);
		}
	</script>
</body>
</html>
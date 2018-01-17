<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: red;" >测试页面</h1>
	<h4 style="color: orange">——————————————获得验证码测试——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/getCaptcha">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"mobile":"13263280712"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————注册测试——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/registerAndLogin">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"mobile":"13263280712","captcha":""}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————添加宝宝信息测试——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/addBabbyInfo">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"uid":"2","sex":"1","age":12,"birthday":"2016-08-12","nickname":"尼克"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————查询用户宝宝——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/selectBabbyList">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取用户信息——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/getUserInfo">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————点击更多分类——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/cateloglevel/more">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————修改用户的分类——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/cateloglevel/updateUserCatelog">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2","selectedCList":[1,4,5,6,7]}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取用户的消息——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/getUserMsg">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————点击分类消息——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/getUserMsgByType">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2","type":"1"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取消息详情——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/getMsgInfo">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"id":"2"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————删除消息——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/user/deleteMsg">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"id":"2"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————首页信息——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/index">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2","lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————首页点击任意分类——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/clickCatelog">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":"2","lon":116.422464,"lat":40.011988,"regionId":1,"catelogId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取指定类型商品的数据——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/index/queryProductByCatelog">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"lon":116.422464,"lat":40.011988,"regionId":1,"catelogId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取更多推荐数据——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/queryMoreRecommend">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取更多新到数据——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/queryMoreNew">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取商品详细数据——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/product/queryProductInfo">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"id":1,"userId":1,"lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————获取商品的评价——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/product/queryProductCommon">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"id":1,"page":1,"limit":10}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————给用户的商品评价点赞——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/product/praiseProductCommon">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"pcid":1,"isPraise":1,"userId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————点击评论提交——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/product/commentUserCommon">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"pcid":1,"userId":1,"content":"世界那么大你想去看看"}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————添加收藏——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/product/addCollect">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1,"id":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————取消收藏——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/product/deleteCollect">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1,"id":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————查询购物车——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/usercart/queryUserCart">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1,"id":1,"lon":116.422464,"lat":40.011988}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<!-- 新 -->

	<h4 style="color: orange">——————————————点击附近的售货机——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/queryVendorNearby">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————点击柜子的可租按钮——————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/clickVendorNormal">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"lon":116.422464,"lat":40.011988,"vendorId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: red">——————————————点击领取代金券(免租时长和代金券)——————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/addCouponToUser">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1,"type":0}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: red">——————————————活动展示——————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/index/activityShow">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz"></textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: red">——————————————折扣商品展示——————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/index/queryDiscountProduct">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————加入购物车——————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/product/addMyCart">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"id":1,"vendorId":1,"rentDays":10,"userId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————查询商品所在的柜子列表——————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/product/queryProductVendorList">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"id":1,"lon":116.422464,"lat":40.011988,"regionId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————增减购物项的租用天数——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/usercart/addRentDay">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"cartId":1,"num":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————删除购物车项——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/usercart/deleteCartItems">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"cartIdArr":[3,4]}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————点击确认按钮生成订单预览(抢先预定)——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/usercart/generateOrderView">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1,"lon":116.422464,"lat":40.011988,"productTypeId":1,"vendorId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————预定生成订单——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/order/generateOrder">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"itemArr":[{"productTypeId":1,"ucid":2,"rentDays":20,"vendorId":1},{"productTypeId":2,"ucid":1,"rentDays":20,"vendorId":2}],"userId":1,"cartIdArr":[1,2]}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————查询用户待取货订单——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/order/queryUserUntakeOrders">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————查询用户已取货订单——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/order/queryUserTokenOrders">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————查询已完成订单——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post"
		action="/zhihuihouqin-api/order/queryUserFinishedOrders">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————扫码取货——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/zhihuihouqin-api/order/scanQRCodeTake">
		<input type="hidden" name="auth"
			value='{"app_key":"123456","imei":"444012","os":"Iphone os","os_version":"5.0","app_version":"1.0.0","source_id":"Yek_test","ver":"0.9","uid":"-1","crc":"3e64055bf4056d1dc68b85dd4365d649","time_stamp":"20090310113016"}'>
		<textarea title="info" rows="4" cols="150" name="biz">{"userId":1,"vendorNo":"1234567abcdefg","gridNo":1}</textarea>
		<br> <input type="submit" value="测试">
	</form>

</body>
</html>
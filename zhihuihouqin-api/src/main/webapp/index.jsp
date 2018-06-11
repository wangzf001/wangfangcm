<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本地测试</title>
</head>
<body>
<h1 style="color: red;" >测试页面</h1>
	<h4 style="color: orange">——————————————Multipart/form-data首页广告测试——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/wangfangcm-api/ad/findAdInfo"  enctype="Multipart/form-data">
		<input type="hidden" name="auth"
			value=''>
		<textarea title="info" rows="4" cols="150" name="biz">{}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	<h4 style="color: orange">——————————————Multipart/form-data首页广告测试——————————————————————</h4>
	<h4>测试：</h4>
	<form method="post" action="/wangfangcm-api/ad/findAdInfo"  enctype="application/x-www-form-urlencoded">
		<input type="hidden" name="auth"
			value=''>
		<textarea title="info" rows="4" cols="150" name="biz">{}</textarea>
		<br> <input type="submit" value="测试">
	</form>
	
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
	

</body>
</html>
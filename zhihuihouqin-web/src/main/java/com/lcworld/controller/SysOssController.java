package com.lcworld.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.entity.SysOssEntity;
import com.lcworld.oss.CloudStorageConfig;
import com.lcworld.service.SysConfigService;
import com.lcworld.service.SysOssService;
import com.lcworld.utils.ConfigConstant;
import com.lcworld.utils.Constant;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.RRException;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.validator.ValidatorUtils;
import com.lcworld.validator.group.AliyunGroup;
import com.lcworld.validator.group.QcloudGroup;
import com.lcworld.validator.group.QiniuGroup;


/**
 * 文件上传
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017年4月19日 上午10:42:40
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysOssEntity> sysOssList = sysOssService.queryList(query);
		int total = sysOssService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@RequestMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}
		

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@RequestMapping("/upload/{type}")
	public R upload(@RequestParam("file") MultipartFile file,@PathVariable("type") String type) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}
		String pre = "";
		if (ValidateUtil.isValid(type)) {
			try {
				int typeInt = Integer.parseInt(type);
				pre = getPreByType(typeInt);
			} catch (Exception e) {
				return R.error(2,"不是数字");
			}
		}else{
			pre = getPreByType(0);
		}
		//上传文件
		JSONObject obj = WebUtils.uploadFile(file, null,pre, RedisConst.UPLOAD_IMG_FILTER);
		String imgUrl = obj.getString("data");
		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(imgUrl);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);
		return R.ok().put("url", imgUrl);
	}
	@RequestMapping("/upload")
	public R upload(String dir,@RequestParam("imgFile") MultipartFile[] imgFile) throws Exception {
		//上传文件
		JSONObject obj = WebUtils.uploadFiles(imgFile, null,dir, RedisConst.UPLOAD_IMG_FILTER);
		String imgUrl = obj.getString("data");
		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(imgUrl);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);
		HashMap<String,Object> map = new HashMap<>();
		map.put("state", "SUCCESS");
		map.put("url", imgUrl);
		return R.ok(map);
	}
	@RequestMapping(value = "ueditor", method = RequestMethod.GET)
    public @ResponseBody String ueUpload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //这里就是把controller.jsp代码copy下来
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
//        String roolPath = request.getServletContext().getContextPath();
        String roolPath = request.getSession().getServletContext().getRealPath("/");
        String configStr = new ActionEnter(request, roolPath).exec();
        return  configStr;
//		String contextPath = request.getServletContext().getContextPath();
//		String url = contextPath + "/statics/ueditor/jsp/controller.jsp";
//      return  "redirect:"+url;
    }
	private String getPreByType(int typeInt) {
	    return RedisConst.getTypeimgmaps().get(typeInt) == null?RedisConst.getTypeimgmaps().get(RedisConst.TYPE_OTHERS):
	        RedisConst.getTypeimgmaps().get(typeInt);
	}


	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		sysOssService.deleteBatch(ids);
		
		return R.ok();
	}

}

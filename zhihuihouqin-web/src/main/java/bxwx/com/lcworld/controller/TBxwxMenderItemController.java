package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.dto.UserFrontRolesDTO;
import com.lcworld.entity.BaseUserRoleEntity;
import com.lcworld.entity.TBxwxMenderItemEntity;
import com.lcworld.service.TBxwxItemService;
import com.lcworld.service.TBxwxMenderItemService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 维修人维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
@RestController
@RequestMapping("tbxwxmenderitem")
public class TBxwxMenderItemController {
	@Autowired
	private TBxwxMenderItemService tBxwxMenderItemService;
	@Autowired
	private TBxwxItemService tBxwxItemService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tbxwxmenderitem:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxMenderItemEntity> tBxwxMenderItemList = tBxwxMenderItemService.queryList(query);
		int total = tBxwxMenderItemService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxMenderItemList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbxwxmenderitem:info")
	public R info(@PathVariable("id") Integer id){
		TBxwxMenderItemEntity tBxwxMenderItem = tBxwxMenderItemService.queryObject(id);
		
		return R.ok().put("tBxwxMenderItem", tBxwxMenderItem);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tbxwxmenderitem:save")
	public R save(@RequestBody TBxwxMenderItemEntity tBxwxMenderItem){
		tBxwxMenderItemService.save(tBxwxMenderItem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tbxwxmenderitem:update")
	public R update(@RequestBody TBxwxMenderItemEntity tBxwxMenderItem){
		tBxwxMenderItemService.update(tBxwxMenderItem);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tbxwxmenderitem:delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxMenderItemService.deleteBatch(ids);
		
		return R.ok();
	}
	
	
	   /**
     * 更新权限
     */
    @RequestMapping("/updateFavor")
    public R updateFavor(Integer menderid,String ids,String selectids){
        JSONObject params = new JSONObject();
        params.put("itemids", Arrays.asList(ids.split(",")));
        params.put("menderid", menderid);
        
        if(ValidateUtil.isValid(menderid) && ValidateUtil.isValid(ids) ){
            tBxwxMenderItemService.deleteByParams(params);
            if(ValidateUtil.isValid(selectids)){
                List<TBxwxMenderItemEntity> rlist = new ArrayList<TBxwxMenderItemEntity>();
                for(String itemid : selectids.split(",")){
                    TBxwxMenderItemEntity ur = new TBxwxMenderItemEntity();
                    ur.setItemid(Integer.parseInt(itemid));
                    ur.setMenderid(menderid);
                    rlist.add(ur);
                }
                tBxwxMenderItemService.savebench(rlist);
            }else{
                return R.error(400, "没有可选的维修项");
            }
        }else{
            return R.error(400, "没有可选的维修项");
        }
        return R.ok();
        
    }
    
    /**
     * list
     */
    @RequestMapping("/menderitemlist")
    public R menderitemlist(Integer menderid){
        if(menderid == null){
            return R.ok();
        }else{
            JSONObject params = new JSONObject();
            params.put("menderid", menderid);
            
            Query q = new Query(params);
            List<BxwxMenderItemDTO> itemlist = tBxwxItemService.queryItemList(q);
            int total = tBxwxItemService.queryTotal(q);
            List<BxwxMenderItemDTO> mitemlist = tBxwxMenderItemService.queryUserItemList(q);
            
            PageUtils pageUtil = new PageUtils(packageRoleList(itemlist,mitemlist), total, q.getLimit(), q.getPage());
            
            return R.ok().put("page", pageUtil).put("menderid", menderid);
        }
    }


    

    private List<BxwxMenderItemDTO> packageRoleList(List<BxwxMenderItemDTO> froses, List<BxwxMenderItemDTO> baseUserRoleList) {
        if(ValidateUtil.isValid(froses) && ValidateUtil.isValid(baseUserRoleList)){
                for( BxwxMenderItemDTO role : froses){
                    role.setStatus(0);
                    for(BxwxMenderItemDTO ur :baseUserRoleList){
                        if(ur.getId() == role.getId()){
                            role.setStatus(1);
                            break;
                        }
                    }
                }
        }
        return froses;
    }

	
}

package com.lcworld.controller;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.dto.BgypProductDTO;
import com.lcworld.entity.*;
import com.lcworld.service.*;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.R;
import com.lcworld.validator.Assert;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api")
public class BgypfwProductAPI {
    private Logger log = LoggerFactory.getLogger(BgypfwProductAPI.class);
    @Autowired
    private BgypfwProductService producinfoService;

    @Autowired
    private BgypfwSkuidService skuidService;

    @Autowired
    private BgypfwCategoryService categoryService;

    @Autowired
    private BgypfwProductimgService productimgService;

    @Autowired
    private  BgypfwSkufirstcataService skufirstcataService;

    @IgnoreToken
    @IgnoreSign
    @PostMapping("/products")
    public R products(String biz) {
        log.info(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)+"调用导入数据接口");
        JSONObject jsonObject = JSONObject.parseObject(biz);
        String access_token = jsonObject.getString("access_token");
        Assert.isBlank(access_token, "access_token : parameters can not be empty");
        if (!APPConstant.APP_ACCESS_TOKEN.equals(access_token)) {
            return R.error(1500, "Access_token mismatch");
        }
        String operation = jsonObject.getString("operation");
        Assert.isBlank(operation, "operation : parameters can not be empty");
        if (!"POST".equals(operation)) {
            return R.error(1501, "Operation error");
        }
        String data = jsonObject.getString("data");
        Assert.isBlank(data, "Data is empty");
        List list = JSONObject.parseObject(data, List.class);
        if (list.size()<1) {
            return R.error(1502, "Data is empty");
        }
        log.info(list.toString());
        List<BgypProductDTO> unImport = new ArrayList<>();
        for (Object obj : list) {
            BgypProductDTO dto = JSONObject.parseObject(obj.toString(), BgypProductDTO.class);
            if(StringUtils.isBlank(dto.getPackSpec()) || StringUtils.isBlank(dto.getCommodityName()) || StringUtils.isBlank(dto.getCommodityNum())
                    || StringUtils.isBlank(dto.getBrandName())|| StringUtils.isBlank(dto.getPriceInterval())|| StringUtils.isBlank(dto.getClassifyName())
                    || StringUtils.isBlank(dto.getSmallClassifyName())|| StringUtils.isBlank(dto.getPrice().toString())|| StringUtils.isBlank(dto.getMainPic())
                    || StringUtils.isBlank(dto.getSkuid())|| StringUtils.isBlank(dto.getRollingPic().toString())|| StringUtils.isBlank(dto.getSupplierName())
                    || StringUtils.isBlank(dto.getSupplierAddress())|| StringUtils.isBlank(dto.getSupplierTel())|| StringUtils.isBlank(dto.getStore().toString())){
                unImport.add(dto);
                continue;
            }
            String commodityName = dto.getCommodityName();
            BgypfwProductEntity producinfoEntity = producinfoService.queryProductByName(commodityName);
            BgypfwSkuidEntity skuidEntity = skuidService.querySkuidByName(dto.getSkuid());
            if (producinfoEntity == null) {
                producinfoEntity = new BgypfwProductEntity();
                String oneName = dto.getClassifyName();
                Map<String,Object> params = new HashMap<>();
                params.put("name",oneName);
                params.put("grade",1);
                BgypfwCategoryEntity categoryOne = categoryService.queryCategoryByName(params);
                if(categoryOne == null){
                    categoryOne = new BgypfwCategoryEntity();
                    categoryOne.setCname(oneName);
                    categoryOne.setCgrade(1);
                    categoryService.save(categoryOne);
                }
                String twoName = dto.getSmallClassifyName();
                params.put("name",twoName);
                params.put("grade",2);
                BgypfwCategoryEntity categoryTwo = categoryService.queryCategoryByName(params);
                if(categoryTwo == null){
                    categoryTwo = new BgypfwCategoryEntity();
                    categoryTwo.setCname(twoName);
                    categoryTwo.setCgrade(2);
                    categoryTwo.setPid(categoryOne.getId());
                    categoryService.save(categoryTwo);
                }
                producinfoEntity.setCategoryTwoId(categoryTwo.getId());
                producinfoEntity.setCategoryOneId(categoryOne.getId());
                producinfoEntity.setProductname(dto.getCommodityName());
                producinfoEntity.setProductCode(dto.getCommodityNum());
                producinfoEntity.setStatus(1);
                producinfoEntity.setCreatetime(new Date());
                producinfoEntity.setOnsaleTime(new Date());
                producinfoService.save(producinfoEntity);
            }
            List<String> rollingPic = dto.getRollingPic();
            if(rollingPic != null && rollingPic.size() > 0){
                productimgService.deleteByPids(new Integer[]{producinfoEntity.getId()});
                for (String url: rollingPic) {
                    BgypfwProductimgEntity productimgEntity = new BgypfwProductimgEntity();
                    productimgEntity.setImg(url);
                    productimgEntity.setProductId(producinfoEntity.getId());
                    productimgEntity.setSort(1);
                    productimgService.save(productimgEntity);
                }
            }
            if(skuidEntity == null){
                skuidEntity = new BgypfwSkuidEntity();
                skuidEntity.setSkuname(dto.getBrandName()+"-"+dto.getSkuid()+"-"+dto.getColor()+"-"+dto.getSize()+"-"+dto.getNumber());
                skuidEntity.setStore(dto.getStore());
                skuidEntity.setStatus(1);
                skuidEntity.setCreatetime(new Date());
                skuidEntity.setMainimg(dto.getMainPic());
                skuidEntity.setPrice(dto.getPrice());
                skuidEntity.setProductid(producinfoEntity.getId());
                skuidEntity.setFirstcataid(2);
                skuidService.save(skuidEntity);
            }else{
                skuidEntity.setPrice(dto.getPrice());
                skuidEntity.setStore(dto.getStore());
                skuidService.update(skuidEntity);
            }
        }
        if(unImport.size()>0){
            return R.error(1503,"The data is not fully imported, the following is the data that can not be imported, and the data format may not meet the requirements.").put("data",unImport);
        }else{
            return R.ok();
        }
    }

    /**
     * 规格类别
     * @param biz
     * @return
     */
    @IgnoreToken
    @IgnoreSign
    @PostMapping("/skuCatas")
    public R skuCatas(String biz) {
        log.info(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)+"调用规格类别接口");
        JSONObject jsonObject = JSONObject.parseObject(biz);
        String access_token = jsonObject.getString("access_token");
        Assert.isBlank(access_token, "access_token : parameters can not be empty");
        if (!APPConstant.APP_ACCESS_TOKEN.equals(access_token)) {
            return R.error(1500, "Access_token mismatch");
        }
        String operation = jsonObject.getString("operation");
        Assert.isBlank(operation, "operation : parameters can not be empty");
        if (!"GET".equals(operation)) {
            return R.error(1501, "Operation error");
        }
        List<BgypfwSkufirstcataEntity> skufirstcataEntities = skufirstcataService.queryList(new JSONObject());
        return R.ok().put("data",skufirstcataEntities);
    }
}

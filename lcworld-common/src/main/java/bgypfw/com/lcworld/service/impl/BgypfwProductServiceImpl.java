package com.lcworld.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.BgypfwProductDao;
import com.lcworld.dao.BgypfwSkuidDao;
import com.lcworld.entity.BgypfwProductEntity;
import com.lcworld.entity.BgypfwProductimgEntity;
import com.lcworld.entity.TDcfwFoodEntity;
import com.lcworld.entity.TNoticeEntity;
import com.lcworld.service.BgypfwProductService;
import com.lcworld.utils.POIUtil;
import com.lcworld.utils.Query;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.dao.BgypfwProductimgDao;


@Service("bgypfwProductService")
public class BgypfwProductServiceImpl implements BgypfwProductService {
    @Autowired
    private BgypfwProductDao bgypfwProductDao;
    @Autowired
    private BgypfwSkuidDao bgypfwSkuidDao;
    @Autowired
    private BgypfwProductimgDao bgypfwProductimgDao;

    @Override
    public BgypfwProductEntity queryObject(Integer id) {
        BgypfwProductEntity productEntity = bgypfwProductDao.queryObject(id);
//		String imgs = productEntity.getImgs();
//		if (ValidateUtil.isValid(imgs)) {
//			String[] imgArr = imgs.split(",");
//			productEntity.getImgList().addAll(Arrays.asList(imgArr));
//		}
        return productEntity;
    }

    @Override
    public BgypfwProductEntity queryProductByName(String productname) {
        return bgypfwProductDao.queryProductByName(productname);
    }

    @Override
    public List<BgypfwProductEntity> queryList(Map<String, Object> map) {
        return bgypfwProductDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return bgypfwProductDao.queryTotal(map);
    }

    @Override
    public void save(BgypfwProductEntity bgypfwProduct) {
        bgypfwProductDao.save(bgypfwProduct);
        List<BgypfwProductimgEntity> imgEntityList = bgypfwProduct.getImgEntityList();
        if (ValidateUtil.isValid(imgEntityList)) {
            for (int i = 0; i < imgEntityList.size(); i++) {
                imgEntityList.get(i).setProductId(bgypfwProduct.getId());
            }
            bgypfwProductimgDao.saveBatch(imgEntityList);
        }
    }

    @Override
    public void saveBatch(List<BgypfwProductEntity> list) {
        bgypfwProductDao.saveBatch(list);
    }

    @Override
    public void update(BgypfwProductEntity bgypfwProduct) {
        bgypfwProductDao.update(bgypfwProduct);
        //先删除所有图片
        Integer[] ids = {bgypfwProduct.getId()};
        bgypfwProductimgDao.deleteByPids(ids);
        //再添加
        List<BgypfwProductimgEntity> imgEntityList = bgypfwProduct.getImgEntityList();
        if (ValidateUtil.isValid(imgEntityList)) {
            for (int i = 0; i < imgEntityList.size(); i++) {
                imgEntityList.get(i).setProductId(bgypfwProduct.getId());
            }
            bgypfwProductimgDao.saveBatch(imgEntityList);
        }
    }

    @Override
    public void delete(Integer id) {
        bgypfwProductDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        bgypfwProductDao.deleteBatch(ids);
        bgypfwSkuidDao.deleteByPids(ids);
        bgypfwProductimgDao.deleteByPids(ids);
    }

    @Override
    public List<BgypfwProductEntity> queryFavorList(Query params) {
        return queryList(params);
    }

    @Override
    public void update1(BgypfwProductEntity product) {
        // TODO Auto-generated method stub
        bgypfwProductDao.update(product);
    }

    @Override
    public void exportExcel(DualHashBidiMap titleMapping,
                            List<BgypfwProductEntity> objectList, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            POIUtil.generateExcel(titleMapping, objectList, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<BgypfwProductEntity> importExcel(Class<BgypfwProductEntity> t,
                                                 DualHashBidiMap titleMapping, MultipartFile file) {
        // TODO Auto-generated method stub
        List<BgypfwProductEntity> list = null;
        try {
            list = POIUtil.readExcel(BgypfwProductEntity.class, titleMapping, file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

}

package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcworld.dao.HysfwConferenceRoomDao;
import com.lcworld.entity.BgypfwProductimgEntity;
import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.entity.HysfwConferenceRoomEntity;
import com.lcworld.entity.HysfwConferenceServiceEntity;
import com.lcworld.service.HysfwConferenceRoomService;
import com.lcworld.utils.ValidateUtil;



@Service("hysfwConferenceRoomService")
public class HysfwConferenceRoomServiceImpl implements HysfwConferenceRoomService {
	@Autowired
	private HysfwConferenceRoomDao hysfwConferenceRoomDao;
	
	@Override
	public HysfwConferenceRoomEntity queryObject(Integer id){
		HysfwConferenceRoomEntity obj = hysfwConferenceRoomDao.queryObject(id);
		String imgs = obj.getImgs();
		if (ValidateUtil.isValid(imgs)) {
			String[] split = imgs.split(",");
			for (int i = 0; i < split.length; i++) {
				BgypfwProductimgEntity imgEntity = new BgypfwProductimgEntity();
				imgEntity.setImg(split[i]);
				imgEntity.setSort(i+1);
				obj.getImgEntityList().add(imgEntity);
			}
		}
		//查询room下的所有服务和设备
		List<HysfwConferenceEquipmentEntity> elist = hysfwConferenceRoomDao.queryAvailableEquipmentList(id);
		List<HysfwConferenceServiceEntity> slist = hysfwConferenceRoomDao.queryAvailableServiceList(id);
		for (HysfwConferenceServiceEntity s : slist) {
			obj.getServiceList().add(s.getId());
		}
		for (HysfwConferenceEquipmentEntity e : elist) {
			obj.getEquipmentList().add(e.getId());
		}
		return obj;
	}
	
	@Override
	public List<HysfwConferenceRoomEntity> queryList(Map<String, Object> map){
		List<HysfwConferenceRoomEntity> list = hysfwConferenceRoomDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			String imgs = list.get(i).getImgs();
			if (ValidateUtil.isValid(imgs)) {
				String[] split = imgs.split(",");
				for (String img : split) {
					list.get(i).getImgList().add(img);
				}
			}
		}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwConferenceRoomDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwConferenceRoomEntity hysfwConferenceRoom){
		List<BgypfwProductimgEntity> imgEntityList = hysfwConferenceRoom.getImgEntityList();
		//保存房间的服务和设备信息
		saveSAndE(hysfwConferenceRoom);
		if (ValidateUtil.isValid(imgEntityList)) {
			String imgs = "";
			for (int i = 0; i < imgEntityList.size(); i++) {
				imgs += imgEntityList.get(i).getImg()+",";
			}
			hysfwConferenceRoom.setImgs(imgs);
		}
		hysfwConferenceRoomDao.save(hysfwConferenceRoom);
	}
	
	private void saveSAndE(HysfwConferenceRoomEntity hysfwConferenceRoom) {
		hysfwConferenceRoomDao.deleteByRoomid(hysfwConferenceRoom.getId());
		List<Map<String,Object>> list = new ArrayList<>();
		for (Integer eid : hysfwConferenceRoom.getEquipmentList()) {
			Map<String, Object> map = new HashMap<>();
			map.put("eid", eid);
			map.put("roomid", hysfwConferenceRoom.getId());
			map.put("count",1);
			map.put("type", 1);
			list.add(map);
		}
		for (Integer sid : hysfwConferenceRoom.getServiceList()) {
			Map<String, Object> map = new HashMap<>();
			map.put("eid", sid);
			map.put("roomid", hysfwConferenceRoom.getId());
			map.put("count",1);
			map.put("type", 2);
			list.add(map);
		}
		hysfwConferenceRoomDao.saveRoomSE(list);
	}

	@Override
	public void update(HysfwConferenceRoomEntity hysfwConferenceRoom){
		List<BgypfwProductimgEntity> imgEntityList = hysfwConferenceRoom.getImgEntityList();
		if (ValidateUtil.isValid(imgEntityList)) {
			String imgs = "";
			for (int i = 0; i < imgEntityList.size(); i++) {
				imgs += imgEntityList.get(i).getImg()+",";
			}
			hysfwConferenceRoom.setImgs(imgs);
		}
		hysfwConferenceRoomDao.update(hysfwConferenceRoom);
		saveSAndE(hysfwConferenceRoom);
	}
	
	@Override
	public void delete(Integer id){
		hysfwConferenceRoomDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwConferenceRoomDao.deleteBatch(ids);
	}

	@Override
	public List<HysfwConferenceEquipmentEntity> queryAvailableEquipmentList(Integer id) {
		return hysfwConferenceRoomDao.queryAvailableEquipmentList(id);
	}

	@Override
	public List<Map<Integer, String>> queryConferenceType() {
		return hysfwConferenceRoomDao.queryConferenceType();
	}

	@Override
	public List<HysfwConferenceServiceEntity> queryAvailableServiceList(Integer id) {
		return hysfwConferenceRoomDao.queryAvailableServiceList(id);
	}
	
}

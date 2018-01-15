package com.lcworld.dto;

import java.util.ArrayList;
import java.util.List;

import com.lcworld.entity.BgypfwSkuCataInfoEntity;

public class BgypSkuDto {
	//规格分类id
		private Integer cataid;
		private String cataname;
		private List<BgypfwSkuCataInfoEntity> infolist = new ArrayList<BgypfwSkuCataInfoEntity>();
		
		public String getCataname() {
			return cataname;
		}
		public void setCataname(String cataname) {
			this.cataname = cataname;
		}
		public Integer getCataid() {
			return cataid;
		}
		public void setCataid(Integer cataid) {
			this.cataid = cataid;
		}
		public List<BgypfwSkuCataInfoEntity> getInfolist() {
			return infolist;
		}
		public void setInfolist(List<BgypfwSkuCataInfoEntity> infolist) {
			this.infolist = infolist;
		}
		
		
}

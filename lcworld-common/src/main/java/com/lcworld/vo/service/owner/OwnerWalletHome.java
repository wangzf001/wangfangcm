package com.lcworld.vo.service.owner;

import java.util.List;

public class OwnerWalletHome {
	
	Double walletMoney;
	
	List<ServiceOwnerWalletLog> log;

	public Double getWalletMoney() {
		return walletMoney;
	}

	public void setWalletMoney(Double walletMoney) {
		this.walletMoney = walletMoney;
	}

	public List<ServiceOwnerWalletLog> getLog() {
		return log;
	}

	public void setLog(List<ServiceOwnerWalletLog> log) {
		this.log = log;
	}
	
	
	
}

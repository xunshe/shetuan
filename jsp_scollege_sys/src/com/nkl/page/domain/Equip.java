package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Equip extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8738777721805535695L;
	private int equip_id; // 
	private String equip_name; // 
	private String equip_note; // 

	private String ids;
	private String random;

	public void setEquip_id(int equip_id){
		this.equip_id=equip_id;
	}

	public int getEquip_id(){
		return equip_id;
	}

	public void setEquip_name(String equip_name){
		this.equip_name=equip_name;
	}

	public String getEquip_name(){
		return equip_name;
	}

	public void setEquip_note(String equip_note){
		this.equip_note=equip_note;
	}

	public String getEquip_note(){
		return equip_note;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

}

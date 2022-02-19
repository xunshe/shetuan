package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Equip;

public class EquipDao {

	public int addEquip(Equip equip, Connection conn){
		String sql = "INSERT INTO equip(equip_id,equip_name,equip_note) values(null,?,?)";
		Object[] params = new Object[] {
			equip.getEquip_name(),
			equip.getEquip_note()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delEquip(String equip_id, Connection conn){
		String sql = "DELETE FROM equip WHERE equip_id=?";

		Object[] params = new Object[] { new Integer(equip_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delEquips(String[] equip_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <equip_ids.length; i++) {
			sBuilder.append("?");
			if (i !=equip_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM equip WHERE equip_id IN(" +sBuilder.toString()+")";

		Object[] params = equip_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateEquip(Equip equip, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE equip SET equip_id = " + equip.getEquip_id() +" ");
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" , equip_name ='" + equip.getEquip_name() + "' ");
		}
		sBuilder.append(" , equip_note ='" + equip.getEquip_note() + "' ");
		sBuilder.append("where equip_id = " + equip.getEquip_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Equip getEquip(Equip equip, Connection conn){
		Equip _equip=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM equip WHERE 1=1");
		if (equip.getEquip_id()!=0) {
			sBuilder.append(" and equip_id = " + equip.getEquip_id() +" ");
		}
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" and equip_name = '" + equip.getEquip_name() + "' ");
		}

		List<Object> list = BaseDao.executeQuery(Equip.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _equip = (Equip)list.get(0);
		}
		return _equip;
	}

	public List<Equip>  listEquips(Equip equip, Connection conn){
		List<Equip> equips = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM equip WHERE 1=1");

		if (equip.getEquip_id()!=0) {
			sBuilder.append(" and equip_id = " + equip.getEquip_id() +" ");
		}
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" and equip_name like '%" + equip.getEquip_name() + "%' ");
		}
		sBuilder.append(" order by equip_id asc) t");

		if (equip.getStart() != -1) {
			sBuilder.append(" limit " + equip.getStart() + "," + equip.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Equip.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			equips = new ArrayList<Equip>();
			for (Object object : list) {
				equips.add((Equip)object);
			}
		}
		return equips;
	}

	public int  listEquipsCount(Equip equip, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM equip WHERE 1=1");

		if (equip.getEquip_id()!=0) {
			sBuilder.append(" and equip_id = " + equip.getEquip_id() +" ");
		}
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" and equip_name like '%" + equip.getEquip_name() + "%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}

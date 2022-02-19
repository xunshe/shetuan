package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Member;

public class MemberDao {

	public int addMember(Member member, Connection conn){
		String sql = "INSERT INTO member(member_id,college_id,user_id,reg_date,member_reason,member_hobby,member_flag) values(null,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			member.getCollege_id(),
			member.getUser_id(),
			member.getReg_date(),
			member.getMember_reason(),
			member.getMember_hobby(),
			member.getMember_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delMember(String member_id, Connection conn){
		String sql = "DELETE FROM member WHERE member_id=?";

		Object[] params = new Object[] { new Integer(member_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delMembers(String[] member_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <member_ids.length; i++) {
			sBuilder.append("?");
			if (i !=member_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM member WHERE member_id IN(" +sBuilder.toString()+")";

		Object[] params = member_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateMember(Member member, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE member SET member_id = " + member.getMember_id() +" ");
		if (member.getMember_flag()!=0) {
			sBuilder.append(" ,member_flag = " + member.getMember_flag() +" ");
		}
		sBuilder.append("where member_id = " + member.getMember_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Member getMember(Member member, Connection conn){
		Member _member=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM member WHERE 1=1");
		if (member.getMember_id()!=0) {
			sBuilder.append(" and member_id = " + member.getMember_id() +" ");
		}
		if (member.getCollege_id()!=0) {
			sBuilder.append(" and college_id = " + member.getCollege_id() +" ");
		}
		if (member.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + member.getUser_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Member.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _member = (Member)list.get(0);
		}
		return _member;
	}

	public List<Member>  listMembers(Member member, Connection conn){
		List<Member> members = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT m.*,u.real_name,c.college_name FROM member m join user u on m.user_id=u.user_id ");
		sBuilder.append("  join college c on m.college_id=c.college_id join user u2 on c.user_id=u2.user_id WHERE 1=1 ");

		if (member.getMember_id()!=0) {
			sBuilder.append(" and member_id = " + member.getMember_id() +" ");
		}
		if (member.getUser_id()!=0) {
			sBuilder.append(" and m.user_id = " + member.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(member.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + member.getReal_name() + "%'");
		}
		if (!StringUtil.isEmptyString(member.getCollege_name())) {
			sBuilder.append(" and c.college_name like '%" + member.getCollege_name() + "%'");
		}
		if (member.getMember_flag()!=0) {
			sBuilder.append(" and member_flag =" + member.getMember_flag() + " ");
		}
		if (member.getAdmin_id()!=0) {
			sBuilder.append(" and u2.user_id =" + member.getAdmin_id() + " ");
		}
		sBuilder.append(" order by member_id asc) t");

		if (member.getStart() != -1) {
			sBuilder.append(" limit " + member.getStart() + "," + member.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Member.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			members = new ArrayList<Member>();
			for (Object object : list) {
				members.add((Member)object);
			}
		}
		return members;
	}

	public int  listMembersCount(Member member, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM member m join user u on m.user_id=u.user_id join college c on m.college_id=c.college_id join user u2 on c.user_id=u2.user_id WHERE 1=1");

		if (member.getMember_id()!=0) {
			sBuilder.append(" and member_id = " + member.getMember_id() +" ");
		}
		if (member.getMember_id()!=0) {
			sBuilder.append(" and member_id = " + member.getMember_id() +" ");
		}
		if (member.getUser_id()!=0) {
			sBuilder.append(" and m.user_id = " + member.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(member.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + member.getReal_name() + "%'");
		}
		if (!StringUtil.isEmptyString(member.getCollege_name())) {
			sBuilder.append(" and c.college_name like '%" + member.getCollege_name() + "%'");
		}
		if (member.getMember_flag()!=0) {
			sBuilder.append(" and member_flag =" + member.getMember_flag() + " ");
		}
		if (member.getAdmin_id()!=0) {
			sBuilder.append(" and u2.user_id =" + member.getAdmin_id() + " ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}

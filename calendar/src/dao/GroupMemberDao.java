package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Component("groupMemberDao")
public class GroupMemberDao implements DaoInterface<GroupMember>  {

	private NamedParameterJdbcTemplate jdbc;
	


	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	


	public List<GroupMember> getList() {

		return jdbc.query("select * from GroupMembers", new RowMapper<GroupMember>() {

			public GroupMember mapRow(ResultSet rs, int rowNum) throws SQLException {
				GroupMember groupMember = new GroupMember();
				
				groupMember.setGroupMembersId(rs.getInt("groupMembersId"));
				groupMember.setGroupId(rs.getInt("groupId"));
				groupMember.setUserName(rs.getString("userName"));
		
				return groupMember;
			}

		});
	}
	

	
	public boolean update(GroupMember groupMember) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(groupMember);
		
		return jdbc.update("update GroupMembers set groupId=:groupId, UserName=:UserName "
				+ "where groupMembersId=:groupMembersId", params) == 1;
	}
	

	public boolean create(GroupMember groupMember) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(groupMember);
		
		/* insert them into groupmembers even if they are are in there*/
		return jdbc.update("insert IGNORE into GroupMembers (groupId, UserName) "
				+ "values ( :groupId, :UserName)", params) == 1;
	
	}
	

	
	public boolean delete(Object groupId) {
		MapSqlParameterSource params = new MapSqlParameterSource("groupId", groupId);
		
		return jdbc.update("delete from GroupMembers where groupId=:groupId", params) == 1;
	}

	public GroupMember getItem(Object groupMembersId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("groupMembersId", groupMembersId);

		return jdbc.queryForObject("select * from GroupMembers where groupMembersId=:groupMembersId", params,
				new RowMapper<GroupMember>() {

					public GroupMember mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						GroupMember groupMember = new GroupMember();
						
						groupMember.setGroupMembersId(rs.getInt("groupMembersId"));
						groupMember.setGroupId(rs.getInt("groupId"));
						groupMember.setUserName(rs.getString("userName"));
				
						return groupMember;
						
					}

				});
	}
	

	 
}

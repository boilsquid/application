package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Component("groupDao")
public class GroupDao implements DaoInterface<Group>  {

	private NamedParameterJdbcTemplate jdbc;
	


	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	


	public List<Group> getList() {

		return jdbc.query("select * from Groups", new RowMapper<Group>() {

			public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
				Group group = new Group();
				
				group.setGroupId(rs.getInt("groupId"));
				group.setGroupName(rs.getString("groupName"));
				group.setCreatedBy(rs.getString("createdBy"));
				group.setUserName(rs.getString("userName"));
				group.setDateCreated(rs.getTimestamp("dateCreated"));
		
				return group;
			}

		});
	}
	

	
	public boolean update(Group group) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(group);
		
		return jdbc.update("update Groups set groupName=:groupName, createdBy=:createdBy, UserName=:UserName, dateCreated=:dateCreated "
				+ "where groupId=:groupId", params) == 1;
	}
	

	public boolean create(Group group) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(group);
		
		/* insert them into groupmembers even if they are are in there*/
		return jdbc.update("insert IGNORE into Groups (groupName, createdBy, UserName, dateCreated) "
				+ "values ( :groupName, :createdBy, :UserName, now())", params) == 1;
	
	}
	
	/* returns the auto generated primary key*/
	public int createWithKey(Group group) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(group);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 
		 jdbc.update("insert into Groups (groupName, createdBy, UserName, dateCreated) "
					+ "values ( :groupName, :createdBy, :UserName, now())", params, keyHolder);
		 
		 		return keyHolder.getKey().intValue();
	}
	

	
	public boolean delete(Object groupId) {
		MapSqlParameterSource params = new MapSqlParameterSource("groupId", groupId);
		
		return jdbc.update("delete from Groups where groupId=:groupId", params) == 1;
	}

	public Group getItem(Object groupId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("groupId", groupId);

		return jdbc.queryForObject("select * from Groups where groupId=:groupId", params,
				new RowMapper<Group>() {

					public Group mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Group group = new Group();
						
						group.setGroupId(rs.getInt("groupId"));
						group.setGroupName(rs.getString("groupName"));
						group.setCreatedBy(rs.getString("createdBy"));
						group.setUserName(rs.getString("userName"));
						group.setDateCreated(rs.getTimestamp("dateCreated"));
				
						return group;
						
					}

				});
	}
	

	 
}

package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("UserDao")
public class UserDao implements DaoInterface<User> {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	



	public List<User> getList() {

		return jdbc.query("select * from users", new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRoleId(rs.getString("roleId"));
				user.setStreamId(rs.getInt("streamId"));
				user.setPassword(rs.getString("password"));
				user.setPasswordConfirmation(rs.getString("passwordConfirmation"));
				user.setCreatedAt(rs.getTimestamp("createdAt"));
				user.setUpdatedAt(rs.getTimestamp("updatedAt"));
				user.setSignInCount(rs.getInt("signInCount"));
				user.setEnabled(rs.getBoolean("enabled"));

				return user;
			}

		});
	}
	
	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return jdbc.update("update users set firstName=:firstName, lastName=:lastName, userName=:userName"
				+ "email=:email, phone=:phone, roleId=:roleId, streamId=:stream, passowrd=:password, "
				+ "passwordConfirmation=:passwordConfirmation,enabled=:enabled"
				+ "where id=:id", params) == 1;
	}
	
	public boolean create(User user) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		

		
		return jdbc.update("insert into Users (firstName, lastName,userName, email, phone, roleId,streamId,password, passwordConfirmation,"
				+ "createdAt, updatedAt, signInCount,enabled) "
				+ "values( :firstName, :lastName, :userName, :email, :phone, :roleId,:streamId,:password, :passwordConfirmation,"
				+ "now(), now(), :signInCount, :enabled) ", params) == 1;
	}
	

	@Transactional
	public int[] create(List<User> user) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(user.toArray());
		return jdbc.batchUpdate("insert into Users (firstName, lastName,userName; email, phone, roleId,streamId,password, passwordConfirmation,"
				+ "createdAt, updatedAt, signInCount, enabled) "
				+ "values( :firstName, :lastName, :userName, :email, :phone, :roleId,:streamId,:password, :passwordConfirmation,"
				+ "now(), now(), :signInCount, :enabled) ", params);

		
	}
	
	
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from Users where id=:id", params) == 1;
	}

	public User getItem(Object userName) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userName", userName);

		return jdbc.queryForObject("select * from Users where userName=:userName", params,
				new RowMapper<User>() {

					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();

						user.setId(rs.getInt("id"));
						user.setUserName(rs.getString("userName"));
						user.setFirstName(rs.getString("firstName"));
						user.setLastName(rs.getString("lastName"));
						user.setEmail(rs.getString("email"));
						user.setPhone(rs.getString("phone"));
						user.setRoleId(rs.getString("roleId"));
						user.setStreamId(rs.getInt("streamId"));
						user.setPassword(rs.getString("password"));
						user.setPasswordConfirmation(rs.getString("passwordConfirmation"));
						user.setCreatedAt(rs.getTimestamp("createdAt"));
						user.setUpdatedAt(rs.getTimestamp("updatedAt"));
						user.setSignInCount(rs.getInt("signInCount"));
						user.setEnabled(rs.getBoolean("enabled"));

						return user;
						
					}

				});
	}
	
}

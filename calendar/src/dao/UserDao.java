package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("UserDao")
public class UserDao {

	private NamedParameterJdbcTemplate jdbc;
	
	public UserDao() {
		System.out.println("Successfully loaded user DAO");
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	

	public List<User> getUsers() {

		return jdbc.query("select * from user", new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getInt("phone"));
				user.setRole(rs.getString("role"));
				user.setStream(rs.getString("stream"));
				

				return user;
			}

		});
	}
	
	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return jdbc.update("update user set firstName=:firstName, lastName=:lastName, "
				+ "email=:email phone=:phone role=:role stream=:stream  "
				+ "where id=:id", params) == 1;
	}
	
	public boolean create(User user) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return jdbc.update("insert into User (id,firstName, lastName, email, phone, role,stream) "
				+ "values (:id,:firstName, :lastName, :email, :phone, :role, :stream)", params) == 1;
	}
	

	
	
	
	
	@Transactional
	public int[] create(List<User> user) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(user.toArray());
		
		return jdbc.batchUpdatedbc.update("insert into User (id,firstName, lastName, email, phone, role,stream) "
				+ "values (:id,:firstName, :lastName, :email, :phone, :role, :stream)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from User where id=:id", params) == 1;
	}

	public User getUser(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from User where id=:id", params,
				new RowMapper<User>() {

					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setFirstName(rs.getString("firstName"));
						user.setLastName(rs.getString("lastName"));
						user.setEmail(rs.getString("email"));
						user.setPhone(rs.getInt("phone"));
						user.setRole(rs.getString("role"));
						user.setStream(rs.getString("stream"));
						

						return user;
						
					}

				});
	}
	
}

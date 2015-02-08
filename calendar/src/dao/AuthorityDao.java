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

@Component("AuthorityDao")
public class AuthorityDao implements DaoInterface<Authority>  {

	private NamedParameterJdbcTemplate jdbc;
	

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	

	public List<Authority> getList() {

		return jdbc.query("select * from Authorities", new RowMapper<Authority>() {

			public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
				Authority authority = new Authority();

				authority.setId(rs.getInt("id"));
				authority.setUserName(rs.getString("userName"));
				authority.setAuthority(rs.getString("authority"));
			
				return authority;
			}

		});
	}
	
	public boolean update(Authority authority) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(authority);
		
		return jdbc.update("update streams set stream=:stream, year=:year "
				+ "where streamId=:streamId", params) == 1;
	}
	
	public boolean create(Authority authority) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(authority);
		
		return jdbc.update("insert into authorities (userName, authority) "
				+ "values ( :userName, :authority)", params) == 1;
	}
	

	//@Transactional
	public int[] create(List<Authority> authority) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(authority.toArray());
		return jdbc.batchUpdate("insert into streams (stream, year) "
				+ "values (:stream, :year)", params);
		
	}
	
	
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("streamId", id);
		
		return jdbc.update("delete from Stream where streamId=:streamId", params) == 1;
	}

	public Authority getItem(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from authorities where id=:id", params,
				new RowMapper<Authority>() {

					public Authority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Authority authority = new Authority();

						authority.setId(rs.getInt("id"));
						authority.setUserName(rs.getString("userName"));
						authority.setAuthority(rs.getString("authority"));
					
						return authority;
					}
						
					

				});
	}



	
}
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


@Component("streamDao")
public class StreamDao implements DaoInterface<Stream>  {

	private NamedParameterJdbcTemplate jdbc;
	


	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	

	public List<Stream> getList() {

		return jdbc.query("select * from Streams", new RowMapper<Stream>() {

			public Stream mapRow(ResultSet rs, int rowNum) throws SQLException {
				Stream stream = new Stream();

				stream.setStream(rs.getString("stream"));
				stream.setStreamId(rs.getInt("streamId"));
				stream.setYear(rs.getString("year"));
		
				return stream;
			}

		});
	}
	
	public boolean update(Stream stream) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(stream);
		
		return jdbc.update("update streams set stream=:stream, year=:year "
				+ "where streamId=:streamId", params) == 1;
	}
	

	public boolean create(Stream stream) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(stream);
		
		return jdbc.update("insert into streams (stream, year) "
				+ "values ( :stream, :year)", params) == 1;
	
	}
	

	@Transactional
	public int[] create(List<Stream> stream) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(stream.toArray());
		return jdbc.batchUpdate("insert into streams (stream, year) "
				+ "values (:stream, :year)", params);
		
	}
	
	
	
	public boolean delete(int streamId) {
		MapSqlParameterSource params = new MapSqlParameterSource("streamId", streamId);
		
		return jdbc.update("delete from Stream where streamId=:streamId", params) == 1;
	}

	public Stream getItem(int streamId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("streamId", streamId);

		return jdbc.queryForObject("select * from Stream where streamId=:streamId", params,
				new RowMapper<Stream>() {

					public Stream mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Stream stream = new Stream();
						
						
						stream.setStream(rs.getString("stream"));
						stream.setStreamId(rs.getInt("StreamId"));
						stream.setYear(rs.getString("year"));
				
						return stream;
						
					}

				});
	}
	

	 
}
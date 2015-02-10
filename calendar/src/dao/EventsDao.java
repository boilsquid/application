package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
@Component("eventsDao")
public class EventsDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	

	public List<Events> getList() {

		return jdbc.query("select * from Events", new RowMapper<Events>() {

			public Events mapRow(ResultSet rs, int rowNum) throws SQLException {
				Events events = new Events();

				events.setId(rs.getInt("id"));
				events.setUserName(rs.getString("userName"));
				events.setStart(rs.getTimestamp("start"));
				events.setEnd(rs.getTimestamp("end"));
				events.setTitle(rs.getString("title"));
				
			
				return events;
			}

		});
	}

}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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

@Component("LectureDao")
public class LectureDao implements DaoInterface<Lecture> {

	private NamedParameterJdbcTemplate jdbc;
	

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	


	public List<Lecture> getList() {

		return jdbc.query("select * from Lecturemastertimetable", new RowMapper<Lecture>() {

			public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Lecture lecture = new Lecture();
				
				lecture.setLectureId(rs.getInt("lectureId"));
				lecture.setStreamId(rs.getInt("streamId"));
				lecture.setModuleId(rs.getInt("moduleId"));
				lecture.setDay(rs.getString("day"));
				lecture.setStartTime(rs.getTime("startTime"));
				lecture.setDuration(rs.getInt("duration"));
				lecture.setSemester(rs.getString("semester"));
				
				
				return lecture;
			}

		});
	}
	
	
	
	public boolean update(Lecture lecture) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(lecture);
		
		return jdbc.update("update Lecturemastertimetable set streamId=:streamId, moduleId=:moduleId,"
				+ "day=:day, startTime=:startTime, duration=:duration, semester:=semester "
				+ "where lectureId=:lectureId", params) == 1;
	}
	
	public boolean create(Lecture lecture) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(lecture);
		
		return jdbc.update("insert into Lecturemastertimetable (streamId, moduleId, day, startTime, duration, semester) "
				+ "values ( :streamId, :moduleId, :day, :startTime, :duration, :semester)", params) == 1;
	}
	

	@Transactional
	public int[] create(List<Lecture> lecture) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(lecture.toArray());
		return jdbc.batchUpdate("insert into Lecturemastertimetable (lectureId, streamId, moduleId, day, startTime, duration, semester) "
				+ "values (:lectureId :streamId, :moduleId, :day, :startTime, :duration, :semester)", params);
		
	}
	
	
	
	public boolean delete(int lectureId) {
		MapSqlParameterSource params = new MapSqlParameterSource("lectureId", lectureId);
		
		return jdbc.update("delete from Lecturemastertimetable where lectureId=:lectureId", params) == 1;
	}

	public Lecture getItem(int lectureId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("lectureId", lectureId);

		return jdbc.queryForObject("select * from Lecturemastertimetable where lectureId=:lectureId", params,
				new RowMapper<Lecture>() {

					public Lecture mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Lecture lecture = new Lecture();
						
						lecture.setLectureId(rs.getInt("lectureId"));
						lecture.setStreamId(rs.getInt("streamId"));
						lecture.setModuleId(rs.getInt("moduleId"));
						lecture.setDay(rs.getString("day"));
						lecture.setStartTime(rs.getTime("startTime"));
						lecture.setDuration(rs.getInt("duration"));
						lecture.setSemester(rs.getString("semester"));
						
						
						return lecture;
						
					}

				});
	}




}

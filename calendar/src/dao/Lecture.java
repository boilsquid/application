package dao;



import java.sql.Time;

public class Lecture {
	
	private int lectureId;
	private int streamId;
	private String moduleId;
	private String day;
	private Time startTime;
	private int duration;
	private String semester;
	
	public Lecture(){
		
	}
	
	public Lecture(int lectureId, int streamId, String moduleId, String day,
			Time startTime, int duration, String semester) {
		super();
		this.lectureId = lectureId;
		this.streamId = streamId;
		this.moduleId = moduleId;
		this.day = day;
		this.startTime = startTime;
		this.duration = duration;
		this.semester = semester;
	}
	public int getLectureId() {
		return lectureId;
	}
	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	@Override
	public String toString() {
		return "Lecture [lectureId=" + lectureId + ", streamId=" + streamId
				+ ", moduleId=" + moduleId + ", day=" + day + ", startTime="
				+ startTime + ", duration=" + duration + ", semester="
				+ semester + "]";
	}
	
	
	

}

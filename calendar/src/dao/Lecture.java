package dao;



import java.sql.Time;
import java.sql.Timestamp;

public class Lecture {

	
	private int lectureId;
	private int streamId;
	private String moduleId;
	private String day;
	private Timestamp start;
	private Timestamp end;
	private int recurring;
	private String semester;
	
	public Lecture(){
		
	}
	
	
	
	public Lecture(int lectureId, int streamId, String moduleId, String day,
			Timestamp start, Timestamp end, int recurring, String semester) {
		super();
		this.lectureId = lectureId;
		this.streamId = streamId;
		this.moduleId = moduleId;
		this.day = day;
		this.start = start;
		this.end = end;
		this.recurring = recurring;
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

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public int getRecurring() {
		return recurring;
	}

	public void setRecurring(int recurring) {
		this.recurring = recurring;
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
				+ ", moduleId=" + moduleId + ", day=" + day + ", start="
				+ start + ", end=" + end + ", recurring=" + recurring
				+ ", semester=" + semester + "]";
	}
	
	
	
	

}

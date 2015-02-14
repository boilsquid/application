package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



public class Stream {
	
	
	private int streamId;
	private String year;
	private String stream;
	
	public Stream(int streamId, String year, String stream) {
		super();
		this.streamId = streamId;
		
		this.year = year;
		this.stream = stream;
	}
	
	public Stream(){
		
	}
	
	
	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	

	
	@Override
	public String toString() {
		return "Stream [streamId=" + streamId + ", year=" + year + ", stream="
				+ stream + "]";
	}
	
	
	

}

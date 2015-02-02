package dao;



public class Stream {
			
	private String streamId;
	private String year;
	private String stream;
	
	public Stream(String streamId, String year, String stream) {
		super();
		this.streamId = streamId;
		this.year = year;
		this.stream = stream;
	}
	
	public Stream(){
		
	}
	
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
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

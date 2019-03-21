package intercorp.retail.kpi.intercorpretail.dto;

public class ErrorDetailsDTO {
	private String timestamp;
	private String message;

	public ErrorDetailsDTO(String timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

}
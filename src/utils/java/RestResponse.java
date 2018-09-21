public class RestResponse {
	private final  Integer m_responseCode;
	private final String m_message;
	public RestResponse(Integer p_responseCode, String p_message) {
		m_responseCode = p_responseCode;
		m_message = p_message;
	}
	public Integer getResponseCode() {
		return m_responseCode;
	}
	public String getMessage() {
		return m_message;
	}
}

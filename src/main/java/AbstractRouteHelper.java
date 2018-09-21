import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

public abstract class AbstractRouteHelper implements IRouteHelper , IJsonHelper{
	private final Request m_request;
	private final Response m_response;
	private static ObjectMapper om = new ObjectMapper();
	AbstractRouteHelper(Request p_request, Response p_response) {
		super();
		m_request = p_request;
		m_response = p_response;
	}
	@Override
	public String getRouteParams(String p_key) {
		return m_request.params(p_key);
	}
	@Override
	public String getParam(String p_key) {
		return m_request.queryParams(p_key);
	}
	@Override
	public void setStatus(Integer p_statusCode) {
		m_response.status(p_statusCode);
	}
	@Override
	public String writeValue(Object p_value) throws JsonProcessingException {
		return om.writeValueAsString(p_value);
	}
}

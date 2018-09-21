import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJsonHelper implements  IJsonHelper {
	private static final ObjectMapper s_objectMapper = new ObjectMapper();
	@Override
	public String writeValue(Object p_value) throws JsonProcessingException {
		return s_objectMapper.writeValueAsString(p_value);
	}
}

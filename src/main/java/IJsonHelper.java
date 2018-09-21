import com.fasterxml.jackson.core.JsonProcessingException;

interface IJsonHelper {
	String writeValue (Object p_value) throws JsonProcessingException;
}

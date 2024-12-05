import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;
import org.junit.Assert;
import org.junit.Test;

public class TestCases {

    ApiDriver apiDriver = new ApiDriver();

    @Test
    public void api_basic_request_from_yaml() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
    }

    @Test
    public void api_basic_request_from_yaml_with_query_params() {
        apiDriver.setConfigFromYaml("catfact_facts");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
    }
}

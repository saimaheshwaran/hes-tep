import org.sm.utilities.YamlReader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TestCases {

    @Test
    public void YamlReaderTest() throws IOException {

        YamlReader yamlReader = new YamlReader();
        Map<?,?> yamlData = yamlReader.getYamlDataFromFolder(System.getProperty("user.dir") + "/src/test/resources");
        System.out.println(yamlReader.convertYamlDataToJson(yamlData));
        System.out.println(yamlReader.convertYamlDataToString(yamlData));
    }
}

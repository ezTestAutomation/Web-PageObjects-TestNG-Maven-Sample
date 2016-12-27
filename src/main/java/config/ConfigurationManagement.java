package config;

/**
 * Created by nguyen.pham on 12/27/16.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationManagement {

    InputStream inputStream;

    public Map<String, String> getPropValues() throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = this.getClass().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result.put("faceBookUsername", prop.getProperty("faceBookUsername"));
            result.put("faceBookPassword", prop.getProperty("faceBookPassword"));
            result.put("faceBookURL", prop.getProperty("faceBookURL"));
            result.put("faceBookMessage", prop.getProperty("faceBookMessage"));

            result.put("browserType", prop.getProperty("browserType"));
            result.put("timeOut", prop.getProperty("timeOut"));
            result.put("urlSignIn", prop.getProperty("urlSignIn"));
            result.put("urlReview", prop.getProperty("urlReview"));
            result.put("urlProfile", prop.getProperty("urlProfile"));
            result.put("walletHubUsername", prop.getProperty("walletHubUsername"));
            result.put("walletHubPassword", prop.getProperty("walletHubPassword"));
            result.put("walletHubMessage", prop.getProperty("walletHubMessage"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}


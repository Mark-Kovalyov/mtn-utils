package mayton.lib;

import org.apache.commons.lang3.Validate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * <h1>The Universal configurator</h1>
 * based on properties, system props, and environment variables.<br>
 *
 * The route is:<br>
 * <ol>1)Lookup in application config</ol>
 * <ol>2)Or else lookup in Java system props</ol>
 * <ol>3) Or else lookup in OS env</ol>
 *
 * Agreements are:<br>
 * <pre>
 * Command line argument    :   --baseConfigPath
 * Application property name:   baseConfigPath
 * Java system property name:   baseConfigPath
 * OS env name              :   BASE_CONFIG_PATH
 * </pre>
 */
public class Uniconf {

    private Properties properties = null;

    private String[] commandLine = null;

    public Uniconf(String applicationConfig) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(applicationConfig));
    }

    public Uniconf() {
    }

    public String lookupProperty(String key, String defaultValue) {
        return lookupProperty(key).orElse(defaultValue);
    }

    public Optional<String> lookupProperty(String key) {
        Validate.notBlank(key, "The lookupProperty accepts only non blank keys!");
        String lower = key.toLowerCase();
        String upper = key.toUpperCase();
        if (properties != null && properties.contains(lower)) {
            return Optional.of(properties.getProperty(lower)); // baseConfigPath=
        } else if (System.getProperty(lower) != null){
            return Optional.of(System.getProperty(lower)); // baseConfigPath=
        } else if (System.getenv(upper) != null){
            return Optional.of(System.getenv(upper)); // BASE_CONFIG_PATH=
        } else {
            return Optional.empty();
        }
    }



}

package mayton.lib;

import org.apache.commons.lang3.StringUtils;

public class CamelUtils {

    private CamelUtils() {}

    // GPT_ORG_ID => gptOrgId
    public static String dashToCamel(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        boolean nextUpper = false;
        for(char c : s.toCharArray()) {
            if (nextUpper) {
                sb.append(Character.toUpperCase(c));
                nextUpper = false;
            } else {
                if (c == '_') {
                    nextUpper = true;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }

    // gptOrgId => GPT_ORG_ID
    public static String camelToDash(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for(char c : s.toCharArray()) {
            if (c == '.') {
                sb.append("_");
                continue;
            }
            if (Character.isUpperCase(c)) {
                sb.append("_");
            }
            sb.append(Character.toUpperCase(c));
        }
        return StringUtils.replace(sb.toString(), "__", "_");
    }

    public static String commandLineToDash(String s) {
        return StringUtils.replace(s.substring(2), "-", "_").toUpperCase();
    }
}

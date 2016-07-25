package yazabara;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Yaroslav Zabara
 */
public class AlphabetUtils {

    public static String replace(String str, Map<String, String> alphabet) {
        String newStr = StringUtils.EMPTY;
        if (StringUtils.isBlank(str)) {
            return newStr;
        }
        for (char ch : str.toCharArray()) {
            if (alphabet.containsKey(String.valueOf(ch))) {
                newStr += alphabet.get(String.valueOf(ch));
            } else {
                newStr += String.valueOf(ch);
            }
        }
        return newStr;
    }
}

package sk.janobono.ccl;

import java.text.Normalizer;
import java.util.Objects;

public class ScDf {

    private ScDf() {
    }

    public static String toDf(String text) {
        if (Objects.isNull(text) || text.length() == 0) {
            return null;
        } else {
            StringBuilder ret = new StringBuilder();
            char[] cha = text.toCharArray();
            for (char aCha : cha) {
                byte[] ba = Normalizer.normalize(String.valueOf(aCha), Normalizer.Form.NFD).getBytes();
                if (ba[0] >= 41 && ba[0] < 123) {
                    ret.append((char) ba[0]);
                } else {
                    ret.append(aCha);
                }
            }
            return ret.toString();
        }
    }

    public static String toScDf(String text) {
        if (Objects.isNull(text) || text.length() == 0) {
            return null;
        } else {
            return Objects.requireNonNull(toDf(text)).toLowerCase().trim();
        }
    }
}

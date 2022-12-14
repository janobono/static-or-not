package sk.janobono.ccl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScDfTest {

    private static final String TEXT = "ľščťžýáíéňäúô ĽŠČŤŽÝÁÍÉŇÄÚÔ";
    private static final String DF_RESULT = "lsctzyaienauo LSCTZYAIENAUO";
    private static final String SCDF_RESULT = "lsctzyaienauo lsctzyaienauo";

    @Test
    void toDf_TestText_EqualsToExpectedResult() {
        assertThat(ScDf.toDf(TEXT)).isEqualTo(DF_RESULT);
    }

    @Test
    void toScDf_TestText_EqualsToExpectedResult() {
        assertThat(ScDf.toScDf(TEXT)).isEqualTo(SCDF_RESULT);
    }
}

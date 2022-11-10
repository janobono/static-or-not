package sk.janobono.oocp.pst.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.janobono.ccl.RandomString;
import sk.janobono.ccl.ScDf;
import sk.janobono.oocp.common.CodeFormatter;
import sk.janobono.oocp.pst.component.PstDataUtil;
import sk.janobono.oocp.pst.model.PstData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class PstService {

    private final RandomString randomString;

    private final CodeFormatter codeFormatter;

    private final PstDataUtil dataUtil;

    public PstData getPstData(final String prefix, final Long codeNumber, int minScore, int maxScore) {
        log.debug("getPstData({},{})", prefix, codeNumber);

        final String scdfPrefix = ScDf.toScDf(prefix);
        log.debug("scdfPrefix = {}", scdfPrefix);

        final String code = codeFormatter.format(scdfPrefix, codeNumber);
        log.debug("code = {}", code);

        final String generatedName = randomString.alphabet(20);
        log.debug("generatedName = {}", generatedName);

        final int score = ThreadLocalRandom.current().nextInt(minScore, maxScore + 1);
        log.debug("score = {}", score);

        return new PstData(code, generatedName, score);
    }

    public Map<Integer, Long> getCountMap(List<PstData> dataList) {
        log.debug("getCountMap({})", dataList);

        final List<Integer> scoreList = dataUtil.getScoreList(dataList);
        log.debug("scoreList = {}", scoreList);

        Map<Integer, Long> result = new HashMap<>();

        for (Integer score : scoreList) {
            final Long scoreCount = dataUtil.countByScore(score, dataList);
            log.debug("score {} count = {}", score, scoreCount);
            result.put(score, scoreCount);
        }

        log.debug("getCountMap({})={}", dataList, result);
        return result;
    }
}

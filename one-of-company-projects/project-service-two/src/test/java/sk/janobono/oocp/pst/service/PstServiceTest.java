package sk.janobono.oocp.pst.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.janobono.oocp.pst.model.PstData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class PstServiceTest {

    @Autowired
    public PstService pstService;

    @Test
    public void generateDataListAndCountScoreMap_NoException() throws Exception {
        List<PstData> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(pstService.getPstData("Test" + i, (long) i, 0, 10));
        }
        Map<Integer, Long> scoreMap = pstService.getCountMap(dataList);
        log.debug("scoreMap = {}", scoreMap);
    }
}

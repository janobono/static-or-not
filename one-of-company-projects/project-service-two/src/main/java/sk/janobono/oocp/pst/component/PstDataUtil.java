package sk.janobono.oocp.pst.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sk.janobono.oocp.pst.model.PstData;

import java.util.List;

@Component
@Slf4j
public class PstDataUtil {

    public List<Integer> getScoreList(List<PstData> dataList) {
        log.debug("getScoreList({})", dataList);
        return dataList.stream().map(PstData::score).distinct().sorted().toList();
    }

    public long countByScore(int score, List<PstData> dataList) {
        log.debug("countByScore({},{})", score, dataList);
        return dataList.stream().filter(d -> d.score() == score).count();
    }
}

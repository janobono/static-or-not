package sk.janobono.oocp.pso.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.janobono.ccl.ScDf;
import sk.janobono.oocp.common.CCLImageUtil;
import sk.janobono.oocp.common.CodeFormatter;
import sk.janobono.oocp.pso.component.PsoLocalStorage;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class PsoService {

    private final PsoLocalStorage localStorage;

    private final CCLImageUtil imageUtil;

    private final CodeFormatter codeFormatter;

    public Path getGeneratedCodeImage(final String prefix, final Long codeNumber) {
        log.debug("generateCodeImage({},{})", prefix, codeNumber);

        final String scdfPrefix = ScDf.toScDf(prefix);
        log.debug("scdfPrefix = {}", scdfPrefix);

        final String formattedCode = codeFormatter.format(scdfPrefix, codeNumber);
        log.debug("formattedCode = {}", formattedCode);

        Path result = localStorage.createTempFile("PSO", ".png");
        localStorage.save(result, imageUtil.generateMessageImage(formattedCode));
        return result;
    }
}

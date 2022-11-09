package sk.janobono.oocp.pso.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@SpringBootTest
@Slf4j
class PsoServiceTest {

    @Autowired
    public PsoService psoService;

    @Test
    public void generateCodeImage_NoException() throws Exception {
        Path generatedImage = psoService.getGeneratedCodeImage("ľščťžýáíé", 1L);
        Path tempFile = Files.createTempFile("PSO", ".png");
        log.debug("target path {}", tempFile);
        Files.copy(generatedImage, tempFile, StandardCopyOption.REPLACE_EXISTING);
    }
}

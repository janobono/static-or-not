package sk.janobono.ccl;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class ImageUtilTest {

    @Test
    void scalePNGandJPGImagesGenerateMessageImages_smallerImagesAfterScaleAndNotEmptyGeneratedMessageImages() throws Exception {
        ImageUtil imageUtil = new ImageUtil();

        Path pngFilePath = Path.of(Objects.requireNonNull(ImageUtilTest.class.getResource("/Debian_logo.png")).toURI());
        byte[] originalData = getFileData(pngFilePath);
        byte[] scaledData = imageUtil.scaleImage("image/png", originalData, 100, 100);
        assertThat(originalData.length > scaledData.length).isTrue();

        scaledData = imageUtil.scaleImageAndCropToSquare("image/png", originalData, 100);
        assertThat(originalData.length > scaledData.length).isTrue();

        Path jpgFilePath = Path.of(Objects.requireNonNull(ImageUtilTest.class.getResource("/wallpaper.jpg")).toURI());
        originalData = getFileData(jpgFilePath);
        scaledData = imageUtil.scaleImage("image/jpg", originalData, 100, 100);
        assertThat(originalData.length > scaledData.length).isTrue();

        scaledData = imageUtil.scaleImageAndCropToSquare("image/jpg", originalData, 100);
        assertThat(originalData.length > scaledData.length).isTrue();

        byte[] generatedData = imageUtil.generateMessageImage(null);
        assertThat(generatedData.length > 0).isTrue();

        generatedData = imageUtil.generateMessageImage("Some text");
        assertThat(generatedData.length > 0).isTrue();
    }

    private byte[] getFileData(Path file) throws Exception {
        try (
                InputStream is = new BufferedInputStream(new FileInputStream(file.toFile()));
                ByteArrayOutputStream os = new ByteArrayOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while (bytesRead != -1) {
                bytesRead = is.read(buffer);
                if (bytesRead > 0) {
                    os.write(buffer, 0, bytesRead);
                }
            }
            return os.toByteArray();
        }
    }
}

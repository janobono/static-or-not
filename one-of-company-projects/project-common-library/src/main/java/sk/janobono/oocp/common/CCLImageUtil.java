package sk.janobono.oocp.common;

import sk.janobono.ccl.ImageUtil;

import java.io.IOException;

public class CCLImageUtil extends ImageUtil {

    @Override
    public byte[] scaleImage(String fileType, byte[] data, int maxWidth, int maxHeight) {
        try {
            return super.scaleImage(fileType, data, maxWidth, maxHeight);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public byte[] scaleImageAndCropToSquare(String fileType, byte[] data, int maxSize) {
        try {
            return super.scaleImageAndCropToSquare(fileType, data, maxSize);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public byte[] generateMessageImage(String message) {
        try {
            return super.generateMessageImage(message);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }
}

package sk.janobono.oocp.common;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class LocalStorageIT {

    @Test
    public void initStorageSaveAndClean_NoException() {
        LocalStorage localStorage = new LocalStorage();
        localStorage.init();

        Path path = localStorage.createTempFile("Test", ".bin");
        localStorage.save(path, new byte[]{102, 103, 104});

        localStorage.clean();
    }
}

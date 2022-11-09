package sk.janobono.oocp.common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class LocalStorage {

    protected Path storageLocation;

    public void init() {
        try {
            this.storageLocation = Files.createTempDirectory("local-storage");
        } catch (IOException e) {
            throw new ApplicationException("Could not create the local storage directory.", e);
        }
    }

    public void clean() {
        delete(storageLocation);
    }

    public Path createTempFile(String prefix, String suffix) {
        try {
            return Files.createTempFile(storageLocation, prefix, suffix);
        } catch (IOException e) {
            throw new ApplicationException("Local storage exception.", e);
        }
    }

    public void save(Path file, byte[] data) {
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file.toFile(), false));
            os.write(data, 0, data.length);
        } catch (Exception e) {
            throw new ApplicationException("Local storage exception.", e);
        } finally {
            close(os);
        }
    }

    public void delete(Path path) {
        try {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new ApplicationException("Local storage exception.", e);
        }
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

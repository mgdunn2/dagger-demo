package elastictweets.util;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class Closer implements AutoCloseable {
    private Set<AutoCloseable> closeables = new HashSet<>();
    private boolean closed = false;

    @Inject
    public Closer() {}
    public synchronized <T extends AutoCloseable> T register(T closeable) {
        if (closed) {
            throw new IllegalStateException("Closer has already been closed.");
        }
        closeables.add(closeable);
        return closeable;
    }
    @Override
    public synchronized void close() throws Exception {
        Exception exception = null;
        for (AutoCloseable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
                if (exception != null) {
                    exception.addSuppressed(e);
                } else {
                    exception = e;
                }
            }
        }
        closed = true;
        if (exception != null) {
            throw exception;
        }
    }
}

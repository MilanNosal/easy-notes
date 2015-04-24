package easynotes.utilities;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class AutoFileCloser {
    protected abstract void doWork() throws IOException;

    private List<Closeable> closeables_ = new LinkedList<>();

    protected final <T extends Closeable> T autoClose(T closeable) {
            closeables_.add(0, closeable);
            return closeable;
    }

    public AutoFileCloser() throws IOException {
        IOException pending = null;

        try {
            doWork();

        } catch (IOException throwable) {
            pending = throwable;

        } finally {
            for (Closeable closeable : closeables_) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException throwable) {
                        if (pending == null) {
                            pending = throwable;
                        }
                    }
                }
            }
            if (pending != null) {
                throw pending;
            }
        }
    }
}
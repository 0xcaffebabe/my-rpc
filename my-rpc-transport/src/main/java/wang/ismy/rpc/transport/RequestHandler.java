package wang.ismy.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author MY
 * @date 2020/5/9 9:46
 */
public interface RequestHandler {
    void onRequest(InputStream request, OutputStream response);
}

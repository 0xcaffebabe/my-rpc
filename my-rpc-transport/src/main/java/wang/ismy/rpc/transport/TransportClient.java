package wang.ismy.rpc.transport;

import wang.ismy.rpc.Peer;

import java.io.InputStream;

/**
 * @author MY
 * @date 2020/5/9 9:43
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}

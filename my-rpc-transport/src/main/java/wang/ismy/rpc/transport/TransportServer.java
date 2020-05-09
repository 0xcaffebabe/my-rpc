package wang.ismy.rpc.transport;

/**
 * @author MY
 * @date 2020/5/9 9:45
 */
public interface TransportServer {
    void init(int port,RequestHandler handler);

    void listen();

    void close();
}

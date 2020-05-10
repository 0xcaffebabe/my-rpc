package wang.ismy.rpc.client;

import wang.ismy.rpc.Peer;
import wang.ismy.rpc.transport.TransportClient;

import java.util.List;

/**
 * @author MY
 * @date 2020/5/10 13:02
 */
public interface TransportSelector {

    void init(List<Peer> peers,int count,Class<? extends TransportClient> klass);

    TransportClient select();

    void release(TransportClient client);

    void close();
}

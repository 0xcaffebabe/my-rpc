package wang.ismy.rpc.client;

import wang.ismy.rpc.Peer;
import wang.ismy.rpc.common.ReflectionUtils;
import wang.ismy.rpc.transport.TransportClient;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author MY
 * @date 2020/5/10 13:07
 */
public class RandomTransportSelector implements TransportSelector {
    private List<TransportClient> clients = new LinkedList<>();
    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> klass) {
        count = Math.max(count,1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(klass);
                client.connect(peer);
                clients.add(client);
            }
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
    }
}

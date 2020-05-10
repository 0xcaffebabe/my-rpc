package wang.ismy.rpc.demo;

import wang.ismy.rpc.server.RpcServer;
import wang.ismy.rpc.server.RpcServerConfig;

/**
 * @author MY
 * @date 2020/5/10 13:29
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(Service.class,new ServiceImpl());
        server.start();
    }
}

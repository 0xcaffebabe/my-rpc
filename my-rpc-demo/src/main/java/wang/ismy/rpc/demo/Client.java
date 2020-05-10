package wang.ismy.rpc.demo;

import wang.ismy.rpc.client.RpcClient;

/**
 * @author MY
 * @date 2020/5/10 13:29
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        Service service = client.getProxy(Service.class);
        System.out.println(service.hello("cxkk"));
        System.out.println(service.word("cxkk"));
    }
}

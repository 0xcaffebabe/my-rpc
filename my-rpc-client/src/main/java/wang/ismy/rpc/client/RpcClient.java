package wang.ismy.rpc.client;

import wamg.ismy.rpc.codec.Decoder;
import wamg.ismy.rpc.codec.Encoder;
import wang.ismy.rpc.common.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author MY
 * @date 2020/5/10 13:15
 */
public class RpcClient {
    private RpcClientConfig clientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient(RpcClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.encoder = ReflectionUtils.newInstance(clientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(clientConfig.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(clientConfig.getSelectorClass());
        this.selector.init(clientConfig.getServers(),
                clientConfig.getConnectCount(),
                clientConfig.getTransportClass());
    }

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public <T> T getProxy(Class<T> klass){
        return (T)Proxy.newProxyInstance(klass.getClassLoader(),new Class[]{klass},new RemoteInvoker(klass,encoder,decoder,selector));
    }

}

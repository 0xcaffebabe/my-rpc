package wang.ismy.rpc.client;

import org.apache.commons.io.IOUtils;
import wamg.ismy.rpc.codec.Decoder;
import wamg.ismy.rpc.codec.Encoder;
import wang.ismy.rpc.Request;
import wang.ismy.rpc.Response;
import wang.ismy.rpc.ServiceDescription;
import wang.ismy.rpc.transport.TransportClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.ClientInfoStatus;

/**
 * @author MY
 * @date 2020/5/10 13:19
 */
public class RemoteInvoker implements InvocationHandler {
    private Class<?> klass;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public RemoteInvoker(Class<?> klass, Encoder encoder, Decoder decoder, TransportSelector transportSelector) {
        this.klass = klass;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request(ServiceDescription.from(klass,method),args);
        Response response = invokeRemote(request);
        assert response != null;
        if (response.getCode() != 0){
            throw new RuntimeException("调用失败"+response);
        }
        return response.getData();
    }
    private Response invokeRemote(Request request){
        TransportClient client = transportSelector.select();
        try {
            InputStream receive = client.write(new ByteArrayInputStream(encoder.encode(request)));
            return decoder.decode(IOUtils.readFully(receive,receive.available()),Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            transportSelector.release(client);
        }
        return null;
    }
}

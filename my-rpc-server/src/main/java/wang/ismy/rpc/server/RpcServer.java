package wang.ismy.rpc.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import wamg.ismy.rpc.codec.Decoder;
import wamg.ismy.rpc.codec.Encoder;
import wang.ismy.rpc.Request;
import wang.ismy.rpc.Response;
import wang.ismy.rpc.common.ReflectionUtils;
import wang.ismy.rpc.transport.RequestHandler;
import wang.ismy.rpc.transport.TransportServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * @author MY
 * @date 2020/5/10 9:49
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer server;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream request, OutputStream response) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(request,request.available());
                Request req = decoder.decode(inBytes,Request.class);
                log.info("请求到达:{}",req);
                ServiceInstance instance = serviceManager.lookup(req);
                Object ret = serviceInvoker.invoke(instance,req);
                resp.setData(ret);
            } catch (IOException e) {
                log.error("执行服务错误",e);
                resp.setCode(1);
                resp.setMsg("invoke error:"+e.getMessage());
            }finally {
                byte[] outBytes = encoder.encode(resp);
                try {
                    response.write(outBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.server = ReflectionUtils.newInstance(config.getTransportClass());
        this.server.init(config.getPort(),this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        serviceManager = new ServiceManager();
        serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        serviceManager.register(interfaceClass,bean);
    }
    public void start(){
        server.listen();
    }
    public void stop(){
        server.close();
    }

}

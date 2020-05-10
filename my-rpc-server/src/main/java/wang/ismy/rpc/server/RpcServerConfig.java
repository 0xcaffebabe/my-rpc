package wang.ismy.rpc.server;

import lombok.Data;
import wamg.ismy.rpc.codec.Decoder;
import wamg.ismy.rpc.codec.Encoder;
import wamg.ismy.rpc.codec.JsonDecoder;
import wamg.ismy.rpc.codec.JsonEncoder;
import wang.ismy.rpc.transport.HttpTransportServer;
import wang.ismy.rpc.transport.TransportServer;

/**
 * @author MY
 * @date 2020/5/10 9:24
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private int port = 3001;
}

package wang.ismy.rpc.client;

import lombok.Data;
import wamg.ismy.rpc.codec.Decoder;
import wamg.ismy.rpc.codec.Encoder;
import wamg.ismy.rpc.codec.JsonDecoder;
import wamg.ismy.rpc.codec.JsonEncoder;
import wang.ismy.rpc.Peer;
import wang.ismy.rpc.transport.HttpTransportClient;
import wang.ismy.rpc.transport.TransportClient;

import java.util.List;

/**
 * @author MY
 * @date 2020/5/10 13:12
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = List.of(new Peer("localhost",3001));
}

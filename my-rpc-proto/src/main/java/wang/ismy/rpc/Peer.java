package wang.ismy.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 代表网络传输端点
 * @author MY
 * @date 2020/5/9 9:10
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}

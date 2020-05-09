package wamg.ismy.rpc.codec;

/**
 * @author MY
 * @date 2020/5/9 9:35
 */
public interface Encoder {
    /**
     * 序列化
     * @param obj
     * @return
     */
    byte[] encode(Object obj);
}

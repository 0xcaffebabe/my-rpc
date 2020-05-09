package wamg.ismy.rpc.codec;

/**
 * @author MY
 * @date 2020/5/9 9:35
 */
public interface Decoder {
    /**
     * 反序列化
     * @param bytes
     * @param klass
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes,Class<T> klass);
}

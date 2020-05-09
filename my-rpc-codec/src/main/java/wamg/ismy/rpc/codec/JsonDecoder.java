package wamg.ismy.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author MY
 * @date 2020/5/9 9:37
 */
public class JsonDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> klass) {
        return JSON.parseObject(bytes,klass);
    }
}

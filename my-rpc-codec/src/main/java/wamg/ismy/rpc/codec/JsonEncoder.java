package wamg.ismy.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author MY
 * @date 2020/5/9 9:37
 */
public class JsonEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}

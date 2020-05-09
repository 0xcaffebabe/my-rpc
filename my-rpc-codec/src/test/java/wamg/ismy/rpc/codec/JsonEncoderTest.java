package wamg.ismy.rpc.codec;

import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest {
    @Data
    public static class Bean {
        private String name;
        private int age;
    }
    @Test
    public void encode() {
        Encoder encoder = new JsonEncoder();
        Bean bean = new Bean();
        bean.setName("cxk");
        bean.setAge(18);
        assertNotNull(encoder.encode(bean));
    }
}
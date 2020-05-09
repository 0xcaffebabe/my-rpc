package wamg.ismy.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JsonEncoder();
        JsonEncoderTest.Bean bean = new JsonEncoderTest.Bean();
        bean.setName("cxk");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);

        Decoder decoder = new JsonDecoder();
        JsonEncoderTest.Bean bean1= decoder.decode(bytes, JsonEncoderTest.Bean.class);
        assertEquals("cxk",bean1.getName());
        assertEquals(18,bean1.getAge());
    }
}
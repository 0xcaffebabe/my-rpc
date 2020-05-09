package wang.ismy.rpc.common;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectionUtilsTest{

    public static class TestClass {
        public String a(){
            return "a";
        }
    }

    @Test
    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void testGetAllPublicMethod() {
        TestClass testClass = new TestClass();
        Method[] methods = ReflectionUtils.getAllPublicMethod(testClass.getClass());
        assertEquals(1,methods.length);
        assertEquals("a",methods[0].getName());
    }

    @Test
    public void testInvoke() {
        TestClass testClass = new TestClass();
        var result = ReflectionUtils.invoke(testClass,ReflectionUtils.getAllPublicMethod(TestClass.class)[0]);
        assertEquals("a",result);
    }
}
package wang.ismy.rpc.server;

import org.junit.Before;
import org.junit.Test;
import wang.ismy.rpc.Request;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    public interface TestInterface {
        void hello();
    }
    public static class TestClass implements TestInterface{

        @Override
        public void hello() {
            System.out.println("hello");
        }
    }
    ServiceManager serviceManager;
    @Before
    public void setup(){
        serviceManager = new ServiceManager();
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class,bean);
    }

    @Test
    public void lookup() {

    }
}
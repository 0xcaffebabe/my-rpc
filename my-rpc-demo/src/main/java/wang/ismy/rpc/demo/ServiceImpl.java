package wang.ismy.rpc.demo;

/**
 * @author MY
 * @date 2020/5/10 13:30
 */
public class ServiceImpl implements Service {
    @Override
    public String hello(String name) {
        return name+", hello";
    }

    @Override
    public String word(String name) {
        return name+", world";
    }
}

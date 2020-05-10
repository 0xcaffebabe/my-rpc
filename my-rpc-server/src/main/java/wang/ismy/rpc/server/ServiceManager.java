package wang.ismy.rpc.server;

import lombok.extern.slf4j.Slf4j;
import wang.ismy.rpc.Request;
import wang.ismy.rpc.ServiceDescription;
import wang.ismy.rpc.common.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MY
 * @date 2020/5/10 9:28
 */
@Slf4j
public class ServiceManager {

    private Map<ServiceDescription,ServiceInstance> serviceMap = new ConcurrentHashMap<>();

    public <T> void register(Class<T> interfaceClass,T bean){
        Method[] methods = ReflectionUtils.getAllPublicMethod(interfaceClass);
        for (Method method : methods) {
            ServiceInstance instance = new ServiceInstance(bean,method);
            ServiceDescription description = ServiceDescription.from(interfaceClass,method);
            serviceMap.put(description,instance);
            log.info("注册服务：{} {}",description.getClassName(),description.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescription description = request.getService();
        return serviceMap.get(description);
    }
}

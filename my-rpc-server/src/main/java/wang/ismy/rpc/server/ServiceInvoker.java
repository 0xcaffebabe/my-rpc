package wang.ismy.rpc.server;

import wang.ismy.rpc.Request;
import wang.ismy.rpc.common.ReflectionUtils;

/**
 * @author MY
 * @date 2020/5/10 9:48
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(serviceInstance.getTarget(),serviceInstance.getMethod(),request.getParameters());
    }
}

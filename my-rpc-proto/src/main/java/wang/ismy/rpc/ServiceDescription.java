package wang.ismy.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 代表服务
 * @author MY
 * @date 2020/5/9 9:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescription {
    private String className;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescription from(Class<?> klass, Method method){
        ServiceDescription description = new ServiceDescription();
        description.className = klass.getName();
        description.setMethod(method.getName());
        description.setReturnType(method.getReturnType().getName());
        Class<?>[] parameterTypes = method.getParameterTypes();
        description.parameterTypes = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            description.parameterTypes[i]=parameterTypes[i].getName();
        }
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescription that = (ServiceDescription) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(method, that.method) &&
                Objects.equals(returnType, that.returnType) &&
                Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(className, method, returnType);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}

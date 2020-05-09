package wang.ismy.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

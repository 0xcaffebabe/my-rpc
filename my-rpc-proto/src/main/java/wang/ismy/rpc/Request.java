package wang.ismy.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * rpc的请求
 * @author MY
 * @date 2020/5/9 9:13
 */
@Data
@AllArgsConstructor
public class Request {
    private ServiceDescription service;
    private Object[] parameters;
}

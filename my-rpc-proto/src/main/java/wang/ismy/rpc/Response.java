package wang.ismy.rpc;

import lombok.Data;

/**
 * rpc响应
 * @author MY
 * @date 2020/5/9 9:14
 */
@Data
public class Response {
    private int code = 0;
    private String msg = "ok";
    private Object data;
}

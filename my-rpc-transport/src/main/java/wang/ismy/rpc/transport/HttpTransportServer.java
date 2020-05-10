package wang.ismy.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author MY
 * @date 2020/5/10 9:12
 */
@Slf4j
public class HttpTransportServer implements TransportServer {
    private RequestHandler requestHandler;
    private Server server;
    @Override
    public void init(int port, RequestHandler handler) {
        this.requestHandler = handler;
        server = new Server(port);
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
        ServletHolder holder = new ServletHolder();
        holder.setServlet(new RequestServlet());
        ctx.addServlet(holder,"/*");
    }

    @Override
    public void listen() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error("启动服务器发生错误",e);
        }
    }

    @Override
    public void close() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error("服务器停止发生错误",e);
        }
    }

    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if (requestHandler != null){
                requestHandler.onRequest(in,out);
            }
            out.flush();
        }
    }
}

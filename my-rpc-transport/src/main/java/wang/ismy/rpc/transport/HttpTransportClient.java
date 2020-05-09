package wang.ismy.rpc.transport;

import org.apache.commons.io.IOUtils;
import wang.ismy.rpc.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author MY
 * @date 2020/5/9 9:48
 */
public class HttpTransportClient implements TransportClient{
    private String url;
    @Override
    public void connect(Peer peer) {
        this.url = "http://"+peer.getHost()+":"+peer.getPort();

    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("POST");
            IOUtils.copy(data,urlConnection.getOutputStream());
            int resultCode = urlConnection.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK){
                return urlConnection.getInputStream();
            }else {
                return urlConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}

package jiyun.com.yiyanmeng.http;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import base.BaseApp;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static jiyun.com.yiyanmeng.http.HttpUtil.isNetworkAvailable;

/**
 * Created by Windows on 2019/11/4.
 */

public class HttpManager {
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private static volatile HttpManager manager;

    public HttpManager() {
    }

    public static HttpManager getInstance() {
        if (manager == null) {
            synchronized (HttpManager.class) {
                if (manager == null) {
                    manager = new HttpManager();
                }
            }
        }
        return manager;
    }

    private OkHttpClient getOkClient(Interceptor... interceptors) {

        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        File mycache = new File(BaseApp.context.getCacheDir(), "mycache");
        Cache cache = new Cache(mycache, 1024 * 1024 * 20);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                 .sslSocketFactory(new TLSSocketFactory())
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(new LogInterceptor());
        if (interceptors != null && interceptors.length > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        okHttpClient = builder.build();


        return okHttpClient;


    }


    public class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
            if (!isNetworkAvailable(BaseApp.context)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (isNetworkAvailable(BaseApp.context)) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }

    public class LogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //拦截链对象
            Request request = chain.request();
            //Object...args ,...是可变参数,可以理解成数组
            //String.format()第一个参数是格式,后面的参数是替代参数,需要将里面的%s的位置使用
            //后面的参数给替代掉
            //"发送请求地址:"+request.url()+"%n请求头:"+request.header();
            Log.d("tag", String.format("发送请求地址:%s%n请求头:%s", request.url(),
                    request.headers()));
            long startTime = System.currentTimeMillis();
            //递归+循环的方式把所有的拦截器串联起来,并获取响应结果
            Response response = chain.proceed(request);
            long endTime = System.currentTimeMillis();

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Log.d("tag", String.format("耗时:%s%n收到来自:%s的结果:%n%s",
                    (endTime - startTime) + "ms", response.request().url(), responseBody.string()));

            return response;

        }
    }

    private Retrofit createRetrofit(String baseUrl, Interceptor... interceptors) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(getOkClient(interceptors))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
        retrofit = builder.build();

        return retrofit;

    }



    public <T> T getSerivce(String baseUrl, Class<T> tClass, Interceptor... interceptors) {
        T t = createRetrofit(baseUrl, interceptors).create(tClass);
        return t;

    }

    public MySerivce getSerivce(Interceptor... interceptors) {
        MySerivce serivce = getSerivce(MySerivce.base_URL, MySerivce.class, interceptors);
        return serivce;

    }


    /**
     * 证书问题报错解决使用的工具类
     *
     * OkHttpClient.Builder builder = new OkHttpClient.Builder()
     * //添加配置
     .sslSocketFactory(new TLSSocketFactory())
     *
     *
     *
     *
     */

    public class TLSSocketFactory extends SSLSocketFactory {



        private SSLSocketFactory delegate;

        public TLSSocketFactory() {
            try {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, null, null);
                delegate = context.getSocketFactory();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return delegate.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return delegate.getSupportedCipherSuites();
        }

        @Override
        public Socket createSocket() throws IOException {
            return enableTLSOnSocket(delegate.createSocket());
        }

        @Override
        public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose));
        }

        @Override
        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
            return enableTLSOnSocket(delegate.createSocket(host, port));
        }

        @Override
        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
            return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort));
        }

        @Override
        public Socket createSocket(InetAddress host, int port) throws IOException {
            return enableTLSOnSocket(delegate.createSocket(host, port));
        }

        @Override
        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
            return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
        }

        private Socket enableTLSOnSocket(Socket socket) {
            if(socket != null && (socket instanceof SSLSocket)) {
                ((SSLSocket)socket).setEnabledProtocols(new String[] {"TLSv1.1", "TLSv1.2"});
            }
            return socket;
        }

    }
}

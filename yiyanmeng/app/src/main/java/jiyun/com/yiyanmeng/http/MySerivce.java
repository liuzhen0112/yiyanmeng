package jiyun.com.yiyanmeng.http;


import bean.LoginBean;
import bean.ResultBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Windows on 2019/11/4.
 */

public interface MySerivce {
    String base_URL="https://app.yiyanmeng.com/index.php/";
    @FormUrlEncoded
    @POST("login/login")
    Observable<ResultBean<LoginBean>> login(@Field("name") String name,@Field("pass") String pass);

}

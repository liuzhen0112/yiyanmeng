package bean;

/**
 * Created by Windows on 2019/11/4.
 */

public class LoginBean {
    @Override
    public String toString() {
        return "LoginBean{" +
                "token=" + token +
                ", user_info=" + user_info +
                '}';
    }

    /**
     * token : {"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIyMTI5NiIsInR5cGUiOiJQb3N0bWFuUnVudGltZVwvNy4xNS4yIiwidGltZSI6MTYzMzMzNDg3MX0.iHEGewKqBWZeG3pB_9xjmHWHJ5iqgNr52jcVfsNF7uA","expires":"2021-10-04 16:07:51","refreshTokenExpires":"2020-08-30 16:07:51","refresh_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIyMTI5NiIsInR5cGUiOiJQb3N0bWFuUnVudGltZVwvNy4xNS4yIiwidGltZSI6MTU5ODc3NDg3MX0.jRTAq4atqFBYRaaduGIieJ7-D6-4LHviJHSjjYKhWXQ"}
     * user_info : {"id":"21296","pass":"e10adc3949ba59abbe56e057f20f883e","u_name":"YYM_5baAWWmzk","u_pic":"https://app.yiyanmeng.com:443/Public/user/2019-11-01/21296/15725884160.png","phnoe":"15735831024","sex":null,"yu_bao_ming":"{\"s_id\":\"12\",\"y_id\":\"7\",\"z_id\":\"41\",\"time\":\"2019-12-20\"}","xinxi":"1"}
     */

    private TokenBean token;
    private UserInfoBean user_info;

    public TokenBean getToken() {
        return token;
    }

    public void setToken(TokenBean token) {
        this.token = token;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class TokenBean {
        @Override
        public String toString() {
            return "TokenBean{" +
                    "access_token='" + access_token + '\'' +
                    ", expires='" + expires + '\'' +
                    ", refreshTokenExpires='" + refreshTokenExpires + '\'' +
                    ", refresh_token='" + refresh_token + '\'' +
                    '}';
        }

        /**
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIyMTI5NiIsInR5cGUiOiJQb3N0bWFuUnVudGltZVwvNy4xNS4yIiwidGltZSI6MTYzMzMzNDg3MX0.iHEGewKqBWZeG3pB_9xjmHWHJ5iqgNr52jcVfsNF7uA
         * expires : 2021-10-04 16:07:51
         * refreshTokenExpires : 2020-08-30 16:07:51
         * refresh_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIyMTI5NiIsInR5cGUiOiJQb3N0bWFuUnVudGltZVwvNy4xNS4yIiwidGltZSI6MTU5ODc3NDg3MX0.jRTAq4atqFBYRaaduGIieJ7-D6-4LHviJHSjjYKhWXQ
         */

        private String access_token;
        private String expires;
        private String refreshTokenExpires;
        private String refresh_token;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getExpires() {
            return expires;
        }

        public void setExpires(String expires) {
            this.expires = expires;
        }

        public String getRefreshTokenExpires() {
            return refreshTokenExpires;
        }

        public void setRefreshTokenExpires(String refreshTokenExpires) {
            this.refreshTokenExpires = refreshTokenExpires;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }

    public static class UserInfoBean {
        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "id='" + id + '\'' +
                    ", pass='" + pass + '\'' +
                    ", u_name='" + u_name + '\'' +
                    ", u_pic='" + u_pic + '\'' +
                    ", phnoe='" + phnoe + '\'' +
                    ", sex=" + sex +
                    ", yu_bao_ming='" + yu_bao_ming + '\'' +
                    ", xinxi='" + xinxi + '\'' +
                    '}';
        }

        /**
         * id : 21296
         * pass : e10adc3949ba59abbe56e057f20f883e
         * u_name : YYM_5baAWWmzk
         * u_pic : https://app.yiyanmeng.com:443/Public/user/2019-11-01/21296/15725884160.png
         * phnoe : 15735831024
         * sex : null
         * yu_bao_ming : {"s_id":"12","y_id":"7","z_id":"41","time":"2019-12-20"}
         * xinxi : 1
         */

        private String id;
        private String pass;
        private String u_name;
        private String u_pic;
        private String phnoe;
        private Object sex;
        private String yu_bao_ming;
        private String xinxi;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getU_pic() {
            return u_pic;
        }

        public void setU_pic(String u_pic) {
            this.u_pic = u_pic;
        }

        public String getPhnoe() {
            return phnoe;
        }

        public void setPhnoe(String phnoe) {
            this.phnoe = phnoe;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public String getYu_bao_ming() {
            return yu_bao_ming;
        }

        public void setYu_bao_ming(String yu_bao_ming) {
            this.yu_bao_ming = yu_bao_ming;
        }

        public String getXinxi() {
            return xinxi;
        }

        public void setXinxi(String xinxi) {
            this.xinxi = xinxi;
        }
    }
}

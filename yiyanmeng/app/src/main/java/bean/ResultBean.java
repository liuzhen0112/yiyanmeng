package bean;

/**
 * Created by Windows on 2019/11/4.
 */

public class ResultBean<T> {


    private T info;
    private String ret;
    private String mas;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMas() {
        return mas;
    }

    public void setMas(String mas) {
        this.mas = mas;
    }
}

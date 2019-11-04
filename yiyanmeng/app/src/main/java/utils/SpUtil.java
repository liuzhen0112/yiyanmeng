package utils;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Windows on 2019/11/1.
 */

public class SpUtil {

    /**
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * 根据存入值的数据类型保存到sp文件中
     */
    public static void put(Context context,String key,Object value){
        SharedPreferences mysp = context.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mysp.edit();

        if (value instanceof String){
            edit.putString(key, (String) value);
        }else if (value instanceof Integer){
            edit.putInt(key, (Integer) value);
        }else if (value instanceof Boolean){
            edit.putBoolean(key, (Boolean) value);
        }else if (value instanceof Float){
            edit.putFloat(key, (Float) value);
        }else if (value instanceof Long){
            edit.putLong(key, (Long) value);
        }else if (value instanceof Set){
            edit.putStringSet(key, (Set<String>) value);
        }
        edit.apply();

    }


    /**
     *
     * @param context   上下文
     * @param key       键
     * @param defaultValue    默认值
     * @param <T>       返回值
     * @return
     *
     * 根据默认值的类型直接获取对应默认值的数据类型的 值
     */
    public static <T>T get(Context context,String key,T defaultValue){
        T value=null;
        SharedPreferences mysp = context.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        if (defaultValue instanceof Integer){
            Integer anInt = mysp.getInt(key, (Integer) defaultValue);
            value=(T)anInt;
        }else if (defaultValue instanceof String){
            String string = mysp.getString(key, (String) defaultValue);
            value=(T)string;
        }else if (defaultValue instanceof Boolean){
            Boolean aBoolean = mysp.getBoolean(key, (Boolean) defaultValue);
            value=(T)aBoolean;
        }else if (defaultValue instanceof Long){
            Long aLong = mysp.getLong(key, (Long) defaultValue);
            value=(T)aLong;
        }else if (defaultValue instanceof Float){
            Float aFloat = mysp.getFloat(key, (Float) defaultValue);
            value=(T)aFloat;
        }else if (defaultValue instanceof Set){
            Set<String> stringSet = mysp.getStringSet(key, (Set<String>) defaultValue);
            value=(T)stringSet;
        }

        return value;


    }




}

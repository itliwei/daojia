package io.github.itliwei.daojia.tenant.config;

/**
 * @author : liwei
 * @date : 2021/03/06 08:43
 * @description : 获取线程
 */
public class ContextUtil {
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<String>();

    public static void setTenant(String  tenant){
        LOCAL.set(tenant);
    }

    public static String getTenant(){
        return LOCAL.get();
    }


}

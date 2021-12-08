package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * java 写法
 */
public class DelegateJavaFactory {

    private static DelegateJavaFactory factory;

    public static DelegateJavaFactory instance(){
        if (factory==null){
            factory = new DelegateJavaFactory();
        }
        return factory;
    }

    public  IDelegate  createClass(IDelegate delegate){
        return (IDelegate) Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                if (methodName.equals("handlePage")){
                    Boolean isIntercept = DelegateParamsUtil.INSTANCE.doIntercept((String)args[0],(String) args[1]);
                    String p = DelegateParamsUtil.INSTANCE.addCommonParams((String)args[0],(String) args[1]);
                    String[] newArgs = {(String) args[0],p};
                    if (!isIntercept)
                    return method.invoke(delegate,newArgs);
                }
                return null;
            }
        });
    }
}

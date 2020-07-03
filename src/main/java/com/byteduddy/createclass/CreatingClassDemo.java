package com.byteduddy.createclass;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class CreatingClassDemo {


    public Object createJavaClass() throws  Exception{
        DynamicType.Unloaded unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello World ByteBuddy!"))
                .make();

        Class<?> dynamicType = unloadedType.load(getClass()
                .getClassLoader())
                .getLoaded();
       return  dynamicType.newInstance();
    }


    public static void main(String[] args) throws Exception {
        CreatingClassDemo creatingClassDemo=new CreatingClassDemo();
        Object obj= creatingClassDemo.createJavaClass();
        System.out.println(obj.toString());
    }

}

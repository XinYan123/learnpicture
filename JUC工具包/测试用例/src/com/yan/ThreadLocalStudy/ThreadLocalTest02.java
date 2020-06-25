package com.yan.ThreadLocalStudy;

import java.sql.SQLOutput;

/**
 * 场景二测试用例：共享变量被多个方法调用，导致频繁传递参数
 */

public class ThreadLocalTest02 {
    public static void main(String[] args) {
        new Service1().process();
    }
}



class Service1 {
    //第一个获取到参数的，调用set()方法尽心传进user对象，然后后续Service2、3的调用直接用这个传进去的user
    public void process() {
        User user = new User("xxx");
        //因为holder是一个静态的对象，可以被类直接调用
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2{

    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("s2"+user.name);
        new Service3().process();
    }
}

class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("s3"+user.name);
        //调用remove方法，底层将value设置为null，让GC可以回收ThreadLocalMap中的ThreadLocal对象，防止内存泄漏导致的OOM
        UserContextHolder.holder.remove();
    }
}

//这个带static的对象不能放到内部类里面去，因为内部类是后面才加载的，static是一开始就要加载的
class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}

package com.blcoder.competitionmanager.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置ID值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取ID值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}

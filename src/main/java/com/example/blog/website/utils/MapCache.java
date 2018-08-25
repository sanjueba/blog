package com.example.blog.website.utils;

import sun.misc.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map缓存实现
 * create by liangyongsen
 */
public class MapCache {

    /**
     * 默认存储1024个缓存
     */
    private static final int DEFAULT_CACHES = 1024;
    public MapCache() {
        this(DEFAULT_CACHES);
    }
    public MapCache(int cacheCount) {
        cachePool = new ConcurrentHashMap<>(cacheCount);
    }
    private static final MapCache INS = new MapCache();
    public static MapCache single() {
        return INS;
    }

    /**
     * 缓存容器
     */
    private Map<String, CacheObject> cachePool;

    /**
     * 读取一个缓存
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key){
        CacheObject cacheObject = cachePool.get(key);
        if(null != cacheObject) {
            long cur = System.currentTimeMillis() / 1000;
            //未过期直接返回
            if (cacheObject.getExpired() <= 0 || cacheObject.getExpired() > cur) {
                Object result = cacheObject.getValue();
                return (T) result;
            }

        }
        return null;
    }

    /**
     * 读取一个hash类型的缓存
     * @param key
     * @param field
     * @param <T>
     * @return
     */
    public <T> T hget(String key,String field){
        key = key + ":"+field;
        return this.get(key);
    }

    /**
     * 设置一个缓存
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        this.set(key,value,-1);
    }

    /**
     * 设置一个带过期时间的缓存
     * @param key
     * @param value
     * @param expired
     */
    public void set(String key,Object value,long expired){
        expired = expired > 0 ? System.currentTimeMillis()/1000 + expired : expired;
        //cachePool大于800时，强制清空缓存池，这个操作有些粗暴会导致误删问题，后期考虑用redis替代MapCache优化
        if(cachePool.size()>800){
            cachePool.clear();
        }
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key,cacheObject);

    }

    /**
     * 设置一个hash缓存
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key,String field,Object value){
        this.hset(key,field,value,-1);
    }

    /**
     * 设置一个hash缓存并带过期时间
     * @param key
     * @param field
     * @param value
     * @param expired
     */
    public void hset(String key,String field,Object value,long expired){
        key = key + ":" +field;
        expired = expired > 0 ? System.currentTimeMillis()/1000 + expired : expired;
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key,cacheObject);
    }

    /**
     * 根据key删除缓存
     */
    public void del(String key){
        cachePool.remove(key);
    }

    /**
     * 根据key和field删除缓存
     * @param key
     * @param field
     */
    public void hdel(String key,String field){
        key = key + ":" + field;
        this.del(key);
    }

    /**
     * 清空缓存
     */
    public void clean(){
        cachePool.clear();
    }

    static class CacheObject {
        private String key;
        private Object value;
        private long expired;

        public CacheObject(String key, Object value, long expired) {
            this.key = key;
            this.value = value;
            this.expired = expired;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public long getExpired() {
            return expired;
        }

    }
}

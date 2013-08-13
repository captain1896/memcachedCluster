package com.lucas.study.memcachesStudys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;


public class SimpleMemCache {

    // create client
    public static MemCachedClient client = new MemCachedClient();
    public static SimpleMemCache memCached = new SimpleMemCache();

    // initialize
    static {
        String[] addr = {"127.0.0.1:11211" };
        Integer[] weights = {3 };
        SockIOPool pool = SockIOPool.getInstance();

        pool.setServers(addr);
        pool.setWeights(weights);

        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 30 * 30);

        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(30);
        pool.setSocketConnectTO(0);

        pool.initialize();

    }

    protected SimpleMemCache() {

    }

    public static SimpleMemCache getInstance() {
        return memCached;
    }

    public boolean set(String key , Object value) {
        return client.set(key, value);
    }

    public boolean add(String key , Object value , Date expiry) {
        return client.add(key, value, expiry);
    }

    public boolean add(String key , Object value) {
        return client.add(key, value);
    }

    public boolean replace(String key , Object value) {
        return client.replace(key, value);
    }

    public boolean replace(String key , Object value , Date expiry) {
        return client.replace(key, value, expiry);
    }

    public boolean delete(String key) {
        return client.delete(key);
    }

    public Object get(String key) {
        return client.get(key);
    }

    public Object gets(String key) {
        return client.gets(key);
    }

    public boolean flushAll() {
        return client.flushAll();
    }

    public static void main(String[] args) {
        System.out.println("----before flush -----");

        SimpleMemCache cache = SimpleMemCache.getInstance();
        cache.flushAll();
        String key1 = "zf";
        cache.set(key1, 18);
        cache.replace(key1, 20);
        String key2 = "zf2";
        cache.set(key2, 19);
        System.out.println("zf get value:" + cache.get(key1));
        System.out.println("zf get value:" + cache.get(key2));
        // cache.flushAll();
        System.out.println("----after flush -----");
        /*
         * System.out.println("zf get value:" + cache.get(key1));
         * System.out.println("zf get value:" + cache.get(key2));
         */
        /*System.out.println("zf get value:" + cache.get(key1));
        System.out.println("zf get value:" + cache.get(key2));
        for (int i = 100; i < 200; i++) {
            cache.set("key" + i, i);
        }
        for (int i = 100; i < 200; i++) {
            System.out.println(cache.get("key" + i));
        }*/
        
        List<String> var = new ArrayList<String>();
        var.add("ENGLAND");
        var.add("SCOTLAND");
        var.add("REAL MADRID");
        var.add("LIVERPOOL");
        var.add("AC MILAN");
        String euroFootballTeam = "euroFootballTeam";
        cache.set(euroFootballTeam, var);
        System.out.println(cache.get(euroFootballTeam));
    }

}

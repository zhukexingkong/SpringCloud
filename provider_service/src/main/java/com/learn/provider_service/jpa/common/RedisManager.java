package com.learn.provider_service.jpa.common;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 */
@Configuration
@ConfigurationProperties(value="jedis.pool")
@Data
public class RedisManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    volatile static RedisManager redisSingleton;

    private String host;
    private int port;
    private int expire;
    private int timeout;
    private String password = "";
    private static JedisPool jedisPool = null;
    //第几个仓库
    private String database;

    public String getDatabase() {
        if(null == database || "".equals(database)) return "0";
        return database;
    }


    public void setDatabase(String database) {
        this.database = database;
    }

    public RedisManager() {


    }



    public static RedisManager getRedisSingleton() {
        if(redisSingleton == null) {
            synchronized (RedisManager.class) {
                if(redisSingleton == null) return new RedisManager();
            }
        }
        return redisSingleton;
    }


    /**
     * 初始化方法
     */
    public void init() {
        if (jedisPool == null) {
            if (password != null && !"".equals(password)) {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
            } else if (timeout != 0) {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
            } else {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
            }


        }
    }




    /**
     * 给Redis中Set集合中某个key值设值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(Integer.parseInt(getDatabase()));
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("Jedis set 异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 从Redis中Set集合中获取key对应value值
     *
     * @param key
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(Integer.parseInt(getDatabase()));
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("Jedis get 异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }






    /**
     * 给Redis中Set集合中某个key值设值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value, long time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(Integer.parseInt(getDatabase()));
            jedis.set(key, value);
            jedis.set(key, value, "XX", "EX", time);
        } catch (Exception e) {
            logger.error("Jedis set 异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }



    /**
     *
     * @Title: expire
     * @Description: 设置指定key的生存时间
     * @return Long    成功 返回 1
     * @throws
     */
    public Long expire(String key,int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(Integer.parseInt(getDatabase()));
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error("Jedis expire 异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }


    }
    /**
     *
     * @Title: exist
     * @Description: 判断key是否存在
     * @return Boolean    返回类型
     * @throws
     */
    public Boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(Integer.parseInt(getDatabase()));
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("Jedis exist 异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }


    }

    /**
     * key的剩余生存时间
     * @param s
     * @return
     */
    public Long ttl(String s) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(Integer.parseInt(getDatabase()));
            return jedis.ttl(s);
        } catch (Exception e) {
            logger.error("Jedis ttl 异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

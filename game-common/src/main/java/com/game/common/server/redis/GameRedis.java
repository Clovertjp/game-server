package com.game.common.server.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;

import io.netty.util.internal.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;

/**
 * @author tangjp
 *
 */
public class GameRedis {
	
	private IGameRedisPool redisPool;
	
	public GameRedis(IGameRedisPool redisPool){
		this.redisPool=redisPool;
	}
	
	private Jedis getClient(){
		return redisPool.getJedisClient();
	}
	
	public String set(String key, String field) {
        Jedis jedis = getClient();
        try {
            return jedis.set(key, field);
        } finally {
            jedis.close();
        }
    }

    public String set(byte[] key, byte[] field) {
        Jedis jedis = getClient();
        try {
            return jedis.set(key, field);
        } finally {
            jedis.close();
        }
    }

    public long hsetnx(String key, String field, String value) {
        Jedis jedis = getClient();
        try {
            return jedis.hsetnx(key, field, value);
        } finally {
            jedis.close();
        }
    }

    public long setnx(String key,String value){
        Jedis jedis = getClient();
        try {
            return jedis.setnx(key, value);
        } finally {
            jedis.close();
        }
    }

    public Set<String> sMember(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.smembers(key);
        } finally {
            jedis.close();
        }
    }

    public long sRem(String key, String... value) {
        Jedis jedis = getClient();
        try {
            return jedis.srem(key, value);
        } finally {
            jedis.close();
        }
    }

    public boolean sIsMember(String key, String member) {
        Jedis jedis = getClient();
        try {
            return jedis.sismember(key, member);
        } finally {
            jedis.close();
        }
    }

    public long sAdd(String key, String... value) {
        Jedis jedis = getClient();
        try {
            return jedis.sadd(key, value);
        } finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = getClient();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public void del(String key) {
        Jedis jedis = getClient();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public long lPush(byte[] key, byte[] value) {
        Jedis jedis = getClient();
        try {
            return jedis.lpush(key, value);
        } finally {
            jedis.close();
        }
    }

    public long lPush(String key, String value) {
        Jedis jedis = getClient();
        try {
            return jedis.lpush(key, value);
        } finally {
            jedis.close();
        }
    }

    public List<String> lRange(String key, long start, long end) {
        Jedis jedis = getClient();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    public Long llen(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.llen(key);
        } finally {
            jedis.close();
        }
    }

    public String rPop(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.rpop(key);
        } finally {
            jedis.close();
        }
    }

    public String lPop(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.lpop(key);
        } finally {
            jedis.close();
        }
    }

    public String rPoplPush(String skey, String dkey) {
        Jedis jedis = getClient();
        try {
            return jedis.rpoplpush(skey, dkey);
        } finally {
            jedis.close();
        }
    }

    public void lRem(byte[] key, int count, byte[] field) {
        Jedis jedis = getClient();
        try {
            jedis.lrem(key, count, field);
        } finally {
            jedis.close();
        }
    }

    public String lIndex(String key, long count) {
        Jedis jedis = getClient();
        try {
            return jedis.lindex(key, count);
        } finally {
            jedis.close();
        }
    }

    public long rPush(String key, String value) {
        Jedis jedis = getClient();
        try {
            return jedis.rpush(key, value);
        } finally {
            jedis.close();
        }
    }

    public String get(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public Map<String, String> mget(Collection<String> keys) {
        return mget(keys, true, "", false);
    }

    public Map<String, String> mget(Collection<String> keys, String keyPrefix) {
        return mget(keys, true, keyPrefix, false);
    }

    /**
     * @param keys
     * @param skipNull
     * @param keyPrefix
     * @param keepPrefix
     * @return
     */
    public Map<String, String> mget(Collection<String> keys, boolean skipNull, String keyPrefix, boolean keepPrefix) {
        if (keys == null || keys.size() == 0) {
            return Collections.EMPTY_MAP;
        }
        String[] keyArr = new String[keys.size()];
        keys.toArray(keyArr);
        return mget(keyArr, skipNull, keyPrefix, keepPrefix);
    }

    public Map<String, String> mget(String... keys) {
        return mget(keys, true, "", true);
    }

    /**
     * 批量获取keys的值，返回一个map
     *
     * @param keys       需要获取的key数组
     * @param skipNull   是否忽略掉null值的key
     * @param keyPrefix  所有key需要增加的prefix
     * @param keepPrefix 返回的map中，key是否包含prefix的值
     * @return
     */
    public Map<String, String> mget(String[] keys, boolean skipNull, String keyPrefix, boolean keepPrefix) {
        if (keys == null || keys.length == 0) {
            return Collections.EMPTY_MAP;
        }
        String[] keysWithPrefix = getKeysWithPrefix(keys, keyPrefix);
        return multiGet(keys, skipNull, keysWithPrefix, keepPrefix);
    }

    private Map<String, String> multiGet(String[] keys, boolean skipNull, String[] keysWithPrefix, boolean keepPrefix) {
        Jedis jedis = getClient();
        try {
            Map<String, String> ret;
            List<String> value = jedis.mget(keysWithPrefix);
            ret = new HashMap<>(value.size());
            String val;
            for (int i = 0; i < keys.length; ++i) {
                val = value.get(i);
                if (skipNull && val == null) {
                    continue;
                }
                if (keepPrefix) {
                    ret.put(keysWithPrefix[i], val);
                } else {
                    ret.put(keys[i], val);
                }
            }
            return ret;
        } finally {
            jedis.close();
        }
    }

    private String[] getKeysWithPrefix(String[] keys, String keyPrefix) {
        String[] prefKeys;
        // 这里用blank判断，是要求prefix不能是空格
        if (!StringUtil.isNullOrEmpty(keyPrefix)) {
            prefKeys = new String[keys.length];
            for (int i = 0; i < keys.length; ++i) {
                prefKeys[i] = keyPrefix + keys[i];
            }
        } else {
            prefKeys = keys;
        }
        return prefKeys;
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getClient();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public long incrBy(String key, long incValue) {
        Jedis jedis = getClient();
        try {
            return jedis.incrBy(key, incValue);
        } finally {
            jedis.close();
        }
    }

    public long incr(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.incr(key);
        } finally {
            jedis.close();
        }
    }

    public long decr(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.decr(key);
        } finally {
            jedis.close();
        }
    }

    public byte[] hGet(byte[] key, byte[] field) {
        Jedis jedis = getClient();
        try {
            return jedis.hget(key, field);
        } finally {
            jedis.close();
        }
    }

    public String hGet(String key, String field) {
        Jedis jedis = getClient();
        try {
            return jedis.hget(key, field);
        } finally {
            jedis.close();
        }
    }

    public List<String> hMGet(String key, String... field) {
        Jedis jedis = getClient();
        try {
            return jedis.hmget(key, field);
        } finally {
            jedis.close();
        }
    }

    public List<String> hMGet(String key, List<String> fields) {
        Jedis jedis = getClient();
        try {
            String[] arr = new String[fields.size()];
            int i = 0;
            for (String item : fields) {
                arr[i++] = item;
            }
            return jedis.hmget(key, arr);
        } finally {
            jedis.close();
        }
    }

    public Map<String, String> hMGetAll(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.hgetAll(key);
        } finally {
            jedis.close();
        }
    }

    public void setExpireTime(String key, int seconds) {
        Jedis jedis = getClient();
        try {
            jedis.expire(key, seconds);
        } finally {
            jedis.close();
        }
    }

    public String hMSet(String key, Map<String, String> map) {
        Jedis jedis = getClient();
        try {
            return jedis.hmset(key, map);
        } finally {
            jedis.close();
        }
    }

    public void mset(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        String[] keyValues = new String[map.size() * 2];
        int index = 0;
        for (Map.Entry<String, String> mapEntry : map.entrySet()) {
            keyValues[index++] = mapEntry.getKey();
            keyValues[index++] = mapEntry.getValue();
        }
        Jedis jedis = getClient();
        try {
            jedis.mset(keyValues);
        } finally {
            jedis.close();
        }
    }

    public List<String> hVal(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.hvals(key);
        } finally {
            jedis.close();
        }
    }

    public long hDel(String key, List<String> fields) {
        return hDel(key, fields.toArray(new String[fields.size()]));
    }

    public long hDel(String key, String... fields) {
        Jedis jedis = getClient();
        try {
            return jedis.hdel(key, fields);
        } finally {
            jedis.close();
        }
    }

    public long hSet(byte[] key, byte[] field, byte[] value) {
        Jedis jedis = getClient();
        try {
            return jedis.hset(key, field, value);
        } finally {
            jedis.close();
        }
    }


    public long hSet(String key, String field, String value) {
        Jedis jedis = getClient();
        try {
            return jedis.hset(key, field, value);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> hKeys(byte[] key) {
        Jedis jedis = getClient();
        try {
            return jedis.hkeys(key);
        } finally {
            jedis.close();
        }
    }

    public Set<String> hKeys(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.hkeys(key);
        } finally {
            jedis.close();
        }
    }

    public long hLen(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.hlen(key);
        } finally {
            jedis.close();
        }
    }

    public boolean hExists(String key, String field) {
        Jedis jedis = getClient();
        try {
            return jedis.hexists(key, field);
        } finally {
            jedis.close();
        }
    }

    public long pushListFromLeft(String key, String... members) {
        Jedis jedis = getClient();
        try {
            return jedis.lpush(key, members);
        } finally {
            jedis.close();
        }
    }

    public String lSet(String key, long index, String members) {
        Jedis jedis = getClient();
        try {
            return jedis.lset(key, index, members);
        } finally {
            jedis.close();
        }
    }

    public List<String> getRangeList(String key, int start, int end) {
        Jedis jedis = getClient();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    public long getLength(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.llen(key);
        } finally {
            jedis.close();
        }
    }

    public List<byte[]> getRangeList(byte[] key, int start, int end) {
        Jedis jedis = getClient();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    public long lRem(String key, int count, String value) {
        Jedis jedis = getClient();
        long lremNum = 0;
        try {
            lremNum = jedis.lrem(key, count, value);
        } finally {
            jedis.close();
        }
        return lremNum;
    }

    public void lTrim(String key, int start, int end) {
        Jedis jedis = getClient();
        try {
            jedis.ltrim(key, start, end);
        } finally {
            jedis.close();
        }
    }

    public String deleteListFromRight(String key) {
        Jedis jedis = getClient();
        try {
            return jedis.rpop(key);
        } finally {
            jedis.close();
        }
    }

    public long hincrBy(String key, String field, long num) {
        Jedis jedis = getClient();
        try {
            return jedis.hincrBy(key, field, num);
        } finally {
            jedis.close();
        }
    }

    public double zincrby(String key, String member, double score) {
        Jedis jedis = getClient();
        try {
            return jedis.zincrby(key, score, member);
        } finally {
            jedis.close();
        }
    }

    public long zAdd(String key, String member, long score) {
        Jedis jedis = getClient();
        try {
            return jedis.zadd(key, (double) score, member);
        } finally {
            jedis.close();
        }
    }

    public long zRem(String key,String member)
    {
        Jedis jedis = getClient();
        try {
            return jedis.zrem(key,member);
        } finally {
            jedis.close();
        }
    }

    public long zAdd(String key, Map<String, Double> memberScores) {
        Jedis jedis = getClient();
        try {
            return jedis.zadd(key, memberScores);
        } finally {
            jedis.close();
        }
    }

    public double zincrBy(String key, String member, long score) {
        Jedis jedis = getClient();
        try {
            return jedis.zincrby(key, (double) score, member);
        } finally {
            jedis.close();
        }
    }

    public double zDel(String key, String... member) {
        Jedis jedis = getClient();
        try {
            return jedis.zrem(key, member);
        } finally {
            jedis.close();
        }
    }

    public Optional<Double> zScore(String key, String member) {
        Jedis jedis = getClient();
        try {
            return Optional.fromNullable(jedis.zscore(key, member));
        } finally {
            jedis.close();
        }
    }

    public Optional<Long> zGetRankByAsc(String key, String member) {
        Jedis jedis = getClient();
        try {
            return Optional.fromNullable(jedis.zrank(key, member));
        } finally {
            jedis.close();
        }
    }

    public Optional<Long> zGetRankByDesc(String key, String member) {
        Jedis jedis = getClient();
        try {
            return Optional.fromNullable(jedis.zrevrank(key, member));
        } finally {
            jedis.close();
        }
    }

    public HashMap<String, Double> zRangByAsc(String key, long start, long end) {
        Jedis jedis = getClient();
        try {
            HashMap<String, Double> ret = new HashMap<>();
            Set<Tuple> result = jedis.zrangeWithScores(key, start, end);
            if (result != null && !result.isEmpty()) {
                for (Tuple member : result) {
                    ret.put(member.getElement(), member.getScore());
                }
            }
            return ret;
        } finally {
            jedis.close();
        }
    }

    public Map<String, Double> zrevrangeWithScores(String key, long start, long end) {
        Jedis jedis = getClient();
        try {
            Map<String, Double> ret = new HashMap<>();
            Set<Tuple> result = jedis.zrevrangeWithScores(key, start, end);
            if (result != null && !result.isEmpty()) {
                for (Tuple member : result) {
                    ret.put(member.getElement(), member.getScore());
                }
            }
            return ret;
        } finally {
            jedis.close();
        }
    }

    public List<Map<String,Double>> zRangByDesc(String key, long start, long end) {
        Jedis jedis = getClient();
        try {
            List<Map<String,Double>> retList = new LinkedList<>();
            Set<Tuple> result = jedis.zrevrangeWithScores(key, start, end);
            if (result != null && !result.isEmpty()) {
                for (Tuple member : result) {
                	Map<String,Double> map=new HashMap<>();
                	map.put(member.getElement(), member.getScore());
                    retList.add(map);
                }
            }
            return retList;
        } finally {
            jedis.close();
        }
    }

    public void subscribe(JedisPubSub listener, List<String> channels) {
        Jedis jedis = getClient();
        try {
            jedis.subscribe(listener, channels.toArray(new String[]{}));
        } finally {
            jedis.close();
        }
    }

    public long publish(String channel, String msg) {
        Jedis jedis = getClient();
        try {
            return jedis.publish(channel, msg);
        } finally {
            jedis.close();
        }
    }

    public Object batch(IGameRedisBatch batch){
        Jedis jedis = getClient();
        try {
            return batch.run(jedis);
        } finally {
            jedis.close();
        }
    }

    public void setPExpireTime(String key, long seconds) {
        Jedis jedis = getClient();
        try {
            jedis.expireAt(key, seconds);
        } finally {
            jedis.close();
        }
    }

}

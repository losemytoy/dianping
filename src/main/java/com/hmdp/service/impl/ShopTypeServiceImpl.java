package com.hmdp.service.impl;

import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Result queryType() {

        Set members = redisTemplate.opsForSet().members(CACHE_SHOP_TYPE_KEY);
        if (!members.isEmpty()) {
            return Result.ok(members);
        }
        List<ShopType> typeList = query().orderByAsc("sort").list();
        for (ShopType shopType : typeList) {
            redisTemplate.opsForSet().add(CACHE_SHOP_TYPE_KEY, shopType);
        }
        return Result.ok(typeList);
    }
}

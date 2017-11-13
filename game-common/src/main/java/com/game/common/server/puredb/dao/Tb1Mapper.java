package com.game.common.server.puredb.dao;

import com.game.common.server.puredb.model.Tb1;

public interface Tb1Mapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Tb1 record);

    int insertSelective(Tb1 record);

    Tb1 selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Tb1 record);

    int updateByPrimaryKey(Tb1 record);
}
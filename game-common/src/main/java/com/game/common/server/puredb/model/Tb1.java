package com.game.common.server.puredb.model;

public class Tb1 {
    private Integer uid;

    private Integer val;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return uid+"   "+val;
    }
}
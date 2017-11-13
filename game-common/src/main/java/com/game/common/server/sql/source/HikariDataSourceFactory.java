package com.game.common.server.sql.source;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author tangjp
 *
 */
public class HikariDataSourceFactory extends UnpooledDataSourceFactory {

	public HikariDataSourceFactory() {
        this.dataSource = new HikariDataSource();
    }
	
}

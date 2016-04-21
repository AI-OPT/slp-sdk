package com.ai.slp.sdk.datasource;

import com.ai.slp.sdk.components.utils.ConfigTool;
import com.zaxxer.hikari.HikariDataSource;

public class OptHikariDataSource extends HikariDataSource {

    public OptHikariDataSource(String dataSourceName) {
        super(ConfigTool.getDBConf(dataSourceName));
    }

}

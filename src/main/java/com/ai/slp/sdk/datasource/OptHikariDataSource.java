package com.ai.slp.sdk.datasource;

import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.slp.sdk.components.ccs.CCSFactory;
import com.ai.slp.sdk.constants.SDKConstants;
import com.ai.slp.sdk.exception.SDKException;
import com.ai.slp.sdk.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class OptHikariDataSource extends HikariDataSource {

    public OptHikariDataSource(String dataSourceName) {
        super(getDBConf(dataSourceName));
    }

    private static HikariConfig getDBConf(String dataSourceName) {
        String data;
        try {
            data = CCSFactory.getDefaultConfigClient().get(SDKConstants.DATASOURCES_PATH);
        } catch (ConfigException e) {
            throw new SDKException("get database conf error from path["
                    + SDKConstants.DATASOURCES_PATH + "]", e);
        }
        if (StringUtil.isBlank(data)) {
            throw new SDKException("cann't get database conf from path["
                    + SDKConstants.DATASOURCES_PATH + "]");
        }
        JSONObject dbConfJson = JSONObject.parseObject(data);
        JSONObject confObject = (JSONObject) dbConfJson.get(dataSourceName);
        if (confObject == null) {
            throw new SDKException("cann't get database config info of dataSourceName["
                    + dataSourceName + "]");
        }
        HikariConfig dbconf = JSONObject.toJavaObject(confObject, HikariConfig.class);

        return dbconf;
    }

}

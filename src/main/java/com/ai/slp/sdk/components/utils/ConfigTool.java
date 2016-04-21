package com.ai.slp.sdk.components.utils;

import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.sdk.components.base.ComponentConfigLoader;
import com.ai.slp.sdk.components.ccs.CCSFactory;
import com.ai.slp.sdk.constants.SDKConstants;
import com.ai.slp.sdk.exception.SDKException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaxxer.hikari.HikariConfig;

public final class ConfigTool {

    private ConfigTool() {

    }

    public static final String getServicePwd(String serviceId) {
        try {
            if (StringUtil.isBlank(serviceId)) {
                throw new SDKException("服务ID为空，无法获取服务密码");
            }
            String conf = CCSFactory.getDefaultConfigClient().get(
                    SDKConstants.SERVICE_PWD_MAPPED_PATH);
            if (StringUtil.isBlank(conf)) {
                throw new SDKException("获取的服务标识与密码映射配置为空，请检查默认配置服务中的相关配置");
            }
            JSONObject data = JSON.parseObject(conf);
            String pwd = data.getString(serviceId);
            if (StringUtil.isBlank(pwd)) {
                throw new SDKException("从默认配置服务中无法获取服务[" + serviceId + "]对应的密码");
            }
            return pwd;
        } catch (ConfigException e) {
            throw new SDKException("获取服务标识与密码映射配置错误", e);
        }
    }

    public static final String getCCSId(String cachens) {
        try {
            if (StringUtil.isBlank(cachens)) {
                throw new SDKException("命名空间为空，无法获取缓存服务ID");
            }
            String conf = CCSFactory.getDefaultConfigClient().get(
                    SDKConstants.CACHENS_MCS_MAPPED_PATH);
            if (StringUtil.isBlank(conf)) {
                throw new SDKException("获取不到缓存应用场景对应的CCS服务ID，请检查默认配置服务中的相关配置");
            }
            JSONObject data = JSON.parseObject(conf);
            String ccsId = data.getString(cachens);
            if (StringUtil.isBlank(ccsId)) {
                throw new SDKException("从默认配置服务中无法获取缓存命名空间[" + cachens + "]对应的CCS服务ID");
            }
            return ccsId;
        } catch (ConfigException e) {
            throw new SDKException("获取缓存命名空间对应的服务ID错误", e);
        }
    }

    public static final HikariConfig getSeqDBConf() {
        String ds = ComponentConfigLoader.getInstance().getPaasAuthInfo().getSeqDataSource();
        if (StringUtil.isBlank(ds)) {
            throw new SDKException("没有指定或配置SEQ序列生成的数据服务");
        }
        return getDBConf(ds);
    }

    public static HikariConfig getDBConf(String dataSourceName) {
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

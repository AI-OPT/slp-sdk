package com.ai.slp.sdk.components.ccs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.slp.sdk.components.base.ComponentConfigLoader;
import com.ai.slp.sdk.components.mo.PaasConf;
import com.ai.slp.sdk.exception.SDKException;

/**
 * 获取指定服务ID的配置中心技术组件服务实例<br>
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhangchao
 */
public final class CCSFactory {

    private static final Logger LOG = LogManager.getLogger(CCSFactory.class);

    private CCSFactory() {

    }

    public static IConfigClient getDefaultConfigClient() {
        PaasConf authInfo = ComponentConfigLoader.getInstance().getPaasAuthInfo();
        AuthDescriptor authDescriptor = new AuthDescriptor(authInfo.getAuthUrl(),
                authInfo.getUserName(), authInfo.getCcsPassword(), authInfo.getCcsServiceId());

        IConfigClient client = null;
        try {
            client = ConfigFactory.getConfigClient(authDescriptor);
        } catch (Exception e) {
            LOG.error("get paas config center error", e);
            throw new SDKException(e);
        }
        return client;
    }

    public static IConfigClient getConfigClient(String serviceId, String password) {
        PaasConf authInfo = ComponentConfigLoader.getInstance().getPaasAuthInfo();
        AuthDescriptor authDescriptor = new AuthDescriptor(authInfo.getAuthUrl(),
                authInfo.getUserName(), password, serviceId);

        IConfigClient client = null;
        try {
            client = ConfigFactory.getConfigClient(authDescriptor);
        } catch (Exception e) {
            LOG.error("get paas config center error", e);
            throw new SDKException(e);
        }
        return client;
    }
}

package com.ai.slp.sdk.components.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ai.slp.sdk.components.mo.PaasAuthInfo;
import com.ai.slp.sdk.exception.SDKException;
import com.ai.slp.sdk.util.StringUtil;

/**
 * 平台技术组件配置加载器<br>
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhangchao
 */
public final class ComponentConfigLoader {

    private static ComponentConfigLoader INSTANCE = null;

    private Properties prop;

    private ComponentConfigLoader() {
        // 禁止实例化
    }

    /**
     * 获取配置加载器单例实例，确保多线程并发情况下高效读取，避免INSTANCE引用指向不同的实例对象
     * 
     * @return
     * @author zhangchao
     */
    public static ComponentConfigLoader getInstance() {
        if (INSTANCE == null) {
            // 多线程并发获取实例时候，避免等线程锁造成性能低下，因此在创建实例时候进行同步处理
            synchronized (ComponentConfigLoader.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ComponentConfigLoader();
                    INSTANCE.loadProp();
                }
            }
        }
        return INSTANCE;

    }

    /**
     * 加载配置文件
     * 
     * @author zhangchao
     */
    private void loadProp() {
        InputStream is = ComponentConfigLoader.class.getClassLoader().getResourceAsStream("");
        try {
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            throw new SDKException("loding config file failed", e);
        }
    }

    public PaasAuthInfo getPaasAuthInfo() {
        String authUrl = prop.getProperty("paas.auth.url");
        String user = prop.getProperty("paas.auth.user");
        String ccsServiceId = prop.getProperty("paas.runner.ccs.serviceid");
        String ccsServicePwd = prop.getProperty("paas.runner.ccs.servicepassword");
        if (StringUtil.isBlank(authUrl)) {
            throw new SDKException("paas auth url is null");
        }
        if (StringUtil.isBlank(user)) {
            throw new SDKException("paas auth user is null");
        }
        if (StringUtil.isBlank(ccsServiceId)) {
            throw new SDKException("paas ccs serviceid is null");
        }
        if (StringUtil.isBlank(ccsServicePwd)) {
            throw new SDKException("paas ccs service password is null");
        }
        PaasAuthInfo paasAuthInfo = new PaasAuthInfo();
        paasAuthInfo.setAuthUrl(authUrl);
        paasAuthInfo.setUserName(user);
        paasAuthInfo.setCcsServiceId(ccsServiceId);
        paasAuthInfo.setCcsPassword(ccsServicePwd);
        return paasAuthInfo;
    }

}

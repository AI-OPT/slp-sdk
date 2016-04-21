package com.ai.slp.sdk.constants;

public final class SDKConstants {

    private SDKConstants() {

    }

    // 配置某种场景下用哪个缓存服务ID {"AA":"CCS001"}
    public static final String CACHENS_MCS_MAPPED_PATH = "/com/ai/slp/cachens-mcs-mapped";

    // 技术服务与密码的映射关系 {"TXS001":"password"}
    public static final String SERVICE_PWD_MAPPED_PATH = "/com/ai/slp/service-pwd-mapped";

    // 数据源的配置 {"db1":{},"db2":{}}
    public static final String DATASOURCES_PATH = "/com/ai/slp/datasources";
}

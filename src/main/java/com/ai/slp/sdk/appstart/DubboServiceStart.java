package com.ai.slp.sdk.appstart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class DubboServiceStart {

    private static final Logger LOG = LoggerFactory.getLogger(DubboServiceStart.class.getName());

    private static final String DUBBO_CONTEXT = "classpath:dubbo/provider/dubbo-provider.xml";

    private DubboServiceStart() {
    }

    @SuppressWarnings("resource")
    private static void startDubbo() {
        LOG.error("开始启动 Dubbo 服务---------------------------");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { DUBBO_CONTEXT });
        context.registerShutdownHook();
        context.start();
        LOG.error(" Dubbo 服务启动完毕---------------------------");
        synchronized (DubboServiceStart.class) {
            while (true) {
                try {
                    DubboServiceStart.class.wait();
                } catch (Exception e) {
                    LOG.error("Dubbo 系统错误，具体信息为：" + e.getMessage(), e);
                }
            }
        }
    }

    public static void main(String[] args) {
        startDubbo();
    }
}

package com.ai.slp.sdk.concurrent;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.ai.slp.sdk.components.base.ComponentConfigLoader;

public class ComponentConfigFactoryTest extends AbstractJavaSamplerClient {

    public void setupTest(JavaSamplerContext arg0) {
        System.out.println("测试开始");
    }

    public void teardownTest(JavaSamplerContext arg0) {
        System.out.println("测试结束");
    }

    @Override
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();
        sr.setSampleLabel("组件配置工厂单例并发测试");
        try {
            sr.sampleStart();
            ComponentConfigLoader instance = ComponentConfigLoader.getInstance();
            sr.setResponseData("内存信息：" + instance.toString(), null);
            sr.setDataType(SampleResult.TEXT);
            sr.setSuccessful(true);
        } catch (Throwable e) {
            sr.setSuccessful(false);
            e.printStackTrace();
        } finally {
            sr.sampleEnd();
        }
        return sr;
    }
}

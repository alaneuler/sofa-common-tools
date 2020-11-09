/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.common.thread.construct;

import com.alipay.sofa.common.thread.SofaThreadPoolTaskScheduler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huzijie
 * @version SofaThreadTaskSchedulerConstructsTest.java, v 0.1 2020年11月09日 5:35 下午 huzijie Exp $
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:thread/sofaThreadPoolTaskScheduler.xml" })
public class SofaThreadPoolTaskSchedulerConstructsTest {

    @Autowired
    @Qualifier("testSofaThreadPoolTaskSchedulerA")
    private ThreadPoolTaskScheduler sofaThreadPoolTaskSchedulerA;

    @Autowired
    @Qualifier("testSofaThreadPoolTaskSchedulerB")
    private ThreadPoolTaskScheduler sofaThreadPoolTaskExecutorB;

    @Test
    public void testSofaThreadPoolTaskSchedulerA() {
        Assert.assertTrue(sofaThreadPoolTaskSchedulerA instanceof SofaThreadPoolTaskScheduler);
        Assert.assertEquals(5, sofaThreadPoolTaskSchedulerA.getScheduledThreadPoolExecutor()
            .getCorePoolSize());
        Assert.assertEquals("testThreadSchedulerA-",
            sofaThreadPoolTaskSchedulerA.getThreadNamePrefix());
        Assert.assertEquals("testThreadPoolNameA",
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskSchedulerA).getThreadPoolName());
        Assert.assertEquals("testNamespaceA",
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskSchedulerA).getNamespace());
        Assert.assertEquals(2000,
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskSchedulerA).getTaskTimeout());
        Assert.assertEquals(10000,
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskSchedulerA).getPeriod());
        Assert.assertTrue(sofaThreadPoolTaskSchedulerA.getScheduledThreadPoolExecutor()
            .getRemoveOnCancelPolicy());
    }

    @Test
    public void testSofaThreadPoolTaskSchedulerB() {
        Assert.assertTrue(sofaThreadPoolTaskExecutorB instanceof SofaThreadPoolTaskScheduler);
        Assert.assertEquals(10, sofaThreadPoolTaskExecutorB.getScheduledThreadPoolExecutor()
            .getCorePoolSize());
        Assert.assertEquals("testThreadSchedulerB-",
            sofaThreadPoolTaskExecutorB.getThreadNamePrefix());
        Assert.assertEquals("testThreadPoolNameB",
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskExecutorB).getThreadPoolName());
        Assert.assertEquals("testNamespaceB",
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskExecutorB).getNamespace());
        Assert.assertEquals(3000,
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskExecutorB).getTaskTimeout());
        Assert.assertEquals(5000,
            ((SofaThreadPoolTaskScheduler) sofaThreadPoolTaskExecutorB).getPeriod());
        Assert.assertTrue(sofaThreadPoolTaskExecutorB.getScheduledThreadPoolExecutor()
            .getRemoveOnCancelPolicy());
    }

    @Configuration
    static class SofaThreadPoolTaskSchedulerConstructsTestConfiguration {

        @Bean
        public SofaThreadPoolTaskScheduler testSofaThreadPoolTaskSchedulerB() {
            SofaThreadPoolTaskScheduler sofaThreadPoolTaskScheduler = new SofaThreadPoolTaskScheduler();
            sofaThreadPoolTaskScheduler.setPoolSize(10);
            sofaThreadPoolTaskScheduler.setThreadNamePrefix("testThreadSchedulerB-");
            sofaThreadPoolTaskScheduler.setThreadPoolName("testThreadPoolNameB");
            sofaThreadPoolTaskScheduler.setNamespace("testNamespaceB");
            sofaThreadPoolTaskScheduler.setTaskTimeout(3000);
            sofaThreadPoolTaskScheduler.setPeriod(5000);
            sofaThreadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
            return sofaThreadPoolTaskScheduler;
        }
    }
}

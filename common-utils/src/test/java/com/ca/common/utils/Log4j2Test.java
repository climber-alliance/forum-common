package com.ca.common.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;

/**
 * forum-common
 *
 * <p> log4j2-slf4j 测试类 </p>
 *
 * @author ZHANGZHENG
 * @version Log4j2Test.java 2020/12/20 15:43
 */
@SpringBootTest
class Log4j2Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Log4j2Test.class);

    @Test
    void Test(){
        String str = "Test";
        LOGGER.info("====测试参数：-[{}]=====",str);
    }

}

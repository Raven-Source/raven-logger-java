package org.raven.logger.test;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.raven.logger.Coder;
import org.raven.logger.Extender;
import org.raven.logger.Tagger;
import org.raven.logger.tag.NumberValueTag;
import org.raven.logger.tag.StringValueTag;
import org.raven.logger.tag.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class LoggerTest {

    private Logger logger;

    @PostConstruct
    public void run() {
        setup();
        coderTest();
        extenderTest();
        tagsTest();
    }

    public void setup() {
//        load("logback-spring.xml");

        logger = LoggerFactory.getLogger(LoggerTest.class);
    }

    public void coderTest() {
        logger.debug("Extender test", Coder.of("111"));  //debug日志记录code码111
        logger.info("Extender test", Coder.of("110"));   //info日志记录code码110
        logger.warn("Extender test", Coder.of("119"));

        // 多个参数的时候，一定要把Throwable放在最后一个参数
        logger.error("NullPointer", Coder.of("gg"), new NullPointerException());
        // 自定义的BusException，可以实现Coder接口，会记录到日志code字段中。
        logger.error("", new BusException("bus exception", "111"));
    }

    public void extenderTest() {

        Order order = new Order();
        order.setId(123).setName("汉字汉字");

        //打印扩展字段ext
        logger.debug("Extender test", Extender.of(order));
        logger.info("Extender test", Extender.of(order));
        logger.warn("Extender test", Extender.of(order));
        logger.error("Extender test", Extender.of(order));

        logger.info("Extender test", Extender.map().addExtValue("id", "123").addExtValue("val", 456));
    }

    public void tagsTest() {

        logger.debug("log tags", Tagger.of(Tags.SPING_MVC, Tags.REDIS));
        logger.info("log tags", Tagger.of(Tags.SPING_MVC, Tags.REDIS));
        logger.warn("log tags", Tagger.of(Tags.SPING_MVC, Tags.REDIS));
        //{...,"code":"","message":"log tags","ext":null,"tag":["sping.mvc","redis"]}

        // 多个参数的时候，一定要把Throwable放在最后一个参数
        logger.error("log tags", Coder.of("err-code"),
                Tagger.of(Tags.SPING_MVC, Tags.REDIS),
                new NullPointerException("some error")
        );
        //{...,"code":"err-code","message":"log tags","exception":"java.lang.NullPointerException","tag":["sping.mvc","redis"],"stackTrace":"java.lang.NullPointerException: some error\r\n\tat com.example.demo.controller.HomeController.tags

        logger.warn("log tags", Tagger.of(
                        StringValueTag.of("abc", "123"), NumberValueTag.of("abc", 456)
                )
        );
        //{...,"code":"","msg":"log tags","ex":"-","stack":"","ext":null,"tag":["abc=123","abc=456"]}
    }

    public void load(String fileName) throws Exception {
        ClassLoader classLoader = Coder.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        assert url != null;
        File externalConfigFile = new File(url.getPath());

        if (!externalConfigFile.exists()) {

            throw new IOException("Logback External Config File Parameter does not reference a file that exists");

        } else {

            if (!externalConfigFile.isFile()) {
                throw new IOException("Logback External Config File Parameter exists, but does not reference a file");

            } else {

                if (!externalConfigFile.canRead()) {
                    throw new IOException("Logback External Config File exists and is a file, but cannot be read.");

                } else {

                    JoranConfigurator configurator = (JoranConfigurator) Class.forName("org.springframework.boot.logging.logback.SpringBootJoranConfigurator").newInstance();
                    configurator.setContext(lc);
                    lc.reset();
                    configurator.doConfigure(externalConfigFile);

                    StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
                }

            }

        }

    }

}

package io.github.itliwei.daojia;

import io.github.itliwei.vboot.vorm.generator.Config;
import io.github.itliwei.vboot.vorm.generator.handler.*;

import java.nio.file.Paths;

/**
 * @author homelink
 * @date 2019/9/5 0006
 */
public class Generator {

    public static void main(String[] args) {
        Config config = new Config();
        config.setGenLogFile(Paths.get(System.getProperty("user.home"), "gen.log").toString());
        config.setUrl("jdbc:mysql://localhost:3306/daojia?useSSL=false");
        config.setUsername("root");
        config.setPassword("root");

        config.setEntityPackage("io.github.itliwei.daojia.system.user.entity");

        config.setUseLombok(true);

        config.setVoPackage("io.github.itliwei.daojia.tenant.vo");
        config.setServicePackage("io.github.itliwei.daojia.tenant.service");
        config.setQueryModelPackage("io.github.itliwei.daojia.tenant.query");
        config.setControllerPackage("io.github.itliwei.daojia.api.web.tenant.controller");
        config.setElementPath("vue");
        config.setVoSuffix("VO");
        config.setDtoSuffix("DTO");
        io.github.itliwei.vboot.vorm.generator.Generator.generate(config
//                  , new VoHandler()
//                , new QueryModelHandler()
//                , new ServiceHandler()
//                , new ControllerHandler()
                , new ElementHandler()
//               , new MysqlHandler(true)
        );

    }
}

package com.socks.api;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    @DefaultValue("stage")
    String env();

    @Key("${env}.baseUrl")
    String baseUrl();

    @Key("${env}.locale")
    String locale();

    @Key("${env}.logging")
    boolean logging();
}

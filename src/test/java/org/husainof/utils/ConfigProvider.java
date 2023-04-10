package org.husainof.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    static Config readConfig() {
        return ConfigFactory.load("application.conf");
    }
}
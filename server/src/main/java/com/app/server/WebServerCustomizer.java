package com.app.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Value("${server.ssl.key-store}")
    private String _keyStore;
    @Value("${server.ssl.key-store-password}")
    private String _keyStorePassword;
    @Value("${server.ssl.trust-store}")
    private String _trustStore;
    @Value("${server.ssl.trust-store-password}")
    private String _trustStorePassword;
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setSsl(getSslConfig());
    }
    private Ssl getSslConfig(){
        Ssl ssl = new Ssl();
        ssl.setKeyStore(_keyStore);
        ssl.setKeyStorePassword(_keyStorePassword);
        ssl.setTrustStore(_trustStore);
        ssl.setTrustStorePassword(_trustStorePassword);
        ssl.setClientAuth(Ssl.ClientAuth.WANT);
        return ssl;
    }
}

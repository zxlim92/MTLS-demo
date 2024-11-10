package com.app.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

@Configuration
public class AppData {

    @Value("${server.ssl.key-store}")
    private Resource _keyStore;
    @Value("${server.ssl.key-store-password}")
    private String _keyStorePassword;
    @Value("${server.ssl.trust-store}")
    private Resource _trustStore;
    @Value("${server.ssl.trust-store-password}")
    private String _trustStorePassword;

    private int connectionTimeout=5000;
    private int readTimeout=5000;

    @Bean
    public RestTemplate restTemplate() throws Exception{
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        SSLContext sslContexst = createSSLContext();
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContexst.getSocketFactory());
        return new RestTemplate(factory);
    }

    private SSLContext createSSLContext() throws Exception{
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(_keyStore.getInputStream(),_keyStorePassword.toCharArray());
        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        trustStore.load(_trustStore.getInputStream(),_keyStorePassword.toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore,_keyStorePassword.toCharArray());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),null);

        return sslContext;
    }
}

package com.example.elasticsearchandmongo.elasticpackage.config;

import java.io.File;

import javax.net.ssl.*;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HttpClientConfigImpl implements HttpClientConfigCallback {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientConfigImpl.class);

    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("elasticusernm",
                    "elasticpasswrd");
            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

//            String trustLocationStore = "truststore file path";
//            File trustLocationFile = new File(trustLocationStore);
//
//            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustLocationFile,
//                    "truststorepasswrd".toCharArray());
//            SSLContext sslContext = sslContextBuilder.build();
//            httpClientBuilder.setSSLContext(sslContext);


            SSLContext sslContext = null;
            try{
                sslContext  = SSLContext.getInstance("SSL");
                sslContext.init(null , trustAllCerts , new SecureRandom());
            }
            catch (KeyManagementException | NoSuchAlgorithmException e)
            {
                logger.warn("Exception when initialising elascticsearch client for elasticsearch connection " + e.getMessage());
            }

            SSLIOSessionStrategy sessionStrategy = new SSLIOSessionStrategy(sslContext , (HostnameVerifier) new NullHostnameVerifier());




            httpClientBuilder.setSSLStrategy(sessionStrategy);

        } catch (Exception e) {
        }
        return httpClientBuilder;
    }

    static   TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }
    };

    private static class NullHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}

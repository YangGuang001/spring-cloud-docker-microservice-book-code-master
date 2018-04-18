package com.itmuch.cloud.study;

import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import com.sun.net.ssl.HostnameVerifier;
import com.sun.net.ssl.HttpsURLConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLSession;

/**
 * Created by yz on 2018/2/14.
 */
@Configuration
public class EurekaJerseyClientJerseyBean {

    @Bean
    public EurekaJerseyClient eurekaJerseyClient() {
        //System.setProperty("javax.net.ssl.trustStore","E:\\java\\homework\\spring-cloud-docker-microservice-book-code-master\\microservice-provider-user-security-client\\src\\main\\resources");
        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder = new
                EurekaJerseyClientImpl.EurekaJerseyClientBuilder()
                .withClientName("eureka")
                .withTrustStoreFile("E:\\java\\homework\\spring-cloud-docker-microservice-book-code-master\\microservice-provider-user-security-client\\src\\main\\resources\\server.truststore", "123456");
        return builder.build();
    }

    private void trustAll() {
        HostnameVerifier hv = new MyhostnameVerifier();
        try {
            trustAllHttpsCertificates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private  void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

}

class MyhostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String urlHostName, String session) {
        System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                + session);
        return true;
    }
}

class miTM implements javax.net.ssl.TrustManager,
        javax.net.ssl.X509TrustManager {
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public boolean isServerTrusted(
            java.security.cert.X509Certificate[] certs) {
        return true;
    }

    public boolean isClientTrusted(
            java.security.cert.X509Certificate[] certs) {
        return true;
    }

    public void checkServerTrusted(
            java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
        return;
    }

    public void checkClientTrusted(
            java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
        return;
    }
}

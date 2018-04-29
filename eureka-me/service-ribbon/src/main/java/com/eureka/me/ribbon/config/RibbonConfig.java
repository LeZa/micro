package com.eureka.me.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.X509Certificate;

@Configuration
public class RibbonConfig {


    @Bean
    public IRule ribbonIRule(){
        return new RandomRule();
    }

   /* @Bean
    public IRule ribbonIRule(){
        return new BestAvailableRule();
    }*/

    @Bean
    public HostnameVerifier hostnameVerifier() {
        return new javax.net.ssl.HostnameVerifier() {

            public boolean verify(String hostname,
                                  javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        };
    }


    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(
            HostnameVerifier hostNameVerifier) {
        ClientHttpRequestFactory clientHttpRequestFactory = new ClientHttpsAcceptLoaclahostRequestFactory(
                hostNameVerifier);
        return clientHttpRequestFactory;
    }

}


class ClientHttpsAcceptLoaclahostRequestFactory extends
        SimpleClientHttpRequestFactory {

    private final HostnameVerifier hostNameVerifier;

    @Autowired
    public ClientHttpsAcceptLoaclahostRequestFactory(
            HostnameVerifier hostNameVerifier) {
        this.hostNameVerifier = hostNameVerifier;
    }

    @Override
    protected void prepareConnection(final HttpURLConnection connection,
                                     final String httpMethod) throws IOException {
        if (connection instanceof HttpsURLConnection) {

            ((HttpsURLConnection) connection)
                    .setHostnameVerifier(hostNameVerifier);
            ((HttpsURLConnection) connection)
                    .setSSLSocketFactory(initSSLContext()
                            .getSocketFactory());
        }
        super.prepareConnection(connection, httpMethod);
    }

    private SSLContext initSSLContext() {
        try {
            System.setProperty("https.protocols", "TLSv1");
            SSLContext sc = SSLContext.getInstance("TLSv1");
            sc.init(null, getTrustManager(), null);

            return sc;
        } catch (final Exception ex) {
            return null;
        }
    }

    private TrustManager[] getTrustManager() {
        return new TrustManager[] { new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs,
                    String authType) {
            }

            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs,
                    String authType) {

            }
        } };
    }
}


package ie.teckro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
@ComponentScan("ie.teckro")
@PropertySource("classpath:/${testConfig}.properties")
public class TestConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "ServiceRestTemplate")
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        RestTemplate rest;
        if (environment.getProperty("use.proxy", Boolean.class, false)) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
            requestFactory.setProxy(proxy);
            rest = new RestTemplate(requestFactory);
        } else {
            rest = new RestTemplate();
        }
        return rest;
    }
}

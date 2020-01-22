package app;

import app.bean.Teste;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.cache.CachingHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
    CacheConfig cacheConfig = new CacheConfig();  
    cacheConfig.setMaxCacheEntries(1000);
    cacheConfig.setMaxObjectSize(8192);
    HttpClient cachingClient = new CachingHttpClient(new DefaultHttpClient(), cacheConfig);
    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(cachingClient);
    RestTemplate template = new RestTemplate(requestFactory);
    ResponseEntity<Teste> response = null;

    for (int i=0;i<10;i++) {
      response = template.getForEntity("http://localhost:8080/teste", Teste.class);
      System.out.println("Executou: " + i + " status: " + response.getStatusCode());
    }
	}
}

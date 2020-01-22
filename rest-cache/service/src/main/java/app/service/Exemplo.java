package app.service;

import app.bean.Teste;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class Exemplo {

  public Exemplo() {
    System.out.println("Construtor");
  }

  @GetMapping(path = "/teste")
	public ResponseEntity<Teste> index() {
    CacheControl cache = CacheControl.maxAge(60, TimeUnit.SECONDS).cachePublic();
    Teste teste = new Teste("Flavio");
    System.out.println("Chamou");
    return ResponseEntity.ok().cacheControl(cache).body(teste);
	}
}

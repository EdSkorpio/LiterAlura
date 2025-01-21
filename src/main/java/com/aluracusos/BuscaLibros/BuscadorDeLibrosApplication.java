package com.aluracusos.BuscaLibros;



import com.aluracusos.BuscaLibros.repository.RepositAutor;
import com.aluracusos.BuscaLibros.repository.RepositLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication


public class BuscadorDeLibrosApplication implements CommandLineRunner {
	@Autowired
	private RepositLibros libroRepository;
	@Autowired
	private RepositAutor autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(BuscadorDeLibrosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal menuPrincipal = new MenuPrincipal(libroRepository, autorRepository);
		menuPrincipal.muestraElMenu();
	}
}

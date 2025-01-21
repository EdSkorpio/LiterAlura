package com.aluracusos.BuscaLibros;


import com.aluracusos.BuscaLibros.modelos.DaAutor;
import com.aluracusos.BuscaLibros.modelos.DaLibros;
import com.aluracusos.BuscaLibros.modelos.DatosLibros;
import com.aluracusos.BuscaLibros.modelos.DatosRec;
import com.aluracusos.BuscaLibros.repository.RepositAutor;
import com.aluracusos.BuscaLibros.repository.RepositLibros;
import com.aluracusos.BuscaLibros.servicios.ConsumoAPI;
import com.aluracusos.BuscaLibros.servicios.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class MenuPrincipal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private RepositLibros libroRepository;
    private RepositAutor autorRepository;
    private List<DaLibros> libros;
    private List<DaAutor> autores;

    public MenuPrincipal(RepositLibros libroRepository, RepositAutor autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }
    public void muestraElMenu() {
        var opcion = 1;
        while (opcion != 0) {
            var menu = """
                   \nBUSCANDO LIBROS
                   ***************************************
                               
                               MENU DE OPCIONES
  
                   ***************************************         
                    1 - Ingresar Libro a la Coleccion.
                   ***************************************
                    2 - Consulta los Libros agregados.
                   ***************************************
                    3 - Consulta Autores Registrados.
                   ***************************************
                    4-  Libros mas Descargados.
                   ***************************************
                    5-  Caracteristicas de Libros.
                   ***************************************
                    0 - Salir
                   ***************************************
                 
                    """;
            System.out.println(menu);
            String menuOpciones = teclado.nextLine();

            try {
                opcion = Integer.parseInt(menuOpciones);
            } catch (NumberFormatException e) {
                System.out.println("""
                      
                         INGRESE UN NUMERO VALIDO
                              
                        """);
                continuar();
                continue;
            }
            switch (opcion) {
                case 1:
                    escribeLibroWeb();
                    break;
                case 2:
                    motrarLibrosBuscados();
                    break;
                case 3:
                    listaAutoresBuscados();
                case 4:
                    topDescargas();
                    break;
                case 5:
                    caracteristicasLlibros();
                    break;
                case 0:
                    System.out.println("Finalizando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    private DatosLibros getDatosLibros() {
        System.out.print("Escribe el libro a buscar: ");
        var tituloIngresado = teclado.nextLine();
        String json = consumoApi.obtenerDatosLibros(URL_BASE + "?search=" + tituloIngresado.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosRec.class);
        return datosBusqueda.listaLibros().stream()
                .filter(datosLibros -> datosLibros.titulo().toUpperCase().contains(tituloIngresado.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
    public void escribeLibroWeb(){
        DatosLibros datos = getDatosLibros();

        if (datos == null) {
            System.out.println("\n*** No se encontro el libro buscado *** ");
            continuar();
            return;
        }
        List<DaAutor> autores = datos.autor().stream()
                .map(datosAutor -> autorRepository.findByNombre(datosAutor.nombre())
                        .orElseGet(() -> {
                            DaAutor nuevoAutor = new DaAutor();
                            nuevoAutor.setNombre(datosAutor.nombre());
                            nuevoAutor.setFechaNacimiento(datosAutor.fechaNacimiento());
                            autorRepository.save(nuevoAutor);
                            return nuevoAutor;
                        })
                ).collect(Collectors.toList());
        DaLibros libro = new DaLibros(datos);
        libro.setAutores(autores);
        libroRepository.save(libro);
        System.out.println("El libro encontrado fue: " + datos);
        continuar();
    }
    private void motrarLibrosBuscados() {
        List<DaLibros> libros = libroRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(DaLibros::toString))
                .forEach(System.out::println);
        continuar();
    }
    private void listaAutoresBuscados() {
        List<DaAutor> autores = autorRepository.findAll();

        autores.stream()
                .sorted(Comparator.comparing(DaAutor::toString))
                .forEach(System.out::println);
        continuar();
    }
    private void topDescargas() {
        List<DaLibros> toplibros = libroRepository.findTop10ByOrderByNumeroDeDescargasDesc();
        toplibros.forEach(s->
                System.out.println("""
                        \n************************************************
                                TOP 10 DE LIBROS CON MAS DESCARGAS:
                        
                        Libro: %s
                        
                        Numero de descargas: %s
                        *************************************************
                        """.formatted(s.getTitulo(), s.getNumeroDeDescargas())));
        continuar();
    }
    private void caracteristicasLlibros() {
        List<DaLibros> caracateristicas = libroRepository.findAll();
        caracateristicas.forEach(s->
                System.out.println("""
                       \n*******************************************************************
                                    LAS CARACTERISTICAS Y LA INFORMACION DEL
                       
                        LIBRO:  %s
                       
                        SON: %s
                       ********************************************************************
                       """.formatted(s.getTitulo(),s.getDetallesLibro())));
        continuar();
    }
        private void continuar() {
        System.out.println("\nPara continuar presione 'Enter'");
        teclado.nextLine();
    }
}

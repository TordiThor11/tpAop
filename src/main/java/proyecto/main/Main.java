package proyecto.main;

import proyecto.dataBase.EnDiscoDB;
import proyecto.model.Concurso;
import proyecto.model.Participante;
import proyecto.model.PersistenceApi;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().start();
                } catch (Exception e) {
                    // log exception...
                    System.out.println(e);
                }
            }
        });

        var bd = new EnDiscoDB("C:\\Users\\Valentin\\IdeaProjects\\tpLayers\\src\\main\\resources\\concursos.txt", "C:\\Users\\Valentin\\IdeaProjects\\tpLayers\\src\\main\\resources\\inscriptos.txt");
        var memoryApi = new PersistenceApi(bd);
        ArrayList<Concurso> concursos;
        try {
            var participante = new Participante("perez", "pepe", "2020-123123", "mail@example.com");
//            concursos = memoryApi.obtenerConcursosAbiertos();
//            bd.cargarParticipante(new Participante("Gomez", "Pepe", "1234-123123", "pepin@gmail.com"), new Concurso(3, "concurso main", LocalDate.of(2024, 3, 15), LocalDate.of(2024, 8, 5)));
            memoryApi.cargarParticipante(participante, new Concurso(3, "concurso main", LocalDate.of(2024, 3, 15), LocalDate.of(2024, 8, 5)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        /*for (Concurso concurso : concursos) {
            System.out.println(concurso.toString());
        }*/

//        var concurso = new Concurso(3, "concurso main", LocalDate.of(2024, 4, 25), LocalDate.of(2024, 8, 25));

    }

    private void start() throws Exception {
//        new VentanaInscripcion(new MemoryApi(new EnDiscoDB("C:\\Users\\Valentin\\IdeaProjects\\tpLayers\\src\\main\\resources\\concursos.txt", "C:\\Users\\Valentin\\IdeaProjects\\tpLayers\\src\\main\\resources\\inscriptos.txt")));
//        new VentanaInscripcion(new PersistenceApi(new EnSqlDB()));
    }
}
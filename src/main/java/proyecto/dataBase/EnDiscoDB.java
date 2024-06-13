package proyecto.dataBase;

import proyecto.model.Concurso;
import proyecto.model.IOData;
import proyecto.model.Participante;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EnDiscoDB implements IOData {


    private String filePathConcursos;
    private String filePathParticipantes;

    public EnDiscoDB(String filePathConcursos, String filePathParticipantes) {
        this.filePathConcursos = filePathConcursos;
        this.filePathParticipantes = filePathParticipantes;
    }

    @Override
    public ArrayList<Concurso> obtenerConcursos() throws Exception {
        var concursos = new ArrayList<Concurso>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePathConcursos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(", ");
                if (parts.length != 4) {
                    throw new IOException("Error en la recuperacion de los datos");
                }
                concursos.add(new Concurso(Integer.parseInt(parts[0]), parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("yyyy/MM/dd")), LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return concursos;
    }

    @Override
    public void cargarParticipante(Participante participante, Concurso concurso) throws Exception {
        try (FileWriter fileWriter = new FileWriter(filePathParticipantes, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String lineaAgregar = participante.getApellido() + ", " + participante.getNombre() + ", " + participante.getTelefono() + ", " + participante.getMail() + ", " + concurso.getId();
            bufferedWriter.write(lineaAgregar);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error en la inscripcion");
        }
    }

    @Override
    public Concurso buscarPorNombre(String nombreConcurso) throws Exception {
        Concurso concurso = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePathConcursos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(", ");
                if (parts.length != 4) {
                    throw new IOException("Error en la recuperacion de los datos");
                }
                if (parts[1].equals(nombreConcurso)) {
                    concurso = new Concurso(Integer.parseInt(parts[0]), parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("yyyy/MM/dd")), LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        if (concurso == null) {
            throw new Exception("No existe un concurso con ese nombre");
        }
        return concurso;
    }
}

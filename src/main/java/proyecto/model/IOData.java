package proyecto.model;

import java.util.ArrayList;

public interface IOData {
    ArrayList<Concurso> obtenerConcursos() throws Exception;

    void cargarParticipante(Participante participante, Concurso concurso) throws Exception;

    Concurso buscarPorNombre(String nombreConcurso) throws Exception;

}

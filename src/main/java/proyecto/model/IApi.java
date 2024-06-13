package proyecto.model;

import java.util.ArrayList;

public interface IApi {
    public ArrayList<Concurso> obtenerConcursos() throws Exception;

    public ArrayList<Concurso> obtenerConcursosAbiertos() throws Exception;

    void cargarParticipante(Participante participante, Concurso concurso) throws Exception;

    Concurso buscarConcurso(String nombre) throws Exception;
}

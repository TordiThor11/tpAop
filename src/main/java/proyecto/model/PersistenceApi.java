package proyecto.model;

import java.util.ArrayList;

public class PersistenceApi implements IApi {


    private IOData ioData;

    public PersistenceApi(IOData ioData) {
        this.ioData = ioData;
    }

    @Override
    @Log
    public ArrayList<Concurso> obtenerConcursos() throws Exception {
        return ioData.obtenerConcursos();
    }

    @Override
    public ArrayList<Concurso> obtenerConcursosAbiertos() throws Exception {
        var concursos = this.obtenerConcursos();
        concursos.removeIf(Concurso::estaCerrado);
        return concursos;
    }

    @Override
    @Log
    public void cargarParticipante(Participante participante, Concurso concurso) throws Exception {
        ioData.cargarParticipante(participante, concurso);
    }

    @Override
    public Concurso buscarConcurso(String nombre) throws Exception {
        return ioData.buscarPorNombre(nombre);
    }
}

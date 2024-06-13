package proyecto.dataBase;

import proyecto.model.Concurso;
import proyecto.model.IOData;
import proyecto.model.Participante;

import java.sql.*;
import java.util.ArrayList;

public class EnSqlDB implements IOData {

    private static final String SQL_FIND_ALL_CONCURSOS = "SELECT * FROM `concursos`";
    private static final String SQL_CREATE_PARTICIPANTE = "insert into participantes(apellido, nombre, telefono, mail, id_concurso) values(?,?,?,?,?)";
    private static final String SQL_FIND_CONCURSO_BY_NAME = "SELECT * FROM concursos WHERE nombre = ?";
    private static final String ID_CONCURSO = "id";
    private static final String NOMBRE_CONCURSO = "nombre";
    private static final String FECHA_INICIO_CONCURSO = "fecha_inicio";
    private static final String FECHA_FIN_CONCURSO = "fecha_fin";
    private Connection dbConn;

    public EnSqlDB() throws Exception {
        String url = "jdbc:mysql://localhost:3306/ejercicio3layering";
        String user = "root";
        String password = "";
        try {
            this.dbConn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.getStackTrace();
            throw new Exception(e);
        }
    }

    @Override
    public ArrayList<Concurso> obtenerConcursos() throws Exception {
        var concursos = new ArrayList<Concurso>();
        try (Statement statement = dbConn.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_ALL_CONCURSOS);) {

            while (rs.next()) {
                concursos.add(new Concurso(rs.getInt(ID_CONCURSO),
                        rs.getString(NOMBRE_CONCURSO),
                        rs.getDate(FECHA_INICIO_CONCURSO).toLocalDate(),
                        rs.getDate(FECHA_FIN_CONCURSO).toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return concursos;
    }

    @Override
    public void cargarParticipante(Participante participante, Concurso concurso) throws Exception {
        try (PreparedStatement st = dbConn.prepareStatement(SQL_CREATE_PARTICIPANTE);) {
            st.setString(1, participante.getApellido());
            st.setString(2, participante.getNombre());
            st.setString(3, participante.getTelefono());
            st.setString(4, participante.getMail());
            st.setInt(5, concurso.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    @Override
    public Concurso buscarPorNombre(String nombreConcurso) throws Exception {
        try (PreparedStatement sent = dbConn.prepareStatement(SQL_FIND_CONCURSO_BY_NAME)) {
            sent.setString(1, nombreConcurso);
            ResultSet result = sent.executeQuery();
            if (result.next()) {
                Concurso concurso = new Concurso(result.getInt(ID_CONCURSO),
                        result.getString(NOMBRE_CONCURSO),
                        result.getDate(FECHA_INICIO_CONCURSO).toLocalDate(),
                        result.getDate(FECHA_FIN_CONCURSO).toLocalDate());
                return concurso;
            }
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        throw new Exception("No se encontro el concurso");
    }
}

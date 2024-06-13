package proyecto.dataBase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GrabarEnDisco {
    private String filePath;

    public GrabarEnDisco(String filePath) {
        this.filePath = filePath;
    }

    public void grabar(String informacion) throws Exception {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(informacion);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error en la grabacion");
        }
    }
}

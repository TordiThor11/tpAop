package proyecto.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import proyecto.dataBase.EnDiscoDB;

@Aspect

public class Aspectos {
    IOData ioData = new EnDiscoDB("src/main/resources/registroDeAspecto.txt", "src/main/resources/registroDeAspecto.txt");

    @Before("execution(@Marca * *(..))")
    public void registrarAntes(JoinPoint joinPoint) throws Exception {
        PersistenceApi persistenceApi = (PersistenceApi) joinPoint.getThis();
        Participante participante = (Participante) joinPoint.getArgs()[0];
        Concurso concurso = (Concurso) joinPoint.getArgs()[1];
        System.out.println("Se imprime por pantalla");
        ioData.cargarParticipante(participante, concurso);
    }

}

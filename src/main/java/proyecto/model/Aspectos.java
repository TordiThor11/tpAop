package proyecto.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import proyecto.dataBase.GrabarEnDisco;

@Aspect

public class Aspectos {
    GrabarEnDisco grabarEnDisco = new GrabarEnDisco("src/main/resources/registroDeAspecto.txt");

    @Before("execution(@Marca * *(..))")
    public void registrarAntes(JoinPoint joinPoint) throws Exception {
        String informacion = joinPoint.getSignature().getName() + ", "; //copio el nombre del metodo
        Object[] parametros = joinPoint.getArgs();
        if (parametros.length > 0) {
            for (int i = 0; i < parametros.length; i++) {
                informacion += (String) joinPoint.getArgs()[i].toString() + "|";//Agrego los parametros
            }
        } else {
            informacion = "no tiene parametros";
        }
        informacion += ", " + "12/12/2014"; //Agrego la fecha
        System.out.println(informacion);
        grabarEnDisco.grabar(informacion);
    }

}

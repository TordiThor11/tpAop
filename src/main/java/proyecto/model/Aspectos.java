package proyecto.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import proyecto.dataBase.GrabarEnDisco;

import java.time.LocalDateTime;

@Aspect

public class Aspectos {
    GrabarEnDisco grabarEnDisco = new GrabarEnDisco("src/main/resources/registroDeAspecto.txt");

    @Before("execution(@Log * *(..))")
    public void registrarAntes(JoinPoint joinPoint) throws Exception {
        String informacion = joinPoint.getSignature().getName() + ", "; //copio el nombre del metodo
//        System.out.println(informacion);//Agarra el nombre de la funcion obtenerConcursos, pero no desaparece despues
        Object[] parametros = joinPoint.getArgs();
//        System.out.println(informacion);
        if (parametros.length > 0) {
//            System.out.println(informacion); //desaparece aca
            informacion += "|";
            for (int i = 0; i < parametros.length; i++) {
                informacion += (String) joinPoint.getArgs()[i].toString() + "|";//Agrego los parametros
            }
        } else {
            informacion = "no tiene parametros";
        }
        informacion += ", " + LocalDateTime.now(); //Agrego la fecha
        System.out.println(informacion);
        grabarEnDisco.grabar(informacion);
    }

}

package by.my.project.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

//    private static final Logger logger = Logger.getLogger(LoggerAspect.class.getName());
//
//    @Around("execution(* by.my.project.service.JpaUserService.addUser(..))")
//    public void registrationUser(ProceedingJoinPoint joinPoint) {
//        logger.info("*** Начинаем добавлять User ***");
//        try {
//            joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        logger.info("*** User добавлен ***");
//    }
}

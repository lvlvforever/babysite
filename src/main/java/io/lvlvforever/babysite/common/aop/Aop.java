
package io.lvlvforever.babysite.common.aop;


import io.lvlvforever.babysite.common.util.CommonRetUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
public class Aop {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();


    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void recordIP() { }

    @Before("validate()")
    public void before() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);


        String remoteHost = request.getRemoteHost();



        //如果要获取Session信息的话，可以这样写：
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

    }








    /**
     * 对controller的方法入参进行验证 验证对象包括javaBean类型和普通参数
     */
//    @Pointcut("execution(public * com.qq.bookatomconsole.*.controller.*.*(..))")
    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validate() { }

    @Around("validate()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{

        Object[] objects = pjp.getArgs();
        if (objects.length == 0) {
            return pjp.proceed();
        }
        Map<String, Object> data = CommonRetUtil.retParamInvalid();
        ArrayList<String> msg = new ArrayList<>();
        data.put("msg", msg);
        //校验javaBean类型的参数
        for (Object obj : objects) {
            if (obj instanceof BeanPropertyBindingResult) {
                BeanPropertyBindingResult result = (BeanPropertyBindingResult) obj;
                if (result.hasErrors()) {
                    List<ObjectError> list = result.getAllErrors();
                    for (ObjectError err : list) {
                        msg.add(err.getDefaultMessage());
                    }
                }
            }
        }
        //校验单个参数
        Object target = pjp.getThis();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, objects);
        if (!validResult.isEmpty()) {
            for (ConstraintViolation<Object> vi : validResult) {
                msg.add(vi.getMessage());
            }
        }
        if (msg.isEmpty()) {
            return pjp.proceed();
        }else{
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            CommonRetUtil.writeResponse(response,data);
        }
        return null;
    }

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }

    /**
     * 处理controller方法出现的异常
     */
    @Pointcut("execution (public * com.qq.bookatomconsole.*.controller.*.*(..))")
    public void catchException() {

    }

    @AfterThrowing(value = "catchException()",throwing = "e")
    public void catchExceptionAdvice(JoinPoint jp, Exception e) {
        StringBuilder arg = new StringBuilder();
        Arrays.stream(jp.getArgs()).forEach(obj -> arg.append(obj == null ? "null" : obj.toString() + "|"));
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        String methodName = method.getName();
        String msg = e.getMessage();
        String backtrace = ExceptionUtils.getStackTrace(e);
        Map<String, Object> error = CommonRetUtil.retServerBusy();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CommonRetUtil.writeResponse(response,error);
    }

}

package com.hy.salon.basic.common.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.salon.basic.common.request.ServletRequestParamsHelper;
import com.hy.salon.basic.dao.OperateLogDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.OperateLog;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.admin.service.SystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Aspect    //该标签把LoggerAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把LoggerAspect类放到IOC容器中
public class LoggerAspect {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ServletRequestParamsHelper helper;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;


    @Resource(name = "operateLogDao")
    private OperateLogDao operateLogDao;
    @Resource(name="systemRoleService")
    private SystemRoleService systemRoleService;
    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);


    private static final String MAPPING_PATH = "映射路径：{}";
    private static final String METHOD_DESC = "方法描述：{}";
    private static final String MAPPING_ALLOW_METHOD = "请求可用：{}";

    /**
     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码
     * 使用@Pointcut声明切入点表达式
     * 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
     */
    @Pointcut("execution(public * com.*.*.controller.*.*(..))||execution(public * com.*.*.*.controller.*.*(..))")
    public void declareJoinPointExpression() {

    }

    /**
     * 前置通知
     * @param joinPoint
     */
   // @Before("declareJoinPointExpression() &&@annotation(authorized)") //该标签声明次方法是一个前置通知：在目标方法开始之前执行
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) throws Exception {
        if (null == RequestContextHolder.getRequestAttributes()) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("request 字符集为 {}", request.getCharacterEncoding());
        logger.info("request contentType is {}", request.getContentType());
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] names = methodSignature.getParameterNames();
        System.out.println(Arrays.toString(names));
        logger.info("请求IP：{}", request.getRemoteAddr());
        //logger.info("请求参数集: {}", ServletRequestReBox.getParams(request));
        logger.info("请求路径：{}", request.getRequestURL());
        logger.info("请求方式：{}", request.getMethod());
        getMethodDescription(joinPoint).forEach(logger::info);
        logger.info("响应方法名：{}", joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        Map reqParams = request.getParameterMap();


        String tempName= joinPoint.getSignature().getName();
        String temp[]= tempName.split("\\.");
        String name = temp[temp.length-1];
        if(name.equals("createStore")||name.equals("doLogin")){
            logger.info("响应方法名：{}",joinPoint.getSignature().getName());
            logger.info("请求URL参数集: {}",  JSON.toJSONString(reqParams, SerializerFeature.IgnoreErrorGetter));
            logger.info("接受参数集：{}", helper.getJsonFromString(names, args));
            OperateLog ol = new OperateLog();
            SystemUser user = authenticateService.getCurrentLogin();
            if(user!=null){
            }else{
                String[] userCode =  (String[])reqParams.get("userCode");
                String code = "";
                if(userCode==null){
               /*if(args.length>0){
                   SystemUser su =  (SystemUser)args[0];
                   code =   su.getUserCode();
               }*/
                }else{
                    code =   userCode[0];
                }
                user = systemUserDAO.getUserByCode(code);
            }
            if(user!=null) {
                Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
                List<SystemRole> list = systemRoleService.getRoleListById(user.getRecordId());
                logger.info("登陆用户名 {}", stuff2.getStuffName());
                ol.setOptUserId(stuff2.getRecordId());
                ol.setOptRoleId(list.get(0).getRecordId());
                ol.setOptUrl(request.getRequestURI());
                ol.setOptDate(new Date());
                ol.setOptAction(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
                ol.setOptInfo(helper.getJsonFromString(names, args));
                ol.setOptStatu(200);
                ol.setOptResult("成功");
                operateLogDao.insert(ol);
            }

        }else {

        }

    }
    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        logger.info("方法 " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + " 已执行完毕");
    }
    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     * @param joinPoint
     */
    @AfterReturning(value="declareJoinPointExpression()",returning="result")
    public void afterReturnMethod(JoinPoint joinPoint,Object result) throws JsonProcessingException {
        logger.info(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() +
                " 方法返回值为：{}", objectMapper.writeValueAsString(result));
    }
    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="declareJoinPointExpression()",throwing="ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex) throws Throwable {
        //ex.printStackTrace();
        logger.error(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() +
                " 方法返回异常：{}", ex.getMessage());
        throw ex;
    }
    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     * @param point
     */
    @Around(value="declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {

        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            System.out.println("The method "+ methodName+" start. param<"+ Arrays.asList(point.getArgs())+">");
            //执行目标方法

            //返回通知
            System.out.println("The method "+ methodName+" end. result<"+ result+">");
        } catch (Throwable e) {
            //异常通知
            System.out.println("this method "+methodName+" end.ex message<"+e+">");
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method "+ methodName+" end.");
        long startTime = System.currentTimeMillis();
         result = point.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("方法 " + point.getTarget().getClass().getName() + "." + point.getSignature().getName() + " 执行了 {} ms", endTime - startTime);
        return result;
    }


    /**
     * Acquire the description for annotation target method
     * @param joinPoint
     * @return
     * @throws Exception
     */
    protected JSONObject getMethodDescription(JoinPoint joinPoint) throws Exception {
        JSONObject methodDescription = new JSONObject();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        for (Method method : methods) {
            if(method.getName().equals(methodName)) {
                Class<?>[] classes = method.getParameterTypes();
                if(classes.length == arguments.length) {
                    if(method.getAnnotation(RequestMapping.class) != null) methodDescription.put(MAPPING_PATH, Arrays.toString(method.getAnnotation(RequestMapping.class).value()));
                    if(method.getAnnotation(RequestMapping.class) != null) methodDescription.put(MAPPING_ALLOW_METHOD, Arrays.toString(method.getAnnotation(RequestMapping.class).method().length == 0 ? RequestMethod.values() : method.getAnnotation(RequestMapping.class).method()));
                    break;
                }
            }
        }
        return methodDescription;
    }
}
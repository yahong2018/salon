/*
package com.hy.salon.basic.common.handler;
import com.jixi.pojo.Log;
import com.jixi.pojo.User;
import com.jixi.service.ILogService;
import com.jixi.service.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Date;
@Aspect
public class LogA {

    public Integer id=null;




    */
/**
     * 管理员登录方法的切入点
     *//*

    @Pointcut("execution(* com.jixi.service.impl.*.selectByUserName(..))")
    public void loginCell(){}

    */
/**
     * 添加业务逻辑方法切入点
     *//*

    @Pointcut("execution(* com.jixi.service.impl.*.add*(..))")
    public void insertCell() {}

    */
/**
     * 修改业务逻辑方法切入点
     *//*

    @Pointcut("execution(* com.jixi.service.impl.*.update*(..))")
    public void updateCell() {}

    */
/**
     * 删除业务逻辑方法切入点
     *//*

    @Pointcut("execution(* com.jixi.service.impl.*.delete*(..))")
    public void deleteCell() {}

    */
/**
     * 导入业务逻辑方法切入点
     *//*

    @Pointcut("execution(* com.jixi.service.impl.*.import*(..))")
    public void importCell() {}

    */
/**
     * 导出业务逻辑方法切入点
     *//*

    @Pointcut("execution(* com.jixi.service.impl.*.export*(..))")
    public void exportCell() {}

    */
/**
     * 登录操作(后置通知)
     * @param joinPoint
     * @param object
     * @throws Throwable
     *//*

    @AfterReturning(value = "loginCell()", argNames = "joinPoint,object", returning = "object")
    public void loginLog(JoinPoint joinPoint, Object object) throws Throwable {
        User user=(User)object;
        if (user==null) {
            return;
        }
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        id=user.getId();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        Log log = new Log();
        log.setContent(opContent);
        log.setAdminid(user.getId().toString());
        log.setAdmin(user.getUsername());
        log.setCreatedate(new Date());
        log.setOperation("登录");
        logService.saveLog(log);
    }

    */
/**
     * 添加操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     *//*

    @AfterReturning(value = "insertCell()", argNames = "joinPoint,object", returning = "object")
    public void insertLog(JoinPoint joinPoint, Object object) throws Throwable {
        // Admin admin=(Admin)
        // request.getSession().getAttribute("businessAdmin");
        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        Log log = new Log();
        log.setContent(opContent);
        log.setAdminid(id.toString());
        log.setAdmin(userService.selectOne(id).getUsername());
        log.setOperation("添加"+getMethodChineseName(methodName));
        log.setCreatedate(new Date());
        logService.saveLog(log);
    }

    */
/**
     * 管理员修改操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     * @throws Throwable
     *//*

    @AfterReturning(value = "updateCell()", argNames = "joinPoint,object", returning = "object")
    public void updateLog(JoinPoint joinPoint, Object object) throws Throwable {
        // Admin admin=(Admin)
        // request.getSession().getAttribute("businessAdmin");

        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        // 创建日志对象
        Log log = new Log();
        log.setContent(opContent);
        log.setAdminid(id.toString());
        log.setAdmin(userService.selectOne(id).getUsername());
        log.setOperation("修改"+getMethodChineseName(methodName));// 操作
        log.setCreatedate(new Date());
        logService.saveLog(log);
    }

    */
/**
     * 管理员导入操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     * @throws Throwable
     *//*

    @AfterReturning(value = "importCell()", argNames = "joinPoint,object", returning = "object")
    public void importLog(JoinPoint joinPoint, Object object) throws Throwable {
        // Admin admin=(Admin)
        // request.getSession().getAttribute("businessAdmin");

        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        // 创建日志对象
        Log log = new Log();
        log.setContent(opContent);
        log.setAdminid(id.toString());
        log.setAdmin(userService.selectOne(id).getUsername());
        log.setOperation("导入"+getMethodChineseName(methodName));// 操作
        log.setCreatedate(new Date());
        logService.saveLog(log);
    }

    */
/**
     * 管理员导出操作日志(后置通知)
     *
     * @param joinPoint
     * @param object
     * @throws Throwable
     *//*

    @AfterReturning(value = "exportCell()", argNames = "joinPoint,object", returning = "object")
    public void exportLog(JoinPoint joinPoint, Object object) throws Throwable {
        // Admin admin=(Admin)
        // request.getSession().getAttribute("businessAdmin");

        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取操作内容
        String opContent = optionContent(joinPoint.getArgs(), methodName);

        // 创建日志对象
        Log log = new Log();
        log.setContent(opContent);
        log.setAdminid(id.toString());
        log.setAdmin(userService.selectOne(id).getUsername());
        log.setOperation("导出"+getMethodChineseName(methodName));// 操作
        log.setCreatedate(new Date());
        logService.saveLog(log);
    }

    */
/**
     * 删除操作
     *
     * @param joinPoint
     * @param object
     *//*

    @AfterReturning(value = "deleteCell()", argNames = "joinPoint,object", returning = "object")
    public void deleteLog(JoinPoint joinPoint, Object object) throws Throwable {
        // Admin admin=(Admin)
        // request.getSession().getAttribute("businessAdmin");
        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();

        StringBuffer rs = new StringBuffer();
        rs.append(methodName);
        String className = null;
        for (Object info : joinPoint.getArgs()) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数，类型:" + className + "，值:(id:"
                    + joinPoint.getArgs()[0] + ")");
        }
        rs.append("]");

        // 创建日志对象
        Log log = new Log();
        log.setContent(rs.toString());
        log.setAdminid(id.toString());
        log.setAdmin(userService.selectOne(id).getUsername());
        log.setOperation("删除"+getMethodChineseName(methodName));// 操作
        log.setCreatedate(new Date());
        logService.saveLog(log);
    }

    */
/**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     *
     * @param args
     * @param mName
     * @return
     *//*

    public String optionContent(Object[] args, String mName) {
        if (args == null) {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(mName);
        String className = null;
        int index = 1;
        // 遍历参数对象
        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型:" + className + "，值:");
            // 获取对象的所有方法
            Method[] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
                if (methodName.indexOf("get") == -1) {// 不是get方法
                    continue;// 不处理
                }
                Object rsValue = null;
                try {
                    // 调用get方法，获取返回值
                    rsValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }
                // 将值加入内容中
                rs.append("(" + methodName + ":" + rsValue + ")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }

    */
/**
     * 判断操作的中文名（根据自己项目而定）
     * @param methodName
     * @return
     *//*

    public String getMethodChineseName(String methodName){
        if(methodName.endsWith("Allorder")){
            return "通用订单分析数据";
        }else if(methodName.endsWith("Commission")){
            return "业务员提成比例";
        }else if(methodName.endsWith("CustomerDetail")){
            return "客户月度明细数据";
        }else if(methodName.endsWith("Customer")){
            return "客户信息";
        }else if(methodName.endsWith("CustomerTypeDetail")){
            return "客户类型月度明细数据";
        }else if(methodName.endsWith("ItemBuy")){
            return "商品入库数据";
        }else if(methodName.endsWith("ItemSell")){
            return "商品出库数据";
        }else if(methodName.endsWith("Item")){
            return "商品数据";
        }else if(methodName.endsWith("Log")){
            return "系统日志数据";
        }else if(methodName.endsWith("PickorderItem")){
            return "商品售出数据";
        }else if(methodName.endsWith("ItemRank")){
            return "商品售出排行";
        }else if(methodName.endsWith("PickOrderSumAsFinance")){
            return "配货单财务数据";
        }else if(methodName.endsWith("PickOrderSum")){
            return "配货单数据";
        }else if(methodName.endsWith("SellmanDetail")){
            return "销售月度明细数据";
        }else if(methodName.endsWith("Sellman")){
            return "销售信息";
        }else if(methodName.endsWith("SellorderAll")){
            return "通用客户分析数据";
        }else if(methodName.endsWith("SellorderSum")){
            return "客户订单数据";
        }else if(methodName.endsWith("SellorderSumAsSellman")){
            return "销售的客户订单数据";
        }else if(methodName.endsWith("SellorderSumAsFinance")){
            return "客户订单财务数据";
        }else if(methodName.endsWith("SupplierDetail")){
            return "供应商月度明细数据";
        }else if(methodName.endsWith("Supplier")){
            return "供应商信息";
        }else if(methodName.endsWith("Tax")){
            return "进销项数据";
        }else if(methodName.endsWith("User")){
            return "管理员";
        }else {
            return "";
        }
    }


}
*/

package io.github.itliwei.daojia.config;


import io.github.itliwei.vboot.vorm.exception.BusinessException;
import io.github.itliwei.vboot.vorm.response.ErrorCode;
import io.github.itliwei.vboot.vorm.response.Resp;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author homelink
 * @date 2019/3/11 0011
 * @description 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {
    @Autowired
    private ApplicationEventPublisher publisher;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Resp handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return Resp.error(businessException.getCode(),businessException.getMessage());
        }else {
            log.error("server exception:{}",e);
            return Resp.error(ErrorCode.SERVER,e.getMessage());
        }
    }

    @ExceptionHandler(value = {AuthorizationException.class, UnauthenticatedException.class} )
    @ResponseBody
    public Resp handleAuth(Exception e) {
        return Resp.error(ErrorCode.AUTHORITY,"无权限");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Resp handle(BusinessException e)  {
        if (!e.getCode().equals(ErrorCode.SIGN.getCode()) && ! e.getCode().equals(ErrorCode.AUTHORITY.getCode())
                && !e.getCode().equals("41000")){
            log.error(e.getMessage(), e);
        }
        return Resp.error(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Resp handleException(Throwable e)  {
        log.error(e.getMessage(), e);
        return Resp.error(ErrorCode.SERVER,e.getMessage());
    }
}
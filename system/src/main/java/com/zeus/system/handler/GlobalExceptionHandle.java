package com.zeus.system.handler;



import com.zeus.system.exception.BusinessException;
import com.zeus.system.exception.ForbiddenException;
import com.zeus.system.exception.ZeusException;
import com.zeus.system.vo.common.ResultVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 全局异常处理
 *
 * @author zhuzihang
 */
@RestControllerAdvice
@Slf4j
@ConditionalOnClass(ResponseBodyAdvice.class)
public class GlobalExceptionHandle {

    /**
     * Validator 参数校验异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<Object> handleMethodVoArgumentNotValidException(BindException ex) {
        FieldError err = ex.getFieldError();
        // err.getField() 读取参数字段
        // err.getDefaultMessage() 读取验证注解中的message值
        String message = "参数{".concat(err.getField()).concat("}").concat(err.getDefaultMessage());
        log.info("{} -> {}", err.getObjectName(), message);
        return new ResponseEntity<>(new ResultVO<>(ResultVO.PARAM_INVALID.getCode(), err.getDefaultMessage()), HttpStatus.OK);
    }

    /**
     * 业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResultVO<>(ex.getCode(), ex.getMessage()), HttpStatus.OK);
    }

    /**
     * Zeus全局异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ZeusException.class)
    public ResponseEntity<Object> handleBusinessException(ZeusException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResultVO<>(ResultVO.EXCEPTION.getCode(), ex.getMessage()), HttpStatus.OK);
    }

    /**
     * 文件超出最大上传限制
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> fileTooLarge(MaxUploadSizeExceededException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResultVO<>(ResultVO.EXCEPTION.getCode(), "文件大小超出系统限制"), HttpStatus.OK);
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Assert.notNull(ex.getBindingResult(), "断言失败 BindingResult 为空");
        if (ex.getBindingResult().hasErrors()) {
            FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
            // 读取参数字段
            String paramName = fieldError.getField();
            String errMsg = fieldError.getDefaultMessage();
            String messageLog = "参数{".concat(paramName).concat("}").concat(errMsg);
            if (log.isInfoEnabled()) {
                log.info("{} -> {}", messageLog, fieldError);
            }
            return new ResponseEntity<>(new ResultVO<>(ResultVO.PARAM_INVALID.getCode(), errMsg), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResultVO<>(ResultVO.PARAM_INVALID.getCode(), ex.getMessage()),
                    HttpStatus.OK);
        }
    }


    /**
     * sql异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public ResponseEntity<Object> handleBadSqlGrammarException(BadSqlGrammarException ex) {
        log.error("handleBadSqlGrammarException ==> {}", ex);
        return new ResponseEntity<>(new ResultVO<>(ResultVO.EXCEPTION.getCode(), "数据库异常，请检查sql是否正确"), HttpStatus.OK);
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResultVO<>(ResultVO.EXCEPTION.getCode(), ResultVO.EXCEPTION.getMessage()), HttpStatus.OK);
    }

    /**
     * Token无效
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> handleTokenNotValidException(ForbiddenException ex) {
        return new ResponseEntity<>(new ResultVO<>(ResultVO.FORBIDDEN.getCode(), ex.getMessage()), HttpStatus.OK);
    }
}

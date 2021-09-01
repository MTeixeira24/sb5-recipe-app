package pt.mteixeira.sb5recipeapp.controllers.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView entityNotFound(HttpServletRequest req, Exception ex) {
        log.error("operation='entityNotFound', msg='Failure to retrieve an entity', path='{}', " +
                        "queryParameters='{}'", req.getPathInfo(), req.getQueryString(), ex);
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.NOT_FOUND);
        mav.setViewName("notFound");
        return mav;
    }

}

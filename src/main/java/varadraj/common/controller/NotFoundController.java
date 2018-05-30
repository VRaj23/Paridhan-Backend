package varadraj.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;

@RestController
public class NotFoundController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public Object onNotFound(HttpServletRequest request) {
    	Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
    	if (exception == null)
    		return new ModelAndView("forward:/");
    	else if(exception.getMessage().equals("Request processing failed; nested exception is java.lang.NullPointerException"))
    		return new JsonResponse<String>(500, JsonResponseMessage.ERROR, "NullPointerException");
    	else
    		return new JsonResponse<String>(400, JsonResponseMessage.BAD_REQUEST, exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}

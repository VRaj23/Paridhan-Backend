package varadraj.common.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NotFoundController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView onNotFound() {
        return new ModelAndView("forward:/");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}

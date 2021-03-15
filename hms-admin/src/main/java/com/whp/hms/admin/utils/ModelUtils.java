package com.whp.hms.admin.utils;

import com.whp.hms.core.entity.Manager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ModelUtils {
    public static ModelAndView createModel(HttpServletRequest request, String view) {
        ModelAndView result = new ModelAndView();
        result.setViewName(view);
        if (ShiroUtil.getSubject() != null) {
            Manager manager = (Manager) ShiroUtil.getSubject().getPrincipal();
            result.addObject("manager", manager);
        }
        return result;
    }
}

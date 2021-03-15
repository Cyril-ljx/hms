package com.whp.hms.client.utils;

import com.whp.hms.core.entity.Client;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ModelUtils {
    public static ModelAndView createModel(HttpServletRequest request, String view) {
        ModelAndView result = new ModelAndView();
        result.setViewName(view);
        if (ShiroUtil.getSubject() != null) {
            Client manager = (Client) ShiroUtil.getSubject().getPrincipal();
            result.addObject("client", manager);
        }
        return result;
    }
}

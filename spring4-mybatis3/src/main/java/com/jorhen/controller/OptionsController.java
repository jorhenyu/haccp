package com.jorhen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jorhen.service.OptionService;

/**
 * @author  jason
 * @since 2020/7/11.
 * @see Controller
 * 下拉選項數據列表
 */
@Controller
@RequestMapping(value = "/options")
public class OptionsController {
	
	// 設置log
	private static Logger log = Logger.getLogger(UserController.class);
	
    @Autowired
    OptionService optionService;
    @RequestMapping(value = "/catsType")
    public String catsTypeOtions(HttpServletRequest request ,Model model){
        String condition = request.getParameter("condition");
        String order = request.getParameter("order");
        Map<String,Object> param = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(condition)){
            param.put("condition",condition);
        }
        if(StringUtils.isNotBlank(condition)){
            param.put("order",order);
        }

        model.addAttribute("options",this.optionService.catsTypeOption(param));
        List<Map<String, Object>> aa =  this.optionService.catsTypeOption(param);
        for(Map<String, Object> i : aa){ 
        	log.info(i);
        } 
        

        return "plan/add";
    }
    /*
    @RequestMapping(value = "/brand")
    public String brandOtions(HttpServletRequest request ,Model model){
        String condition = request.getParameter("condition");
        String order = request.getParameter("order");
        Map<String,Object> param = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(condition)){
            param.put("condition",condition);
        }
        if(StringUtils.isNotBlank(condition)){
            param.put("order",order);
        }
        model.addAttribute("options",this.optionService.brandOption(param));
        return "common/options";
   }
   */

}

package com.jorhen.util;

import java.util.Enumeration;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author jorhen
 * 把request物件中的請求參數封裝到bean中
 */

public class WebUtils {
	
    /**
     * 生成UUID
     * @return
     */
    public static String makeId(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}

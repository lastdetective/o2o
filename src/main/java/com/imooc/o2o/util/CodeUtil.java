package com.imooc.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession()
                .getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String vefifyCodeAcutal = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if (vefifyCodeAcutal == null
                || verifyCodeExpected == null
                || !vefifyCodeAcutal.toLowerCase().equals(verifyCodeExpected.toLowerCase())) {
            return false;
        }

        return true;

    }
}

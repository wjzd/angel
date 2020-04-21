package com.yy.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestInterceptor implements HandlerInterceptor {
    @Override  // 在执行目标方法之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession(true);
        //判断用户ID是否存在，不存在就跳转到登录界面

        String url=request.getRequestURI();
        if (url.contains("/LoginCon/login") || url.contains("/collectCon/") || url.contains("/views/") || url.contains("/downloanCon/") || url.contains("/orderCon/") || url.contains("/userCon/")){

            if(session.getAttribute("adminUserInfo") == null){
                response.sendRedirect(request.getContextPath()+"/viewLoginInit");
                return false;
            }else{
                session.setAttribute("adminUserInfo", session.getAttribute("adminUserInfo"));
                return true;

            }
        }
        if (url.contains("/page/center") || url.contains("/page/modifyHeadImg") || url.contains("/page/updateUserInfo") || url.contains("/page/tomodifyUserInfo") || url.contains("/page/checkPassword") ||url.contains("/page/updateCollect")||url.contains("/page/download")){
            if(session.getAttribute("userInfo") == null){
                response.sendRedirect(request.getContextPath()+"/page/login");
                return false;
            }else{
                session.setAttribute("userInfo", session.getAttribute("userInfo"));
                return true;
            }
        }
        return true;
    }

    @Override  // 执行目标方法之后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override  // 在请求已经返回之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}


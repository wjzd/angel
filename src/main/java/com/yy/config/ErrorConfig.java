//package com.yy.config;
//
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.server.ErrorPageRegistrar;
//import org.springframework.boot.web.server.ErrorPageRegistry;
//import org.springframework.http.HttpStatus;
//
//public class ErrorConfig implements ErrorPageRegistrar {
//    @Override
//    public void registerErrorPages(ErrorPageRegistry registry) {
//        /*1、按错误的类型显示错误的网页*/
//        /*错误类型为404，找不到网页的，默认显示404.html网页*/
//        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPage");
//        /*错误类型为500，表示服务器响应错误，默认显示500.html网页*/
//        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPage");
//        ErrorPage e400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/errorPage");
//        registry.addErrorPages(e400 ,e404, e500);
//    }
//}

package com.yy.swaggerCon;

import io.swagger.annotations.*;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="接口所在的类")
@RequestMapping ("/my")
public class ArticleRestController {

    @RequestMapping(value="/list", method= RequestMethod.POST)
    @ApiOperation(value = "接口名", notes = "接口描述", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "length",value = "参数1", required = true, paramType = "path"),
            @ApiImplicitParam(name = "size",value = "参数2", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page",value = "参数3", required = true, paramType = "header"),
            @ApiImplicitParam(name = "total",value = "参数4", required = true, paramType = "form"),
            @ApiImplicitParam(name = "start",value = "参数5",dataType = "string", paramType = "body")
    })
    public String register(){
        return "has permission";
    }

    @ApiOperation(value="添加用户", notes="创建新用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser (@RequestBody User user){

        try {

        } catch (Exception e) {

            e.printStackTrace();
        }
        return "4444";
    }

}

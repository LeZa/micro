package com.eureka.me.token.controller;

import com.eureka.me.token.db.RocksDBServiceDetail;
import com.eureka.me.token.db.TokenRocksDBServiceDetail;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {

    private RocksDBServiceDetail rocksDBServiceDetail = RocksDBServiceDetail.getInstance();

    private TokenRocksDBServiceDetail tokenRocksDBServiceDetail = TokenRocksDBServiceDetail.getInstance();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *@Description  get token
     * @param request
     * @return
     */
    @RequestMapping(value="/get",method = RequestMethod.POST)
    @ResponseBody
    public String code(HttpServletRequest request){
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        Map<String,Object> userMap = new HashMap< String,Object >();
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)) {
            userMap.put("msg", "username or password is empty");
            userMap.put("code", "-1");
            userMap.put("data", new ArrayList());
            return new Gson().toJson( userMap );
        }
        String timeMillis = String.valueOf( Calendar.getInstance().getTimeInMillis() ); //token
        Map<String,Object> dataMap =  new HashMap<String,Object>();
/*        StringBuilder stringBuilder =  new StringBuilder(1230);

        stringBuilder.append(username).append(",").append(password);
        byte[] userData = stringBuilder.toString().getBytes();
        *//**
         * @Description  if not exists  this user create it
         *//*
        if( StringUtils.isEmpty( rocksDBServiceDetail.getString( username ) ) ){
            byte[] keyByte = username.getBytes();
            byte[] valByte =  stringBuilder.toString().getBytes();
            rocksDBServiceDetail.setString(keyByte,valByte);
        }else{
           userData = rocksDBServiceDetail.getString( username );
        }


        byte[] tokenByte = timeMillis.getBytes();
        tokenRocksDBServiceDetail.setString(tokenByte,userData);
        String user = new String( userData );*/
        dataMap.put("user",username);
        dataMap.put("token",timeMillis );

        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(String.valueOf(timeMillis),"1000");
        userMap.put("msg","operator success");
        userMap.put("code","0");
        userMap.put("data",dataMap );
        return new Gson().toJson( userMap );
    }


    /**
     * @Description  check this token
     * @param request
     * @return
     */
    @RequestMapping( value="/checkToken")
    @ResponseBody
    public String checkToken( HttpServletRequest request ){
            String token = request.getParameter("token");
            System.out.println("..."+token);
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("msg","session timeout");
            resultMap.put("code","0");
            resultMap.put("data",new ArrayList());
        return new Gson().toJson( resultMap );
    }

    @GetMapping( value="/update")
    public String updateAge(){
        return "update success";
    }

}

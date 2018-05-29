package com.eureka.me.zuul.config;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TokenFilter
        extends ZuulFilter {

    @Autowired
    private RestTemplate  restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
                .create();

        HttpServletRequest request = ctx.getRequest();
        String requestURL = String.valueOf(request.getRequestURL());
        if ( !(requestURL.indexOf("token") > -1 )) {
            if (StringUtils.isEmpty(request.getParameter("token"))) {
                Map<String, Object> tokenMap = new HashMap<String, Object>();
                tokenMap.put("msg", "token is empty");
                tokenMap.put("code", "-1");
                tokenMap.put("data", new ArrayList());
                ctx.setSendZuulResponse( false );
                ctx.setResponseStatusCode( 200 );
                ctx.setResponseBody( new Gson().toJson(tokenMap) );
                return null;
            }else{
                /** check this token**/
                String token = request.getParameter("token");
                this.loadBalancerClient.choose("service-token");
                String result = this.restTemplate.getForObject("http://service-token/checkToken?token="+token,String.class);
                Map<String,Object> resultMap = gson.fromJson(result,new TypeToken<Map<String,Object>>(){}.getType());
                if( resultMap != null
                        && resultMap.containsKey("code") ){
                    String code = String.valueOf( resultMap.get("code") );
                    if( Integer.parseInt( code )  ==  -1 ){
                        Map<String, Object> tokenMap = new HashMap<String, Object>();
                        tokenMap.put("msg", "session timeout");
                        tokenMap.put("code", "-1");
                        tokenMap.put("data", new ArrayList());
                        ctx.setSendZuulResponse( false );
                        ctx.setResponseStatusCode( 200 );
                        ctx.setResponseBody( new Gson().toJson(tokenMap) );
                        return null;
                    }
                }
            }
        }else{
            if( StringUtils.isEmpty( request.getParameter("username"))
                    || StringUtils.isEmpty( request.getParameter("password") )){
                Map<String, Object> tokenMap = new HashMap<String, Object>();
                tokenMap.put("msg", "username or password is empty");
                tokenMap.put("code", "-1");
                tokenMap.put("data", new ArrayList());
                ctx.setSendZuulResponse( false );
                ctx.setResponseStatusCode( 200 );
                ctx.setResponseBody( new Gson().toJson(tokenMap) );
                return null;
            }
        }
        return null;
    }
}

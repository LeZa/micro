package com.eureka.me.zuul.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

public class TokenFilter
        extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURL = String.valueOf(request.getRequestURL());
        if (  !(requestURL.indexOf("token") > -1)  ) {
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
                String token = request.getParameter("token");
                boolean tokenIs = this.stringRedisTemplate.hasKey( token );
                if(!tokenIs){
                    Map<String, Object> tokenMap = new HashMap<String, Object>();
                    tokenMap.put("msg", "token authenticate fail");
                    tokenMap.put("code", "-1");
                    tokenMap.put("data", new ArrayList());
                    ctx.setSendZuulResponse( false );
                    ctx.setResponseStatusCode( 200 );
                    ctx.setResponseBody( new Gson().toJson(tokenMap) );
                    return null;
                }
            }
        }
        return null;
    }
}

package com.amy.server_zuul.filter;

public class PreFilter{

}

/*
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;


@Configuration
public class PreFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        //return "pre";
        return FilterConstants.PRE_TYPE;

    }

    @Override
    public int filterOrder() {
        //return 1;
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info("Request method = {}, url = {}", request.getMethod(), request.getRequestURL().toString());
        return null;
    }
}
*/
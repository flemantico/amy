package com.amy.server_zuul.filter;

public class ErrorFilter {

}

/*
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        //return "error";
        return FilterConstants.ERROR_TYPE;
    }
    @Override
    public int filterOrder() {
        //return 1;
        return FilterConstants.SEND_ERROR_FILTER_ORDER -1;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        String response = ctx.getResponseBody();
        log.info("Error occurred, Response  = {}, ", response);
        return null;
    }
}
*/
package com.amy.server_zuul.filter;

public class RouterFilter{

}

/*
import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(RouterFilter.class);
    @Override
    public String filterType() {
        return "route";
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
    public Object run() {

        log.info("Inside route filter..");
        return null;
    }
}
*/
package com.czy.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * 后置过滤器post的使用
 */
@Component
public class PostAddResponseHeader extends ZuulFilter {
     @Override
     public String filterType() {
          return POST_TYPE;
     }

     @Override
     public int filterOrder() {
          return SEND_RESPONSE_FILTER_ORDER-1;
     }

     @Override
     public boolean shouldFilter() {
          return true;
     }

     /**
      * 向响应头添加数据
      * @return
      * @throws ZuulException
      */
     @Override
     public Object run() throws ZuulException {
          RequestContext context = RequestContext.getCurrentContext();
          HttpServletResponse response = context.getResponse();
          response.setHeader("X-HR", UUID.randomUUID().toString());
          return null;
     }
}

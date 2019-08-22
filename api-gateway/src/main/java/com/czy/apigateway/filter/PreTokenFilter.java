package com.czy.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 前置过滤器pre的使用
 */
@Component
public class PreTokenFilter extends ZuulFilter {

     @Override
     public String filterType() {
          return PRE_TYPE;//常量再FilterConstants中，shift双击搜索
     }

     @Override
     public int filterOrder() {
//          数值越小，优先级越高，减一升高优先级
          return PRE_DECORATION_FILTER_ORDER-1;
     }

     @Override
     public boolean shouldFilter() {
          //是否需要过滤器
          return true;
     }

     @Override
     public Object run() throws ZuulException {
//          从上下文中获取上下文对象
          RequestContext context = RequestContext.getCurrentContext();
//          从上下文对象中获取request请求
          HttpServletRequest request = context.getRequest();
//          从请求中获取token值
          String token = request.getParameter("token");
//          token值为空的处理
          if(StringUtils.isEmpty(token)){

               context.setSendZuulResponse(false);
//               设置响应码  权限不足 401
               context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
          }
          return null;
     }
}

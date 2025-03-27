package com.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PreTimeFilter.class);
	
	private static final String PRE = "pre";
	private static final String INIT_TIME = "initTime";
	private static final String LOG_TRACE = "%s request route to %s";
	private static final int FILTER_ORDER = 1;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		log.info(String.format(LOG_TRACE, request.getMethod(), request.getRequestURL().toString()));
		
		request.setAttribute(INIT_TIME, System.currentTimeMillis());
		return null;
	}

	@Override
	public String filterType() {
		return PRE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	
}

package com.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PostTimeFilter.class);
	
	private static final String POST = "post";
	private static final String INIT_TIME = "initTime";
	private static final String LOG_TRACE = "Time: %s";
	private static final int FILTER_ORDER = 1;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
				
		double initTime = ((Long) request.getAttribute(INIT_TIME)).doubleValue();
		double endTime = System.currentTimeMillis();
		log.info(String.format(LOG_TRACE, (endTime - initTime) / 1000));
		
		return null;
	}

	@Override
	public String filterType() {
		return POST;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	
}

package com.michong.common.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import javax.servlet.*;
import java.io.IOException;

/**
 * 调用Controller的Filter的基类
 *
 * @param <E>
 * @author liuxin_PC on 2017/10/7.
 */
public abstract class AbstractControlFilter<E> implements Filter {

    /**
     * Log
     */
    private static Logger log = LoggerFactory.getLogger(AbstractControlFilter.class);

    /**
     * Filter设置。
     */
    protected FilterConfig config = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.setConfig(config);
    }

    protected E getController() {
        if (log.isDebugEnabled()) {
            log.debug("getController() called.");
        }

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        String controllerId = config.getInitParameter("controller");
        if (controllerId == null || "".equals(controllerId)) {
            if (log.isDebugEnabled()) {
                log.debug("init parameter 'controller' isn't defined or " + "empty");
            }
            controllerId = getDefaultControllerBeanId();
        }

        if (log.isDebugEnabled()) {
            log.debug("controller bean id = \"" + controllerId + "\"");
        }

        // 取得Controller
        E controller;
        try {
            controller = (E) wac.getBean(controllerId, getControllerClass());
        } catch (NoSuchBeanDefinitionException e) {
            log.error("not found " + controllerId + ". " + "controller bean not defined in Beans definition file.", e);
            throw new RuntimeException(e);
        } catch (BeanNotOfRequiredTypeException e) {
            log.error("controller not implemented " + getControllerClass().toString() + ".", e);
            throw new RuntimeException(e);
        } catch (BeansException e) {
            log.error("bean generation failed.", e);
            throw new RuntimeException(e);
        }

        return controller;
    }

    /**
     * 返回Controller实现类
     */
    protected abstract Class<? extends E> getControllerClass();

    /**
     * 返回错误码
     */
    protected abstract String getErrorCode();

    /**
     * 返回Controller的BeanID
     */
    public abstract String getDefaultControllerBeanId();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
    }

    /**
     * 设置Filter配置
     *
     * @param config
     */
    protected void setConfig(FilterConfig config) {
        if (log.isDebugEnabled()) {
            log.debug("setConfig() called.");
        }
        this.config = config;
    }
}

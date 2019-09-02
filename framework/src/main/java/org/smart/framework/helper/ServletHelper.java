package org.smart.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

    private static final ThreadLocal<ServletHelper> SERVLET_HELPER_THREAD_LOCAL = new ThreadLocal<>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ServletHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        SERVLET_HELPER_THREAD_LOCAL.set(new ServletHelper(request, response));
    }

    public static void destroy(HttpServletRequest request, HttpServletResponse response) {
        SERVLET_HELPER_THREAD_LOCAL.remove();
    }

    private static HttpServletRequest getHttpServletRequest() {
        return SERVLET_HELPER_THREAD_LOCAL.get().request;
    }

    private static HttpServletResponse getHttpServletResponse() {
        return SERVLET_HELPER_THREAD_LOCAL.get().response;
    }

    private static HttpSession getSession() {
        return getHttpServletRequest().getSession();
    }

    private static ServletContext getServletContext() {
        return getHttpServletRequest().getServletContext();
    }

    public static void setRequestAttribute(String key, Object value) {
        getHttpServletRequest().setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getRequestAttribute(String key) {
        return (T) getHttpServletRequest().getAttribute(key);
    }

    public static void removeAttribute(String key) {
        getHttpServletRequest().removeAttribute(key);
    }

    public static void sendRedirect(String location) {
        try {
            getHttpServletResponse().sendRedirect(location);
        } catch (Exception e) {
            LOGGER.error("redirect failure", e);
        }
    }

    public static void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(String key) {
        return (T) getHttpServletRequest().getSession().getAttribute(key);
    }

    public static void setSessionAttribute(String key) {
        getHttpServletRequest().getSession().removeAttribute(key);
    }

    public static void invalidSession() {
        getHttpServletRequest().getSession().invalidate();
    }
}

package org.smart.framework.bean;

import java.util.Objects;

public class Request {

    private String requestPath;
    private String requestMethod;

    public Request(String requestPath, String requestMethod) {
        this.requestPath = requestPath;
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestPath, request.requestPath) &&
                Objects.equals(requestMethod, request.requestMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestPath, requestMethod);
    }
}

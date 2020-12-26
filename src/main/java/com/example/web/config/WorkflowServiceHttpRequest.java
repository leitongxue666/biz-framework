package com.example.web.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import java.net.URI;

/**
 * @author Lei
 * craeted: 2020/4/15
 */
public class WorkflowServiceHttpRequest implements HttpRequest {

    private HttpRequest delegate;
    private String serviceAddress;

    public WorkflowServiceHttpRequest(HttpRequest delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getMethodValue() {
        return delegate.getMethodValue();
    }

    @Override
    public URI getURI() {
        return URI.create("http://localhost:8080" + delegate.getURI());
    }

    @Override
    public HttpHeaders getHeaders() {
        return delegate.getHeaders();
    }
}

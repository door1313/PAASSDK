package cloud.oracle.paas.model;

import java.util.Map;

/**
 * Result of HTTP response
 */
public class HTTPResult {

    private int status;
    private String content;
    private Map<String, Object> headers;

    public HTTPResult(int status, String content, Map<String, Object> headers) {
        this.status = status;
        this.content = content;
        this.headers = headers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}

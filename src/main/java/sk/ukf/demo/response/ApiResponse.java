package sk.ukf.demo.response;

import java.time.OffsetDateTime;

public class ApiResponse {
    private Object data;
    private String message;
    private String datetime;

    public ApiResponse(Object data, String message) {
        this.data = data;
        this.message = message;
        this.datetime = OffsetDateTime.now().toString();
    }

    public Object getData() { return data; }
    public String getMessage() { return message; }
    public String getDatetime() { return datetime; }

    public void setData(Object data) { this.data = data; }
    public void setMessage(String message) { this.message = message; }
    public void setDatetime(String datetime) { this.datetime = datetime; }
}

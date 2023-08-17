package contnet_checker.model;

import lombok.Data;

@Data
public class Request {
    private String text;
    private String postBackTopic;
    private String entityId;
    private RequestType requestType;

    public enum RequestType {
        COMMENT, ARTICLE
    }
}

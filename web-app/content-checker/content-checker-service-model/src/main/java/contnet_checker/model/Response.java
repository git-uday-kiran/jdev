package contnet_checker.model;

import lombok.Data;

@Data
public class Response {

    private ContentCheckOutcome contentCheckOutcome;
    private Request.RequestType requestType;
    private String entityId;

    public static Response withRequest(Request request, ContentCheckOutcome checkOutcome) {
        var response = new Response();
        response.contentCheckOutcome = checkOutcome;
        response.requestType = request.getRequestType();
        response.entityId = request.getEntityId();
        return response;
    }
}

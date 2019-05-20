package hello;

public class CDDJob {

    private final long instanceId;
    private final String status;

    public CDDJob(String content,long id) {
        this.instanceId = id;
        this.status = content;
    }
    
    public CDDJob(String content) {
    	this.instanceId = 0;
    	this.status = content;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public String getStatus() {
        return status;
    }
}

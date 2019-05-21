package hello;

public class CDDJob {

    private final long instanceId;
    private final String status;
    private final String errorMsg;
    private final String errorDesc;

    public CDDJob(String content, String eMsg,String eDesc) {
    	this.instanceId = 0;
    	this.status = content;
    	this.errorMsg=eMsg;
    	this.errorDesc=eDesc;
    }

	public CDDJob(String content,long id) {
        this.instanceId = id;
        this.status = content;
        this.errorMsg=null;
        this.errorDesc=null;
    }
    
    public CDDJob(String content) {
    	this.instanceId = 0;
    	this.status = content;
    	this.errorMsg=null;
    	this.errorDesc=null;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public String getStatus() {
        return status;
    }
    
    public String getErrorMsg() {
		return errorMsg;
	}

	public String getErrorDesc() {
		return errorDesc;
	}
}

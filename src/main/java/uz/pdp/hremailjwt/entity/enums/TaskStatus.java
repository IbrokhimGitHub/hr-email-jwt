package uz.pdp.hremailjwt.entity.enums;

public enum TaskStatus {
    NEW(1,"new"),
    IN_PROCESS(2,"in process"),
    DONE(3,"done");

    private final int value;
    private final String reasonPhrase;
    TaskStatus(int value, String reasonPhrase) {
        this.value=value;
        this.reasonPhrase=reasonPhrase;

    }
    public int value() {
        return this.value;
    }


    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}

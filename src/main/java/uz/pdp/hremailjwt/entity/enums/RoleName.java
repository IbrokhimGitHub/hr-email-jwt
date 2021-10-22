package uz.pdp.hremailjwt.entity.enums;

public enum RoleName {
    DIRECTOR(1,"director"),
    HR_MANAGER(2,"hr_manager"),
    MANAGER(3,"manager"),
    WORKER(1,"worker");
    private final int value;
    private final String reasonPhrase;
    RoleName(int value, String reasonPhrase) {
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

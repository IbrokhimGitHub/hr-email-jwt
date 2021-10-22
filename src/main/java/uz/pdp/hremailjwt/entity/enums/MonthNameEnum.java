package uz.pdp.hremailjwt.entity.enums;

import org.springframework.http.HttpStatus;

public enum MonthNameEnum {

    JANUARY(1,"January"),
    FEBRUARY(2, "February"),
    MARCH(3,"March"),
    APRIL(4,"April"),
    MAY(5,"May"),
    JUNE(6,"June"),
    JULY(7,"July"),
    AUGUST(8,"August"),
    SEPTEMBER(9,"September"),
    OCTOBER(10,"October"),
    NOVEMBER(11,"November"),
    DECEMBER(12,"December");
    private final int value;
    private final String reasonPhrase;
    MonthNameEnum(int value, String reasonPhrase) {
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

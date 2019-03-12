package com.learn.stateMachine.anotherExample;

/**
 * @author gonghe.hogan
 */


public enum LeavePermitEnum {

    ANNUAL_LEAVE("annual_leave", "年休假" ),
    CASUAL_LEAVE("casual_leave", "事假" ),
    MEDICAL_LEAVE("medical_leave", "病假" ),
    MARRIAGE_LEAVE("marriage_leave", "混假" );

    private String type;
    private String meme;

    LeavePermitEnum() {
    }

    LeavePermitEnum(String type, String meme) {
        this.type = type;
        this.meme = meme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }
}

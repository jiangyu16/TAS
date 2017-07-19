package com.tas.bean;

public class PaperType {
    
    int paperTypeId;
    String paperTypeName;
    
    /**
     * @return the paperTypeId
     */
    public int getPaperTypeId() {
        return paperTypeId;
    }
    /**
     * @param paperTypeId the paperTypeId to set
     */
    public void setPaperTypeId(int paperTypeId) {
        this.paperTypeId = paperTypeId;
    }
    /**
     * @return the paperTypeName
     */
    public String getPaperTypeName() {
        return paperTypeName;
    }
    /**
     * @param paperTypeName the paperTypeName to set
     */
    public void setPaperTypeName(String paperTypeName) {
        this.paperTypeName = paperTypeName;
    }
    
    @Override
    public String toString() {
        return "PaperType [paperTypeId=" + paperTypeId + ", paperTypeName=" + paperTypeName + "]";
    }
    
}

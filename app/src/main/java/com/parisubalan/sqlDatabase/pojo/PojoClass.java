package com.parisubalan.sqlDatabase.pojo;

public class PojoClass {
    String name;
    String mobile;
    String standard;
    String section;

    public PojoClass(String name, String mobile, String standard, String section) {
        this.name = name;
        this.mobile = mobile;
        this.standard = standard;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

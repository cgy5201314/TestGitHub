package com.example.demo.Config;

public enum SeasonEnum {
    SPRING("春天","春暖花开"),
    SUMMER("夏天","春暖花开"),
    FALL("秋天","春暖花开"),
    WINTER("动态","春暖花开");
    private String name;
    private String desc;
     SeasonEnum(String name,String desc){
        this.name=name;
        this.desc=desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "SeasonEnum{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(SeasonEnum.SPRING);
        SeasonEnum s=SeasonEnum.SPRING;
        
        System.out.println(s.ordinal());


    }
}

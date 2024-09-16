package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category{

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private long id;

    public Category(int i, String cats) {
        this.name = cats;
        this.id = i;
    }
    public Category(){}
    public String getName(){
        return name;
    }

    public long getId(){
        return id;
    }
}
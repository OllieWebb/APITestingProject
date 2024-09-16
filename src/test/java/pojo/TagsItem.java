package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagsItem{

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private long id;

    public String getName(){
        return name;
    }

    public long getId(){

        return id;
    }

    public TagsItem(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public TagsItem(){}
}
package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet{

    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private long id;

    @JsonProperty("category")
    private Category category;
    @JsonProperty("tags")
    private List<TagsItem> tags;
    @JsonProperty("status")
    private String status;

    public List<String> getPhotoUrls(){
        return photoUrls;
    }
    public String getName(){
        return name;
    }

    public long getId(){

        return id;
    }

    public Category getCategory(){
        return category;
    }

    public List<TagsItem> getTags(){
        return tags;
    }

    public String getStatus(){
        return status;
    }

    public Pet(int id,String name, Category category, List<String> photoUrls,  List<TagsItem> tags, String status) {
        this.photoUrls = photoUrls;
        this.name = name;
        this.id = id;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }
    public Pet(){}
}
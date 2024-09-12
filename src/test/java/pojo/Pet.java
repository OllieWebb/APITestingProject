package pojo;

import java.util.List;

public record Pet(
        List<String> photoUrls,
        String name,
        int id,
        Category category,
        List<TagsItem> tags,
        String status
) {
}
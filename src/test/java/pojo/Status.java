package pojo;

import java.util.List;

public record Status(
        List<StatusItem> status
) {
}
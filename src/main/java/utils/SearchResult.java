package utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResult {

    private String url;
    private String desc;
    private String title;

}

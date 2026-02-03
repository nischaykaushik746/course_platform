package com.courseplatform.course_platform_api.course;

import com.courseplatform.course_platform_api.course.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public SearchResponse search(@RequestParam("q") String query) {
        return searchService.search(query);
    }
}

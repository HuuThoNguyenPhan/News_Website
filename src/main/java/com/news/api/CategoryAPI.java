package com.news.api;

import com.news.dto.CategoryDTO;
import com.news.service.ICategoryService;
import com.news.utils.MyUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/category")
    public List<CategoryDTO> createCategory() {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();

        try {
            Document doc = MyUtils.connectURL("https://vnexpress.net/rss");
            Elements elements = doc.getElementsByTag("a");
            if (categoryDTOS.isEmpty()) {
                for (Element element : elements) {
                    if (element.text().contains("RSS")) {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setCode(element.attr("href").replaceAll("[/rss.]", ""));
                        categoryDTO.setName(element.text().replaceAll("RSS", ""));
                        categoryService.save(categoryDTO);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryService.findAll();
    }
}

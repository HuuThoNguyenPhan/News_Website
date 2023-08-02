package com.news.api;

import com.news.api.output.NewOutput;
import com.news.dto.NewDTO;
import com.news.service.ICategoryService;
import com.news.service.INewService;
import com.news.utils.MyUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class NewAPI {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/new")
    public NewOutput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("code") String code) {
        NewOutput result = new NewOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(newService.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
        if (result.getListResult().isEmpty()) {
            System.out.println("https://vnexpress.net" + code);
            try {
                Document doc = MyUtils.connectURL("https://vnexpress.net/rss/" + code + ".rss");
                Elements elements = doc.select("item");
                for (Element item : elements) {
                    NewDTO newDTO = new NewDTO();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
                    String description = item.selectFirst("description").text();
                    String dateString = item.selectFirst("pubDate").text();
                    newDTO.setTitle(item.selectFirst("title").text());
                    newDTO.setIsDeleted(false);
                    newDTO.setShortDescription(description.substring(description.indexOf("</br>") + 5));
                    newDTO.setCategoryCode(code);
                    newDTO.setCreatedDate(inputFormat.parse(dateString));
                    newDTO.setThumbnail(description.substring(description.indexOf("src=\"") + 5, description.indexOf("\"", description.indexOf("src=\"") + 5)));
                    newService.save(newDTO);
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @GetMapping(value = "/new/{id}")
    public NewDTO getNewDetails(@PathVariable("id") long id) {
        return newService.findById(id);
    }

    @PostMapping(value = "/new")
    public NewDTO createNew(@RequestBody NewDTO model) {
        return newService.save(model);
    }

    @PutMapping(value = "/new/{id}")
    public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return newService.save(model);
    }

    @PutMapping(value = "/new")
    public List<NewDTO> deleteNew(@RequestBody long[] ids) {
        return newService.delete(ids);
    }

}

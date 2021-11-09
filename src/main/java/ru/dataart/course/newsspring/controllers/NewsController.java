package ru.dataart.course.newsspring.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dataart.course.newsspring.entity.News;
import ru.dataart.course.newsspring.exceptions.InputFileException;
import ru.dataart.course.newsspring.handler.FileHandler;
import ru.dataart.course.newsspring.services.NewsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@RequestMapping("/news")
@RequiredArgsConstructor
@Controller
public class NewsController {
    private final NewsService newsService;
    private final FileHandler fileHandler;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        @RequestParam("type") Optional<String> type) {
        int currentPage = page.orElse(1);
        long count = type.map(newsService::getCountByType).orElseGet(newsService::getCountOfAll);
        String typeOfNews=type.orElse("none");
        int pageSize = size.orElse((int) (count >= 20 ? count / 10 : 2));
        model.addAttribute("typePage",typeOfNews);
        Page<News> newsPage = newsService.findPaginated(PageRequest.of(currentPage - 1, pageSize),typeOfNews);

        model.addAttribute("newsPage", newsPage);

        int totalPages = newsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "news/index";
    }

    @GetMapping("/{id}")
    public String showOneNews(@PathVariable("id") long id, Model model) {
        model.addAttribute("one", newsService.getNewsById(id));
        return "news/singleNews";
    }

    @GetMapping("/new")
    public String newNews(@ModelAttribute("news") News news) {
        return "news/newNews";
    }

    @PostMapping()
    public String createNews(@ModelAttribute("file") MultipartFile file,
                             @RequestParam("type") String type,
                             Model model) {
        try {
            News news =fileHandler.openFile(file);
            news.setType(type);
            newsService.save(news);
        } catch (InputFileException e) {
            model.addAttribute("error", e.getMessage());
            return "news/newNews";
        }
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        newsService.delete(id);
        return "redirect:/news";
    }
}

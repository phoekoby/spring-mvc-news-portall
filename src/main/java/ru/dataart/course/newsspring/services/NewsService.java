package ru.dataart.course.newsspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import ru.dataart.course.newsspring.entity.News;
import ru.dataart.course.newsspring.repository.NewsRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
    public List<News> findByType(String string){
        return newsRepository.findAllByTypeIsLikeOrderByDateDesc(string);
    }

    public long getCountOfAll() {
        return newsRepository.count();
    }

    public long getCountByType(String string){
        return newsRepository.countAllByTypeIsLike(string);
    }

    public News getNewsById(long id) {
        return newsRepository.findById(id);
    }

    public void save(News news) {
        newsRepository.save(news);
    }

    public void delete(long id) {
        newsRepository.deleteById(id);
    }

    public Page<News> findPaginated(Pageable pageable,String typeOfNews) {
        List<News> allNews;
        if(typeOfNews.equals("travel")||typeOfNews.equals("sport")||typeOfNews.equals("finance")){
            allNews=findByType(typeOfNews);
        }else {
            allNews = getAllNews();
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<News> list;
        if (allNews.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allNews.size());
            list = allNews.subList(startItem, toIndex);
        }

        return  new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allNews.size());
    }

}

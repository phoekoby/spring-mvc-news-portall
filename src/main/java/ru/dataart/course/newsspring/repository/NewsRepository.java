package ru.dataart.course.newsspring.repository;


import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import ru.dataart.course.newsspring.entity.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAll(Sort sort);

    News findById(long id);

    long count();


    long countAllByCategoryIsLike(@Param("string") String string);

    News save(News news);

    void deleteById(long id);

    List<News> findAllByCategoryIsLikeOrderByDateDesc(String string);

}

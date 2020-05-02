/*
    Copyright (C) 2018  Shazin Sadakath

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.khld.article.service;

import com.khld.article.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private  RestTemplate restTemplate;


//    public Article save(Article article) {
//        restTemplate.postForObject("http://", article);
//        return articleRepository.save(article);
//    }

    public List<Article> getArticles() {
//        /articles/{q}/{page}/{size}
        List<Article> article=null;
        try{
            article = (List<Article>)restTemplate.getForObject("http://localhost:8090/articles", List.class);
        }catch (Exception e){
            System.out.println("&&&&&"+e);
        }

        return article;
    }

//    public Optional<Article> getByLink(String link) {
//        return articleRepository.findByLink(link);
//    }
//
//    public Optional<Article> getById(String id) {
//        return articleRepository.findById(id);
//    }
//
//    public void deleteById(String id) {
//        articleRepository.deleteById(id);
//    }
//
//    public Page<Article> search(String q, Pageable pageable) {
//        return articleRepository.findByTitleContainingAndBodyContaining(q, q, pageable);
//    }
}

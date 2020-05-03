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
import com.khld.article.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private RestTemplate restTemplate;

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) {
        if (article.getId() == null) {
            article.setId(UUID.randomUUID().toString());
        }
        return articleRepository.save(article);
    }

    public Page<Article> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Optional<Article> getByLink(String link) {
        return articleRepository.findByLink(link);
    }

    public Optional<Article> getById(String id) {
        return articleRepository.findById(id);
    }

    public void deleteById(String id) {
        articleRepository.deleteById(id);











    }

    public Page<Article> search(String q, Pageable pageable) {
        return articleRepository.findByTitleContainingOrBodyContaining(q, q, pageable);
    }
}

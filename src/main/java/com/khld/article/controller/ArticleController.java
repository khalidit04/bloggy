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
package com.khld.article.controller;

import com.khld.article.exception.NotFoundException;
import com.khld.article.model.Article;
import com.khld.article.model.User;
import com.khld.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String index(Model model,
                        @RequestParam(required = false, value = "q") String q,
                        @RequestParam(required = false, value = "page") Integer page,
                        @RequestParam(required = false, value = "size") Integer size) {
        if (q == null) {
            Page<Article> articles = articleService.getAll(getPageable(page,size));
            System.out.println("restul" + articles);
            model.addAttribute("articles", articles);
        } else {
            model.addAttribute("articles", articleService.search(q, getPageable(page, size)));
        }

        return "article/index";
    }

    @GetMapping("/new")
    public String newPost() {
        return "article/create";
    }

    private String throwNotFoundException(@PathVariable String id) {
        throw new NotFoundException("Article Not Found for " + id);
    }


    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable String id, Model model) {
        Optional<Article> article = articleService.getById(id);
        if (article.isPresent()) {
            model.addAttribute("article", article.get());
        } else {
            return throwNotFoundException(id);
        }

        return "article/create";
    }

    @GetMapping("/show/{link}")
    public String getPost(@PathVariable String link, Model model) {
        Optional<Article> article = articleService.getByLink(link);

        if (article.isPresent()) {
            model.addAttribute("article", article.get());
        } else {
            throwNotFoundException(link);
        }

        return "article/show";
    }


    @PostMapping
    public String savePost(Article article, Model model) {
        if (article.getId() == null || article.getId().length() == 0) {
            User user = new User();
            user.setId(article.getTitle());
            user.setUsername("Khalid Moin");
            user.setDescription("main user description");
            article.setAuthor(user);
        } else {
            Optional<Article> optionalArticle = articleService.getById(article.getId());
            if (optionalArticle.isPresent()) {
                article.setAuthor(optionalArticle.get().getAuthor());
            }
        }
        articleService.save(article);

        return "redirect:/article/show/" + article.getLink();
    }


    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable String id, Model model) {
        articleService.deleteById(id);

        model.addAttribute("message", "Article with id " + id + " deleted successfully!");
        model.addAttribute("articles", articleService.getAll(getPageable(0,10)));
        return "article/index";
    }

    private Pageable getPageable(Integer page, Integer size) {
        if (page == null || size == null) {
            return PageRequest.of(0, 20);
        }

        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
    }

}


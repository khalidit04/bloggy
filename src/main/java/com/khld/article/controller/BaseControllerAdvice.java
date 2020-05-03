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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseControllerAdvice {


    @ExceptionHandler(NotFoundException.class)
    public String handledNotFoundException(NotFoundException e, Model model) {
        model.addAttribute("status", 400);
        model.addAttribute("exception", e);

        return "common/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("exception", e);

        return "common/error";
    }

//    @ModelAttribute
//    public void addCommonAttributes(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        if (userDetails != null) {
//            User user = userService.getByUsername(userDetails.getUsername());
//            model.addAttribute("user", user);
//        }
//    }
}

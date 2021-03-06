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
package com.khld.article.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "springboot2blog_article", type = "article")
@Data
public class Article implements Serializable {

    @Id
    @JsonProperty
    private String id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String link;

    @JsonProperty
    private String summary;

    @JsonProperty
    private String body;

    @JsonProperty
    @Field(type = FieldType.Nested)
    private User author;

    @JsonProperty
    @Field(type = FieldType.Date)
    private Date createdDate = new Date();

}

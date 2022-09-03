package com.alterra.miniproject.mininews.entities;

import com.alterra.miniproject.mininews.config.StandardFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String source;

    @NotBlank
    @Size(max = 100)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(name = "url_to_image", columnDefinition = "TEXT")
    private String urlToImage;

    @Column(name = "published_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StandardFormat.BASIC_FORMAT_OFFSET_DATETIME)
    private OffsetDateTime publishedAt;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}

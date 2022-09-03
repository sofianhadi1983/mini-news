package com.alterra.miniproject.mininews.config;

import com.alterra.miniproject.mininews.entities.Post;
import com.alterra.miniproject.mininews.repository.PostRepository;
import com.alterra.miniproject.mininews.responses.NewsApiArticle;
import com.alterra.miniproject.mininews.responses.NewsApiResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableScheduling
@EnableAsync
public class AppConfig {
    private static Logger Log = LoggerFactory.getLogger(AppConfig.class);

    @Value("${newsapi.api_key}")
    private String newsApiKey;

    @Autowired
    private PostRepository repo;


    @Async
    @Scheduled(fixedDelay = 60000)
    public void scheduleAutoPopulatePosts() {
        Log.info("Mengambil data dari NEWS API");

        ResponseEntity<NewsApiResponse> response = restTemplate().getForEntity(
                "https://newsapi.org/v2/top-headlines?country=id&apiKey={API_KEY}",
                NewsApiResponse.class, newsApiKey);

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            Log.info("Data NEWS API sukses diambil dan akan segera dimasukkan ke database");

            if (!response.getBody().getArticles().isEmpty()) {
                List<NewsApiArticle> articles = response.getBody().getArticles();

                for (NewsApiArticle article : articles) {
                    Optional<Post> post = repo.findAllByTitle(article.getTitle());
                    if (!post.isPresent()) {
                        Post newPost = modelMapper().map(article, Post.class);
                        repo.save(newPost);
                    }
                }
            }
        } else {
            Log.warn("[WARN] Data gagal diambil, coba cek koneksi Anda atau request yang dikirim!");
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

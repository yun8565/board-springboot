package boardspringboot.service;

import boardspringboot.domain.Article;
import boardspringboot.domain.type.SearchType;
import boardspringboot.dto.ArticleDto;
import boardspringboot.dto.ArticleUpdateDto;
import boardspringboot.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 검색 -> 게시글 리스트 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList(){
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");

        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 조회 -> 게시글 반환")
    @Test
    void givenId_whenSearchingArticle_thenReturnsArticle(){
        ArticleDto article = sut.searchArticle(1L);

        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보 입력 -> 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){

        given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Yun", "title", "content", "#java"));

        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID와 수정 정보 입력 -> 게시글 수정")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle(){

        given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID 입력 -> 게시글 삭제")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle(){

        willDoNothing().given(articleRepository).delete(any(Article.class));

        sut.deleteArticle(1L);

        then(articleRepository).should().delete(any(Article.class));
    }
}
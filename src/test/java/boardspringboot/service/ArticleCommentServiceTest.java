package boardspringboot.service;

import boardspringboot.domain.Article;
import boardspringboot.domain.ArticleComment;
import boardspringboot.dto.ArticleCommentDto;
import boardspringboot.repository.ArticleCommentRepository;
import boardspringboot.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID 조회 -> 해당하는 댓글 리스트 반환")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments(){
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title","content","#java"))
        );
        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);
        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력 -> 댓글 저장")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Yun", LocalDateTime.now(), "Yun", "comment"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}
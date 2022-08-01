package boardspringboot.controller;

import boardspringboot.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 인증")
@Import(SecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
     private final MockMvc mvc;

     public AuthControllerTest(@Autowired MockMvc mvc) {
         this.mvc = mvc;
     }

    @DisplayName("[view][GET] 로그인 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenLoggingIn_thenReturnsLogInView() throws Exception {
         mvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

}

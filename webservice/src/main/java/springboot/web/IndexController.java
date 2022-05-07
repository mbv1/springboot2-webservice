package springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.config.auth.LoginUser;
import springboot.config.auth.dto.SessionUser;
import springboot.service.posts.PostsService;
import springboot.web.dto.PostsListResponseDto;
import springboot.web.dto.PostsResponseDto;
import springboot.web.dto.PostsSearchCondition;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, PostsSearchCondition condition, Pageable pageable) {
      //  model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        Page<PostsListResponseDto> result = postsService.search(condition, pageable);
        model.addAttribute("posts", result);
        model.addAttribute("previous", result.getPageable().previousOrFirst().getPageNumber());
        model.addAttribute("next", result.getPageable().next().getPageNumber());

        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}

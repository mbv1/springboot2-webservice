//package springboot.domain.posts;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import springboot.web.dto.PostsSearchCondition;
//import springboot.web.dto.PostsListResponseDto;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//import static org.springframework.util.StringUtils.*;
//import static springboot.domain.posts.QPosts.*;
//
//public class PostsRepositoryImpl implements PostsRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//    public PostsRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public List<PostsListResponseDto> search(PostsSearchCondition condition) {
//        return queryFactory.select(Projections.constructor(
//                        PostsListResponseDto.class,
//                        posts.id,
//                        posts.title,
//                        posts.author,
//                        posts.modifiedDate
//                ))
//                .from(posts)
//                .where(
//                        titleCon(condition.getTitle()),
//                        contentCon(condition.getContent()),
//                        authorCon(condition.getAuthor())
//                )
//                .fetch();
//    }
//
//    @Override
//    public Page<PostsListResponseDto> search(PostsSearchCondition condition, Pageable pageable) {
//        List<PostsListResponseDto> result = queryFactory.select(Projections.constructor(
//                        PostsListResponseDto.class,
//                        posts.id,
//                        posts.title,
//                        posts.author,
//                        posts.modifiedDate
//                ))
//                .from(posts)
//                .where(
//                        titleCon(condition.getTitle()),
//                        contentCon(condition.getContent()),
//                        authorCon(condition.getAuthor())
//                )
//                .orderBy(posts.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        return new PageImpl<>(result, pageable, result.size());
//    }
//
//
//    private BooleanExpression titleCon(String title) {
//        return hasText(title) ? posts.title.contains(title) : null;
//    }
//
//    private BooleanExpression authorCon(String author) {
//        return hasText(author) ? posts.content.contains(author) : null;
//    }
//
//    private BooleanExpression contentCon(String content) {
//        return hasText(content) ? posts.content.contains(content) : null;
//    }
//
//}

package rw.techbuzz.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rw.techbuzz.domain.Post;
import rw.techbuzz.service.PostingService;

/**
 * @author NIYOMWUNGERI
 * @Date Jun 6, 2017
 */
public class TestService {

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:rw/techbuzz/config/app-context.xml");
			PostingService service = context.getBean(PostingService.class);
			for (Post post : service.allPosts()) {
				System.out.println(post.getLikes());
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}
	}
}

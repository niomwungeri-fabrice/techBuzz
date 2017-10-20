package rw.techbuzz.usecase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rw.techbuzz.domain.Post;
import rw.techbuzz.domain.PostTag;
import rw.techbuzz.domain.Rating;
import rw.techbuzz.domain.Tag;
import rw.techbuzz.domain.User;
import rw.techbuzz.service.PostingService;
import rw.techbuzz.service.UserService;

@Component
@ManagedBean
public class PostingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PostingService postingService;

	@Autowired
	private UserService userService;

	private Post post = new Post();
	private PostTag postTag = new PostTag();
	private String username;
	private List<Tag> selectedTags;
	private String likeColor;
	private Rating rating = new Rating();

	public String customIcon(Post post) {
		String cusIcon = "fa-thumbs-o-up";
		User user = userService.usernameByEmail(username);
		for (Rating rating : postingService.ratings()) {
			if (rating.getPost().getId().equals(post.getId()) && rating.getUser().getId().equals(user.getId())) {
				cusIcon = "fa-thumbs-up";
			} else {
				cusIcon = "fa-thumbs-o-up";
			}
		}
		return cusIcon;
	}

	public void like(Post post) {
		try {
			User user = userService.usernameByEmail(username);
			post.setLikes(1L);
			
			postingService.like(post);
			
			rating.setPost(post);
			rating.setUser(user);
			postingService.createRating(rating);
		} catch (Exception e) {
			System.out.println("constraint::: " + e.getMessage());
		}
	}

	public List<Tag> autoCompleteTags(String query) {
		List<Tag> selectedTags = new ArrayList<Tag>();
		List<Tag> allTags = postingService.allTags();
		for (Tag p : allTags) {
			if (p.getLabel().contains(query)) {
				selectedTags.add(p);
			}
		}
		return selectedTags;
	}

	public String convertToTime(Date date) {
		if (date == null) {
			date = new Date();
		}
		DateFormat outputFormat = new SimpleDateFormat("KK:mm a - d MMM yy");
		return outputFormat.format(date);
	}

	public void newPost() {
		try {
			User user = userService.usernameByEmail(username);
			post.setUser(user);
			if (selectedTags != null) {
				for (Tag tag : selectedTags) {
					postTag.setTag(tag);
					postTag.setPost(post);
					postingService.createPostTags(postTag);
				}
			} else {
				postingService.post(post);
			}
			post = new Post();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public PostingService getPostingService() {
		return postingService;
	}

	public void setPostingService(PostingService postingService) {
		this.postingService = postingService;
	}

	public List<Tag> getSelectedTags() {
		return selectedTags;
	}

	public void setSelectedTags(List<Tag> selectedTags) {
		this.selectedTags = selectedTags;
	}

	public List<Post> posts() {
		return postingService.allPosts();
	}

	public List<Tag> tags() {
		return postingService.allTags();
	}

	public PostTag getPostTag() {
		return postTag;
	}

	public void setPostTag(PostTag postTag) {
		this.postTag = postTag;
	}

	public String getLikeColor() {
		return likeColor;
	}

	public void setLikeColor(String likeColor) {
		this.likeColor = likeColor;
	}

}

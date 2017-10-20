package rw.techbuzz.service;

import java.util.List;

import rw.techbuzz.domain.Post;
import rw.techbuzz.domain.PostTag;
import rw.techbuzz.domain.Rating;
import rw.techbuzz.domain.Tag;

public interface PostingService {
	List<Post> allPosts();

	List<Tag> allTags();

	void createTag(Tag tag);

	void post(Post post);

	void createPostTags(PostTag postTag);

	void like(Post post);

	void disLike(Post post);

	void updateRating(Rating rating);

	void createRating(Rating rating);
	
	List<Rating> ratings();

}

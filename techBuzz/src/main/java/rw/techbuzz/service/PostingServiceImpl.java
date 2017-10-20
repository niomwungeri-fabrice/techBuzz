package rw.techbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.techbuzz.dao.PostDao;
import rw.techbuzz.dao.PostTagDao;
import rw.techbuzz.dao.RatingDao;
import rw.techbuzz.dao.TagDao;
import rw.techbuzz.domain.Post;
import rw.techbuzz.domain.PostTag;
import rw.techbuzz.domain.Rating;
import rw.techbuzz.domain.Tag;
import rw.techbuzz.exception.DataManipulationException;

@Service
public class PostingServiceImpl extends TransactionAware implements PostingService {
	@Autowired
	private PostDao postDao;

	@Autowired
	private TagDao tagDao;

	@Autowired
	private PostTagDao postTagDao;

	@Autowired
	private RatingDao ratinDao;

	@Override
	public List<Post> allPosts() {
		return postDao.findAll();
	}

	@Override
	public List<Tag> allTags() {
		return tagDao.findAll();
	}

	@Override
	public void createTag(Tag tag) {
		try {
			tagDao.save(tag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}
	}

	@Override
	public void post(Post post) {
		try {
			postDao.save(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}
	}

	@Override
	public void createPostTags(PostTag postTag) {
		try {
			postTagDao.save(postTag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}

	}

	@Override
	public void like(Post post) {
		try {
			postDao.update(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}

	}

	@Override
	public void disLike(Post post) {
		try {
			postDao.update(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}

	}

	@Override
	public void updateRating(Rating rating) {
		try {
			ratinDao.update(rating);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}
	}

	@Override
	public void createRating(Rating rating) {
		try {
			ratinDao.save(rating);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new DataManipulationException("already liked");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError:" + e.getMessage());
		}

	}

	@Override
	public List<Rating> ratings() {
		return ratinDao.findAll();
	}

}

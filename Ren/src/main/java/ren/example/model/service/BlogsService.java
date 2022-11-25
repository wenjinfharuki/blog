package ren.example.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.example.model.dao.BlogsDao;
import ren.example.model.entity.BlogsEntity;

@Service
public class BlogsService {
	@Autowired
	BlogsDao blogDao;

	// 内容を保存
	public void insert(String blogTitle, String message) {
		blogDao.save(new BlogsEntity(blogTitle, message));
	}

	// ブログ詳細
	public BlogsEntity selectByBlogId(Long blogId) {
		return blogDao.findByBlogId(blogId);
	}

	// ブログ一覧
	public List<BlogsEntity> selectByAll() {
		return blogDao.findAllByOrderByBlogId();
	}

	// 内容を更新
	public void update(Long blogId, String blogTitle, String message) {
		blogDao.save(new BlogsEntity(blogId, blogTitle, message));
	}

	// 削除
	public List<BlogsEntity> deleteBlog(Long blogId) {
		return blogDao.deleteByBlogId(blogId);
	}
}

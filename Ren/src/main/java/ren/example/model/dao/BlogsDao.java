package ren.example.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ren.example.model.entity.BlogsEntity;

public interface BlogsDao extends JpaRepository<BlogsEntity, Long> {
	// ブログの内容を保存
	BlogsEntity save(BlogsEntity blogsEntity);

	// blogIdを使用してDBに検索をかける
	BlogsEntity findByBlogId(Long blogId);

	// ブログテーブルのすべての情報を取得
	List<BlogsEntity> findAllByOrderByBlogId();

	// blogIdを取得して該当するブログ情報を削除する
	@Transactional
	List<BlogsEntity> deleteByBlogId(Long blogId);
}

package ren.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ren.example.model.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	// UserServiceから渡されるユーザ情報（ユーザ名、パスワード）を条件にDB検索
	List<UserEntity> findByUsernameAndPassword(String username, String password);

	// UserServiceから渡されるユーザ情報（ユーザ名）を条件にDB検索
	UserEntity findByUsername(String username);

	// UserServiceから渡されるユーザ情報を基にDBへ保存するF
	UserEntity save(UserEntity userEntity);
}

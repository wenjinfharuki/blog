package ren.example.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ren.example.WebSecurityConfig;
import ren.example.model.dao.UserDao;
import ren.example.model.entity.UserEntity;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	// ユーザの情報を保存する
	public boolean createAccount(String username, String password) {
		// UserServiceから渡されるユーザ情報（ユーザ名、パスワード）を条件にDB検索で検索する
		List<UserEntity> userList = userDao.findByUsernameAndPassword(username, password);
		// UserServiceから渡されるユーザ情報（ユーザ名、パスワード）を条件にDB検索で検索した結果
		// なかった場合には、保存
		if (userList.isEmpty()) {
			userDao.save(new UserEntity(username, password));
			WebSecurityConfig.addUser(username, password);
			return true;
		} else {
			return false;
		}
	}


	// ユーザの一覧を取得する
	public List<UserEntity> getAllAccounts() {
		return userDao.findAll();
	}

	// idを見つける
	// コントローラーでわたってきたusernameを基にしてDBを検索
	public UserEntity selectByUsername(String username) {
		return userDao.findByUsername(username);
	}
}

package dao;

import bean.User;

import java.util.List;

/**
 * @author sairo
 * @since 2018/10/1 15:32
 */
public interface UserDao {

    // 增加
    void save(User u);

    // 删除
    void delete(Integer id);

    // 更改
    void update(User u);

    // 查找-通过ID
    User getByID(Integer id);

    // 统计
    int totalCount();

    // 获取所有用户
    List<User> getAll();
}

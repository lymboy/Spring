package dao.impl;

import bean.User;
import dao.UserDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author sairo
 * @since 2018/10/1 15:38
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    @Override
    public void save(User u) {
        String sql = "INSERT INTO `t_user` VALUES(NULL, ?)";
        this.getJdbcTemplate().update(sql, u.getName());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM `t_user` WHERE `id` = ?";
        this.getJdbcTemplate().update(sql, id);
    }

    @Override
    public void update(User u) {
        String sql = "UPDATE `t_user` SET `name` = ? WHERE `id` = ?";
        this.getJdbcTemplate().update(sql, u.getName(), u.getId());
    }

    @Override
    public User getByID(Integer id) {
        String sql = "SELECT * FROM t_user WHERE id = ?";
         User user = this.getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                return u;
            }
        }, id);
        return user;
    }

    @Override
    public int totalCount() {
        String sql = "SELECT COUNT(*) FROM t_user";
        Integer count = this.getJdbcTemplate().update(sql);
        return count;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM t_user";
        List<User> list = (List<User>) this.getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                return u;
            }
        });
        return list;
    }
}

package service.impl;

import service.UserService;

/**
 * @author sairo
 * @since 2018/9/30 17:38
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("Save User!!!");
    }

    @Override
    public void delete() {
        System.out.println("Delete User!!!");
    }

    @Override
    public void update() {
        System.out.println("Update User!!!");
    }

    @Override
    public void find() {
        System.out.println("Find User!!!");
    }
}

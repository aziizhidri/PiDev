/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

public interface IService<User> {
    public void add(User u);

    public User getById(int id);

    public List<User> getAll();

    public boolean update(User u);

    public boolean delete(User u);

}

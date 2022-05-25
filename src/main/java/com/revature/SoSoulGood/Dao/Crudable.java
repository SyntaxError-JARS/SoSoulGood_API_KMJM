package com.revature.SoSoulGood.Dao;

import java.io.IOException;
import java.util.ArrayList;

public interface Crudable <T> {

    T create(T newObject);

    ArrayList<T> findAll() throws IOException;

    T findByID(String id);

    public boolean update(T updatedObj);

    public boolean delete(String id);

}

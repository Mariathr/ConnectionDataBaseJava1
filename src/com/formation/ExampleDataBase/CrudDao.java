package com.formation.ExampleDataBase;

import java.util.List;

public interface CrudDao<ID,T> {

    Long create(T object);

    T findById(ID id);

    List<T> findAll();

    Boolean update(T object);

    Boolean delete(ID id);

}

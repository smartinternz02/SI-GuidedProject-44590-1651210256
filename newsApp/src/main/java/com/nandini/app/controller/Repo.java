package com.nandini.app.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nandini.app.controller.Input;

@Repository
public interface Repo extends CrudRepository<Input, Long>{

}

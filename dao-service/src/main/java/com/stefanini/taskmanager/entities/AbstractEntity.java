package com.stefanini.taskmanager.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	
	public void setId(Long id) {
        this.id = id;
    }

	public Long getId() {
	    return id;
	}
}

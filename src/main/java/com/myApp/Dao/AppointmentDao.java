package com.myApp.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myApp.domain.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment,Long>{
 
	List<Appointment> findAll();
}

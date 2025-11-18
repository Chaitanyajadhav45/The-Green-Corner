package com.example.The_Green_Corner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.The_Green_Corner.entity.Equipment;
import com.example.The_Green_Corner.repository.EquipmentRepository;

@Service
public class EquipmentService {
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	public Equipment addEquip(Equipment equipment) {
		return equipmentRepository.save(equipment);
	}
	

}

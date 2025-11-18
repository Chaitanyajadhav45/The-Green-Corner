package com.example.The_Green_Corner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.The_Green_Corner.entity.Equipment;
import com.example.The_Green_Corner.service.EquipmentService;

@RestController
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService ;
	
	@PostMapping("/equipment")
	public ResponseEntity<Equipment> addEquip(@RequestBody Equipment equipment)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.addEquip(equipment));
	}

}

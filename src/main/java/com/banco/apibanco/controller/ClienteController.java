package com.banco.apibanco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banco.apibanco.model.Cliente;
import com.banco.apibanco.service.ClienteService;

@Controller
@RestController
@RequestMapping("cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method=RequestMethod.POST, path="salvar")
	public ResponseEntity<?> salvar(@RequestBody Cliente user){
		Cliente cliente = clienteService.salvar(user);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="listar")
	public ResponseEntity<?> listar(){
		List<Cliente> clientes = clienteService.listar();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
}

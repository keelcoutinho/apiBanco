package com.banco.apibanco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.apibanco.model.Conta;
import com.banco.apibanco.repository.ContaRepository;
import com.banco.apibanco.service.ContaService;

@Controller
@RestController
@RequestMapping("conta")
public class ContaController {
	@Autowired
	private ContaService contaService;
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){		
		List<Conta> contas = contaService.listar(); 		
		return new ResponseEntity<>(contas, HttpStatus.OK);   
	}
	
	@GetMapping("/buscarConta/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){			  
		if (contaRepository.existsById(id)) {
			Conta conta = contaService.buscarConta(id) ;		
			return new ResponseEntity<>(conta.getCliente(),HttpStatus.OK); 
		}else {				
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping("/saldo/{id}")
	public ResponseEntity<?> saldo( @PathVariable Long id){		
		Conta conta = contaService.saldo(id);		
		return new ResponseEntity<>(conta.getSaldo(), HttpStatus.OK); 
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar( @RequestBody Conta conta ){
		
		
		Conta contas = this.contaService.salvar(conta);
		return new ResponseEntity<>(contas, HttpStatus.OK);
	}
	
	@PutMapping("/depositar/{valor}/{id}")
	public ResponseEntity<?> depositar(@PathVariable double valor, @PathVariable Long id){
		
		this.contaService.depositar(valor, id);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@PutMapping("/transferir/{valor}/{idConta1}/{idConta2}")
	public ResponseEntity<?> transferir(@PathVariable double valor, @PathVariable Long idConta1, @PathVariable Long idConta2){
		
		this.contaService.transaferir(valor, idConta1, idConta2);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@PutMapping("/sacar/{valor}/{id}")
	public ResponseEntity<?> sacar(@PathVariable double valor, @PathVariable Long id){
		this.contaService.sacar(valor, id);
		return new ResponseEntity<>( HttpStatus.OK); 
	}
	
	@DeleteMapping("deletarConta/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		if (!contaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		contaService.deletarConta(id);		
		return ResponseEntity.noContent().build();
	}
}
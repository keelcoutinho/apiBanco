package com.banco.apibanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.apibanco.model.Conta;
import com.banco.apibanco.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository contaRepository;
	
	public Conta salvar(Conta conta) {
		return contaRepository.save(conta);
	}
	
	public void depositar( double valor, Long id) {
		contaRepository.receberSaldo(valor, id);
	}
	
	public void transaferir( double valor, Long idConta1, Long idConta2) {
		contaRepository.removerSaldo(valor, idConta1);
		contaRepository.receberSaldo(valor, idConta2);
	}
	
	public void sacar( double valor, Long id) {
		contaRepository.removerSaldo(valor, id);
	}
	
	public Conta saldo(Long id) {
		return contaRepository.findByIdConta(id);
	}
	
	public List<Conta> listar() {
		return contaRepository.findAll();
	}
	public Conta buscarConta(Long id) {
		return contaRepository.findByIdConta(id);
	}
	public void deletarConta(Long id) {
		contaRepository.deleteById(id);
	}
}

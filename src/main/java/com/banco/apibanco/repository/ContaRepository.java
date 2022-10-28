package com.banco.apibanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banco.apibanco.model.Conta;

@Repository
@Transactional(readOnly = false)
public interface ContaRepository extends JpaRepository<Conta, Long> {
	

	Conta findByIdConta(Long id);
	
	@Modifying
	@Query("update Conta conta set conta.saldo = conta.saldo + ?1 where conta.idConta = ?2")
	void receberSaldo(double  valor, Long id);
		
	@Modifying
	@Query("update Conta conta set conta.saldo = conta.saldo - ?1 where conta.idConta = ?2")
	void removerSaldo(double  valor, Long id);	
	
	
}

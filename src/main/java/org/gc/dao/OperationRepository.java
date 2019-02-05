package org.gc.dao;

import org.gc.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("select  o from Operation  o where o.compte.codeCompte=:x order by o.dateOperation desc")
    public Page<Operation> listOperation(String codeCpte, Pageable pageable);
}

package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>{

    Seller findByUsername(String username);
    Seller findBySellerId(long sellerId);
    Seller findByEmail(String email);
    Seller save(Seller seller);

    Seller findByEmailAndPassword(String email,String password);
    Seller findByUsernameAndPassword(String username,String password);
    Seller findBySellerId(Long sellerId);
    //////////////////////////////////////////////////////////
    List<Seller> findAllByApplyState(Integer applyState);
    void delete(Seller seller);
    //////////////////////////////////////////////////////
}

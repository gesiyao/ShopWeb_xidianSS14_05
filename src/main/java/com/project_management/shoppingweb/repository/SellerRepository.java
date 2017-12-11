package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>{
    //注册信息_商家
    //changeinf
    Seller save(Seller seller);
    //loginByEmail
    Seller findByEmailAndPassword(String email,String password);
    Seller findByUsernameAndPassword(String username,String password);
    //getSellerInf
    Seller findBySellerId(Long sellerId);
}
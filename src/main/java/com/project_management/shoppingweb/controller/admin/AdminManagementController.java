package com.project_management.shoppingweb.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_management.shoppingweb.domain.Advertisement;
import com.project_management.shoppingweb.domain.Price;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.repository.AdvertisementRepository;
import com.project_management.shoppingweb.repository.PriceRepository;
import com.project_management.shoppingweb.repository.SellerRepository;
import com.project_management.shoppingweb.service.AdvertisementService;
import com.project_management.shoppingweb.service.PriceService;
import com.project_management.shoppingweb.service.SellerService;

@Controller
@RequestMapping("/admin")
public class AdminManagementController {
	@Autowired
	private PriceService priceService;
	private SellerService sellerService;
	private AdvertisementService advertisementService;
	
	@Resource
	private PriceRepository priceRepository;
	private SellerRepository sellerRepository;
	private AdvertisementRepository advertisementRepository;
	
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public String updatePrice(HttpServletRequest request,Model model) {
		Price price = new Price();
		price.setAdminId(Long.parseLong(request.getParameter("adminId")));
		price.setAdvertisementHighPrice(Double.parseDouble(request.getParameter("advertisementHighPrice")));
		price.setAdvertisementLowPrice(Double.parseDouble(request.getParameter("advertisementLowPrice")));
		price.setProductRate(Double.parseDouble(request.getParameter("productRate")));
		priceService.updatePrice(price);
		
		model.addAttribute("price", price);
		return "";//返回页面 -- 
	}
	
	@PostMapping("/manageShopApply")
	public String agreeApply(@RequestParam("sellerId") Long sellerId) {
		Seller seller = sellerService.findById(sellerId);
		seller.setApplyStatus(1);
		sellerRepository.save(seller);
		return "/manageShopApply";
	}
	
	@PostMapping("/manageAdvertisement")
	public String showAdvertisement(@RequestParam("advertisementId") Long advertisementId) {
		Advertisement advertisement = advertisementService.findById(advertisementId);
		advertisement.setStatus(1);
		advertisementRepository.save(advertisement);
		return "/manageAdvertisement";
	}
}

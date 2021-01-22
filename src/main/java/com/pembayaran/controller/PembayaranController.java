package com.pembayaran.controller;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.ui.ModelMap;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.bind.support.SessionStatus;
	import org.springframework.web.client.HttpStatusCodeException;
	import org.springframework.web.servlet.ModelAndView;
	import org.springframework.web.servlet.view.RedirectView;

import com.pembayaran.api.PembayaranApi;
import com.pembayaran.model.Pembayaran;

	
	@RestController
	@RequestMapping("pembayaran")
	public class PembayaranController {
		
		@Autowired PembayaranApi pembayaranapi;
			
		@GetMapping("")
		public ModelAndView index() {
			ModelAndView mv = new ModelAndView("home");
			mv.addObject("listPembayaran", pembayaranapi.getAllPembayaran());
			System.out.println("get in");
			return mv;
		}

		    
	    @GetMapping("/hapus/{id}")
	    public RedirectView hapusPembayaran(@PathVariable("id") int id) {
	    	System.out.println("into the delete");
	    	pembayaranapi.deletePembayaran(id);
	        return new RedirectView("/pembayaran/");
	    }
	    
	    @GetMapping("/form/{id}")
	    public ModelAndView tampilFormedit(@PathVariable(name = "id") int id, ModelMap modelMap) {
	        System.out.println(pembayaranapi.getPembayaranById(id).getId_pembayaran());
	        
	        ModelAndView mv = new ModelAndView("form");
	       
	        mv.addObject("pembayaran", pembayaranapi.getPembayaranById(id));
	    
	        return mv;
	    }
	    
	    @GetMapping("/form")
	    public void tampilFormTambah(ModelMap modelMap) {
	    	Pembayaran pembayaran =  new Pembayaran();
	        modelMap.addAttribute("pembayaran", pembayaran);
	        
	        pembayaranapi.updatePembayaran(pembayaran);
	    }
	    
	    @PostMapping("/form")
	    public RedirectView editMahasiswa(@ModelAttribute Pembayaran pembayaran, BindingResult errors, SessionStatus status) {
	        try {
	        	pembayaranapi.updatePembayaran(pembayaran);
	            status.setComplete();
	            return new RedirectView("/pembayaran/");
	        } catch (HttpStatusCodeException e) {
	            ResponseEntity<String> response = ResponseEntity.status(e.getStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
	            errors.reject("error.object", response.getBody());
	        }
	        return new RedirectView("/pembayaran/form");
	    }
	    
	    @GetMapping("/formAdd")
	    public ModelAndView tampilFormTambah(){

	    	Pembayaran pembayaran= new Pembayaran();
	          ModelAndView modelAndView = new ModelAndView("formAdd");
	          modelAndView.addObject("pembayaran", pembayaran);
	          modelAndView.setViewName("formAdd");
	        return modelAndView;
	    }
	    
	    @PostMapping("/formAdd")
	    public RedirectView addPembayaran(@ModelAttribute("formAdd") Pembayaran pembayaran){

	    	pembayaranapi.addPembayaran(pembayaran);
	        
	        return new RedirectView("/pembayaran/");
	    }


	}

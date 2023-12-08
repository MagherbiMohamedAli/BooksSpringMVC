package tn.essat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.essat.dao.ICategorie;
import tn.essat.dao.ILivre;
import tn.essat.model.Categorie;
import tn.essat.model.Livre;

@Controller
public class AppJpa {
	@Autowired
	ICategorie dao_c;
	@Autowired
	ILivre dao_l;
	
	@GetMapping("/")
	public String f1(Model m) {
		List<Categorie> liste= dao_c.findAll();
		m.addAttribute("cats", liste);
		return "page1";
	}
	@GetMapping("/livres/{id}")
	public String f2(Model m, @PathVariable("id") int id) {
		
		List<Livre> liste= dao_l.getAllLivresByCatID(id);
		m.addAttribute("liv", liste);
		Categorie cat = dao_c.findOne(id);
		m.addAttribute("c", cat);
		return "livres";
	}
	@GetMapping("/addLivre")
	public String f3(Model m) {
		Livre lv= new Livre();
		m.addAttribute("lv", lv);
		List<Categorie> cats= dao_c.findAll();
		m.addAttribute("cats",cats);
		return "page3";
	}
	@PostMapping("/save")
	public String f4(Model m,@ModelAttribute("lv") Livre lv) {
		dao_l.save(lv);
		return "redirect:/livres/"+lv.getCat().getId();
	}
	@GetMapping("/delete/{id}")
	public String f5(Model m, @PathVariable("id") int x) {
		int id = dao_l.findOne(x).getCat().getId();
		dao_l.delete(x);
		return "redirect:/livres/"+id;
	}
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String f6(Model m, @RequestParam(value="error", required = false) String error) {
			String msg = null;
			if (error != null) { 
				msg = "SVP verifier vos parametres de connexion!";
				}
			m.addAttribute("errmsg", msg);
			return "login";
			}

}

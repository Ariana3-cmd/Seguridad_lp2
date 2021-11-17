package uc.us_security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uc.us_security.entity.Usuario;
import uc.us_security.service.UusuarioService;

@RestController
@RequestMapping("/api/uc")
public class HomeController {

	@GetMapping
	public String mensaje() {
		return "Este es una prueba";
	}
	
	@Autowired
	private UusuarioService us;

	@PostMapping("/create")
	public ResponseEntity<Usuario> save(@RequestBody Usuario u){
		try {
			Usuario po = us.create(new Usuario(u.getUsername(), u.getPassword(), u.getEstado()));
			return new ResponseEntity<>(po, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> geUs(){
		try {
			List<Usuario> list = new ArrayList<>();
			list = us.readall();
			if(list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable("id") int id){
		Usuario post = us.read(id);
			if(post.getId()>0) {
				return new ResponseEntity<>(post, HttpStatus.OK);
			}else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
		try {
			us.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Usuario> update(@RequestBody Usuario post, @PathVariable("id") int id){
		try {
			Usuario pp = us.read(id);
			if(pp.getId()>0) {
				pp.setUsername(post.getUsername());
				pp.setPassword(post.getPassword());
				pp.setEstado(post.getEstado());
				return new ResponseEntity<>(us.create(pp),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

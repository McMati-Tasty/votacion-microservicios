package com.microservicio.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservicio.usuarios.Entidades.User;
import com.microservicio.usuarios.enums.Roles;
import com.microservicio.usuarios.repository.UserRepository;

import com.microservicio.usuarios.Entidades.OpcionAdmin;
import com.microservicio.usuarios.repository.OpcionAdminRepository;

@SpringBootApplication
public class UsuariosApplication implements CommandLineRunner  {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OpcionAdminRepository opcionAdminRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;
	

	
	public static void main(String[] args) {
		
		SpringApplication.run(UsuariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User admin= new User("admin@web.com", passwordEncoder.encode("admin1234"), true, true, true, Roles.ADMIN);
	
		
		User user= new User("user@web.com", passwordEncoder.encode("user1234") , true, true, true, Roles.USER);
		
		repository.save(admin);
		repository.save(user);
	
		

	    // Opciones admin
		OpcionAdmin op1 = new OpcionAdmin();
		op1.setTitulo("Candidatos");
		op1.setDescripcion("Gestionar candidatos");
		op1.setImagenUrl("https://fotos.perfil.com/2019/05/22/trim/987/555/elecciones-urna-voto-g20180522-707903.jpg");
		op1.setEnlaceUrl("");

		OpcionAdmin op2 = new OpcionAdmin();
		op2.setTitulo("Votos");
		op2.setDescripcion("Resumen de votos");
		op2.setImagenUrl("https://media.istockphoto.com/id/1371167422/es/vector/concepto-de-contrataci%C3%B3n-de-personal.jpg?s=612x612&w=0&k=20&c=HjNMxrX9fakc9AVi9X3Z6ok-T3tGLaU85YNMhfJxtb8=");
		op2.setEnlaceUrl("");

		OpcionAdmin op3 = new OpcionAdmin();
		op3.setTitulo("Ciudadanos");
		op3.setDescripcion("Listado de ciudadanos");
		op3.setImagenUrl("https://conocimiento.blob.core.windows.net/conocimiento/2022/Contables/ContabilidadBancos/CasosPracticos/CP_Usuarios_y_perfiles/drex_usuarios_y_perfiles_custom.png");
		op3.setEnlaceUrl("");

		opcionAdminRepository.save(op1);
		opcionAdminRepository.save(op2);
		opcionAdminRepository.save(op3);
		
		
		
		
	}

}
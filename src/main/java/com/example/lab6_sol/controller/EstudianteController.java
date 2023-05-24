package com.example.lab6_sol.controller;

import com.example.lab6_sol.entity.Usuario;
import com.example.lab6_sol.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listaUsuarios(Model model){
        List<Usuario> estudiantes = usuarioRepository.findByRolid(5);
        model.addAttribute("estudiantes", estudiantes);
        return "lista_usuarios";
    }

    @GetMapping("/crearEstudiante")
    public String crearEstudiante(Model model, @ModelAttribute("estudiante") @Valid Usuario estudiante){
        return "form";
    }

    @GetMapping("/editarEstudiante")
    public String editarEstudiante(Model model, @ModelAttribute("estudiante") Usuario estudiante,
                                   @RequestParam("id") int id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            estudiante = optionalUsuario.get();
            model.addAttribute("estudiante",estudiante);
        }
        return "form";
    }

    @PostMapping("/save")
    public String guardarEstudiante(@ModelAttribute("estudiante") @Valid Usuario estudiante, BindingResult bindingResult, RedirectAttributes attr){

        if(bindingResult.hasErrors()){
            return "form";
        } else {
            estudiante.setActivo(true);
            estudiante.setRolid(5);
            attr.addFlashAttribute("msg","Estudiante " + (estudiante.getId() == 0 ? "creado": "actualizado") + " exitosamente");
            usuarioRepository.save(estudiante);
            return "redirect:/estudiante/lista";
        }
    }

    /*@GetMapping("/cursos")
    public String verCursos(Model model){
        return
    }*/

}

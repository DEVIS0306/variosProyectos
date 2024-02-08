package com.example.myspringcrud.controller;

import com.example.myspringcrud.model.Persona;
import com.example.myspringcrud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("personas", personaRepository.findAll());
        return "index";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("persona", new Persona());
        return "form";
    }

    @PostMapping("/crear")
    public String crearPersona(@ModelAttribute Persona persona) {
        personaRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + id));
        model.addAttribute("persona", persona);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarConfirmacionEliminar(@PathVariable Long id, Model model) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + id));
        model.addAttribute("persona", persona);
        return "confirmarEliminar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaRepository.deleteById(id);
        return "redirect:/";
    }
}

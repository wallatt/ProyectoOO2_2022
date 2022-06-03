package com.unla.grupo13.TrabajoPractico.controllers;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.EspacioModel;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class EspacioController {

    @Autowired
    @Qualifier("espacioService")
    private IEspacioService espacioService;

    @Autowired
    @Qualifier("aulaService")
    private IAulaService aulaService;

    @GetMapping("/espacios/nuevo")
    public ModelAndView crearEspacio() {

        List<Aula> listAulas = aulaService.getAll();
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIO_NUEVO);
        mAV.addObject("listAulas", listAulas);
        mAV.addObject("espacioModel", new EspacioModel());

        return mAV;

    }

    @PostMapping("/crearespacio")
    public RedirectView nuevoPedido(@ModelAttribute("espacioModel") EspacioModel espacioModel) throws Exception {

        espacioService.generarEspacioMes(espacioModel.getFechaInicio(), espacioModel.getFechaFinalizacion(), espacioModel.getTurno(),
                espacioModel.getAula());
        return new RedirectView(ViewRouteHelper.ESPACIO_NUEVO);
    }

    @GetMapping("/espacio/okEspacio")
    public ModelAndView verPedidos() {

        ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIO_OK);
        return mAV;
    }

    /*@GetMapping("/infoPedidos")
    public ModelAndView verEspacios(Model model, String tipo, boolean proyector, String pizarron, int cantEstudiantes, char turno){
        if(tipo.equalsIgnoreCase("Laboratorio")){
            List<Laboratorio> labos = aulaService.traerLabos(cantEstudiantes, turno);
        }

        //model.
    }*/
}
package br.com.ebix.monitoramento.controllers;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebix.monitoramento.model.Evento;
import br.com.ebix.monitoramento.repository.EventoRepository;
import br.com.ebix.monitoramento.service.EventoService;

@Named
@ViewScoped
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private EventoService eventoService;

	private Evento evento = new Evento();

	private List<Evento> eventos;

	private boolean modoEdicao = false;

	private boolean modoValidar = false;

	// METODO PARA CONTRUIR APOS TODOS OBJETOS ACONTECER
	@PostConstruct
	public void init() {
		eventos = eventoRepository.buscarTodos();
	}

	// METODO DE SALVAR UM OBJETO NA TABELA
	public void salvar() throws Exception {	
				
		Date date = new Date();
		String url = evento.getUrl();
		evento.setNmStatus(EventoService.getStatus(url));
		evento.setDataHora(date);
		eventoRepository.save(evento); // COLOCAMOS NO BANCO
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro inserido"));
		if (!modoEdicao)
			eventos.add(evento); // COLOCAMOS NA LISTA
		evento = new Evento();
		modoEdicao = false;
	}

	// METODO DE EXCLUIR
	public void excluir(Evento evento) {
		eventoRepository.delete(evento);// REMOVI DO BANCO
		eventos.remove(evento);// REMOVI DA LISTA
	}

	// METODO EDITAR
	public void editar(Evento evento) {
		setEvento(evento);
		modoEdicao = true;
	}

	// METODO CANCELAR
	public void cancelar() {
		evento = new Evento();
		modoEdicao = false;
	}

	// METODO VALIDAR URL
	public void validarUrl(Evento evento) throws Exception {
		Date date = new Date();
		String url = evento.getUrl();
		evento.setNmStatus(EventoService.getStatus(url));
		evento.setDataHora(date);
		eventoRepository.save(evento);
	}
	
	//METODO PARA RESTAURA INFORMACAO NA TELA
	public void refresh() {  
        FacesContext context = FacesContext.getCurrentInstance();  
        Application application = context.getApplication();  
        ViewHandler viewHandler = application.getViewHandler();  
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());  
        context.setViewRoot(viewRoot);  
        context.renderResponse();  
    } 
	
	// METODO LISTAR TUDO
	public void listar() throws Exception {
		eventos = eventoRepository.buscarTodos();
		
		for (int i = 0; i < eventos.size(); i++) {
			Evento ev = eventos.get(i);
			Date date = new Date();
				
			evento.setNmStatus(eventoService.getStatus(ev.getUrl()));
			evento.setUrl(ev.getUrl());
			evento.setNmEvento(ev.getNmEvento());
			evento.setDataHora(date);
			evento.setId(ev.getId());
			evento.setNmEmail(ev.getNmEmail());
			evento.setTipo(ev.getTipo());
			eventoRepository.save(evento);		
			
		}
		this.refresh();
	}

	// GET E SET
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public boolean isModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

	public boolean isModoValidar() {
		return modoValidar;
	}

	public void setModoValidar(boolean modoValidar) {
		this.modoValidar = modoValidar;
	}

}

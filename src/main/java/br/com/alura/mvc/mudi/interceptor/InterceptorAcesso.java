package br.com.alura.mvc.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Acesso;
import br.com.alura.mvc.mudi.repository.AcessoRepository;

public class InterceptorAcesso implements HandlerInterceptor {
	@Autowired
	AcessoRepository acessoRepository;
	private static List<Acesso> acessos = new ArrayList<Acesso>(); 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Acesso acesso = new Acesso();
		acesso.setPath(request.getRequestURI());
		acesso.setData(LocalDateTime.now());
		request.setAttribute("acesso", acesso);
		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));
		if(acessoRepository!= null) {
			acessoRepository.save(acesso);
		} else 	acessos.add(acesso);
		
	}

	
	

}

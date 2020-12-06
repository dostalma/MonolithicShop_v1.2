package cz.dostalma.monolithicshop.configuration;

import cz.dostalma.monolithicshop.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Override
	public void onStartup(final ServletContext sc) throws ServletException {

		AnnotationConfigWebApplicationContext root =
				new AnnotationConfigWebApplicationContext();

		root.scan("cz.dostalma.monolithicshop");
		sc.addListener(new ContextLoaderListener(root));

		ServletRegistration.Dynamic appServlet =
				sc.addServlet("dispatcher", new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");
	}

}

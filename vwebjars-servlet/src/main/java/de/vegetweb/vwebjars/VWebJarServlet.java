package de.vegetweb.vwebjars;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.thirdparty.guava.common.io.ByteStreams;

@WebServlet(urlPatterns = "/vwebjars/*")
public class VWebJarServlet extends HttpServlet {

	private static final long serialVersionUID = 1022828878665573318L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		URL resourceURL = getServletContext().getResource(request.getRequestURI());
		response.setContentType("application/javascript");
		InputStream resourceStream = resourceURL.openStream();
		OutputStream output = response.getOutputStream();
		ByteStreams.copy(resourceStream, output);
		output.flush();
	}
}

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

import org.webjars.WebJarAssetLocator;

import com.google.gwt.thirdparty.guava.common.io.ByteStreams;

@WebServlet(urlPatterns = "/webjars/*")
public class VWebJarServlet extends HttpServlet {

	private static final long serialVersionUID = 1022828878665573318L;

	private WebJarAssetLocator webJarAssetLocator = new WebJarAssetLocator();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// remove the "/webjars/" prefix
		String webjarsFileName = request.getRequestURI().substring(9);
		String url;
		if (webjarsFileName.contains("/")) {
			String webjar = webjarsFileName.substring(0, webjarsFileName.indexOf("/"));
			url = webJarAssetLocator.getFullPath(webjar, webjarsFileName.substring(webjarsFileName.indexOf("/")));
		} else {
			url = webJarAssetLocator.getFullPath(webjarsFileName);
		}
		// remove the "META-INF/resources" prefix
		URL resourceURL = getServletContext().getResource(url.substring(18));

		response.setContentType("application/javascript");
		InputStream resourceStream = resourceURL.openStream();
		OutputStream output = response.getOutputStream();
		ByteStreams.copy(resourceStream, output);
		output.flush();
	}
}

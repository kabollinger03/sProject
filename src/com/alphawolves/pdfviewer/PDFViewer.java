package com.alphawolves.pdfviewer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class PDFViewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		// go to "Home" of respective operating system ("This PC" for Windows and "Macintosh HD" for Mac)
		String osHome = System.getProperty("user.home");

		// Name of PDF goes here
		String pdfFileName = "test.pdf";
		
		/*
		 * Use the following code if you want to read from Eclipse file hierarchy instead.
		 * 
		 * String contextPath = getServletContext().getRealPath(File.separator);
		 * File pdfFile = new File(contextPath + File.separator + pdfFileName);
		*/
		
		// File.separator -> Uses appropriate slash (backslash, forward-slash) for respective operating system
		File pdfFile = new File(osHome + File.separator + "Downloads" + File.separator + pdfFileName); 
		System.out.println(pdfFile.toString());

		response.setContentType("application/pdf"); // "This is a pdf application"
		response.addHeader("Content-Disposition","inline;filename=" + pdfFileName); // "Open this PDF application in the browser. Do not download it"
		response.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream = new FileInputStream(pdfFile);
		OutputStream responseOutputStream = response.getOutputStream();
		int bytes;
		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
		}
		
		fileInputStream.close();

	}

}

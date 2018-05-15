package a.b.c;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIRECTORY = "MyDatabase";
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//Alert messages
		PrintWriter writer = response.getWriter();
		//For html tags
		response.setContentType("text/html");
		
		// checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request))
        {
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }
         
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // constructs the directory path to store upload file
        //and set local path = project running path + MyDatabase
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        //MyDatabase folder is there file save that location if not create MyDatabase then save
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
         
        try {
            // parses the request's content to extract file data
            List<?> formItems = upload.parseRequest(request);
            Iterator<?> iter = formItems.iterator();
             
            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                	
                	// processes only fields that are not form fields
                	if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                     
                    // saves the file on disk
                    item.write(storeFile);
                    writer.println("<center><b>Uploaded File Path is :  </b></center>" + uploadPath);
                    writer.println("<center><b>Uploaded File Name is :  </b></center>" + fileName);
                    
                }
            }
            	writer.println("<center><b>File Upload has been done Successfully!</b></center>");
            	//writer.println("File Path is : " + filePath);
        }
        catch (Exception ex) {
        	writer.println("<center><i>There was an error: </i></center>" + ex.getMessage());
        }
	}

}

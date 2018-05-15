<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body>
    <center>
    <!-- Must need to add, this for upload file enctype="multipart/form-data" -->
    	<p>Upload any file extension is not problem!!!</p>
        <form method="post" action="FileUploadServlet" enctype="multipart/form-data">
            Select file to upload: <input type="file" name="UploadFile" />
            <br/><br/>
            <input type="submit" value="Upload" />
        </form>
    </center>
</body>
</html>
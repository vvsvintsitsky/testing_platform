package wsvintsitsky.testing_platform.webapp.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ImageUploadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Part imageFile;
	private String imageId;
	
	public Part getImageFile() {
		return imageFile;
	}

	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public void upload() throws IOException {
		InputStream inputStream = imageFile.getInputStream();
		String fileName = getFileName(imageFile);
		File file = new File(fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		imageId = fileName;
		byte[] buffer = new byte[4096];
		int bytesRead = 0;
		while (true) {
			bytesRead = inputStream.read(buffer);
			if (bytesRead > 0) {
				outputStream.write(buffer, 0, bytesRead);
			} else {
				break;
			}
		}
		outputStream.close();
		inputStream.close();
	}
	
	public void doSomething() {
		
	}
	
//	public void upload(FileUploadEvent event) throws IOException {
//		UploadedFile uploadedFile = event.getFile();
//		InputStream inputStream = uploadedFile.getInputstream();
//		imageId = uploadedFile.getFileName();
//		File file = new File(imageId);
//		FileOutputStream outputStream = new FileOutputStream(file);
//		byte[] buffer = new byte[4096];
//		int bytesRead = 0;
//		while (true) {
//			bytesRead = inputStream.read(buffer);
//			if (bytesRead > 0) {
//				outputStream.write(buffer, 0, bytesRead);
//			} else {
//				break;
//			}
//		}
//		outputStream.close();
//		inputStream.close();
//	}

	private String getFileName(Part imageFile) {
		for (String cd : imageFile.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE																										// fix.
			}
		}
		return null;
	}

	public StreamedContent getLoadedImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String imageName = context.getExternalContext().getRequestParameterMap().get("imageName");
			InputStream iStream = new FileInputStream(new File(imageName));
			return new DefaultStreamedContent(iStream);
		}
	}
}

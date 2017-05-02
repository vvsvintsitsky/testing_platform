package wsvintsitsky.testing_platform.service.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.service.PDFCreationService;

@Service
public class PDFCreationServiceImpl implements PDFCreationService {
	
	private static final Logger LOGGER = LogManager.getLogger(PDFCreationService.class);
	
	@Override
	public PDDocument createPDF(Quiz quiz) throws IOException {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDFont font = PDType1Font.HELVETICA_BOLD;
		
		// Start a new content stream which will "hold" the to be created
		// content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Define a text content stream using the selected font, moving the
		// cursor and drawing the text "Hello World"
		contentStream.beginText();
		contentStream.setFont(font, 12);
//		contentStream.moveTextPositionByAmount(100, 700);
		contentStream.drawString("" + page.getArtBox().getHeight() + " " + page.getArtBox().getWidth());
		contentStream.endText();

		// Make sure that the content stream is closed:
		contentStream.close();

		// Save the results and ensure that the document is properly closed:
		try {
			document.save("Hello World.pdf");
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		LOGGER.error("aaaf");
		return null;
	}

}

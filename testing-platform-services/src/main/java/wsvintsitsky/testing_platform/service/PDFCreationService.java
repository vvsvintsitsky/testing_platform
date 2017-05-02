package wsvintsitsky.testing_platform.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;

public interface PDFCreationService {

	PDDocument createPDF(Quiz quiz) throws IOException;
}

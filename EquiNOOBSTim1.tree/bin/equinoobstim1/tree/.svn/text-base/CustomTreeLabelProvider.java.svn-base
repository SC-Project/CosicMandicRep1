package equinoobstim1.tree;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

import equinoobstim1.model.Prvenstvo;
import equinoobstim1.model.Tim;
import equinoobstim1.model.Timovi;
import equinoobstim1.model.Utakmica;
import equinoobstim1.model.Utakmice;

public class CustomTreeLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {

		Bundle bundle = Platform.getBundle("EquiNOOBSTim1.view");
		URL fileURL = bundle.getEntry("icons/small");
		File file = null;
		try {
			file = new File(FileLocator.resolve(fileURL).toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String putanjaSlike = file.getAbsolutePath();

		if (element instanceof Tim) {

			putanjaSlike = putanjaSlike + "/" + ((Tim) element).getNazivTima()
					+ ".png";
			putanjaSlike = putanjaSlike.replaceAll(" ", "_");
			Image img = new Image(null, putanjaSlike);
			return img;

		} else if (element instanceof Utakmica) {

			Image img = new Image(null, putanjaSlike + "/" + "teren.png");
			return img;

		} else if (element instanceof Utakmice) {

			Image img = new Image(null, putanjaSlike + "/"
					+ "1404420550_instant-messanger.png");
			return img;

		} else if (element instanceof String) {
			if (((String) element).toLowerCase().contains("kartoni")) {

				Image img = new Image(null, putanjaSlike + "/"
						+ "1404420546_my-documents.png");
				return img;

			} else if (((String) element).toLowerCase().contains("golovi")) {

				Image img = new Image(null, putanjaSlike + "/"
						+ "1404420550_instant-messanger.png");
				return img;

			} else if (((String) element).toLowerCase().contains("stadion")) {

				Image img = new Image(null, putanjaSlike + "/" + "stadion.png");
				return img;
			} else if (((String) element).toLowerCase().contains("datum")
					|| ((String) element).toLowerCase().contains("prvenstvo")) {

				Image img = new Image(null, putanjaSlike + "/" + "datum.png");
				return img;
			} else if (((String) element).toLowerCase().contains("uspeh")) {

				Image img = new Image(null, putanjaSlike + "/"
						+ "postignuce.png");
				return img;
			} else if (((String) element).toLowerCase().contains("ucesca")) {

				Image img = new Image(null, putanjaSlike + "/"
						+ "1404420543_file-commander.png");
				return img;
			} else if (((String) element).toLowerCase().contains(":")) {
				String nazivTemp = (String) element;
				int index = nazivTemp.indexOf(":");
				String naziv = nazivTemp.substring(0, index);
				putanjaSlike += "/" + naziv + ".png";
				putanjaSlike = putanjaSlike.replaceAll(" ", "_");
				Image img = new Image(null, putanjaSlike);
				return img;
			}
		}

		Image img = new Image(null, putanjaSlike + "/"
				+ "1404420543_file-commander.png");
		return img;

	}

	@Override
	public String getText(Object element) {

		if (element instanceof Prvenstvo) {

			return ((Prvenstvo) element).getNaziv();

		} else if (element instanceof Utakmice) {

			return "Utakmice";

		} else if (element instanceof Timovi) {

			return "Timovi";

		} else if (element instanceof Utakmica) {

			return ((Utakmica) element).getNazivDomaci() + "-"
					+ ((Utakmica) element).getNazivGosti();

		} else if (element instanceof Tim) {

			return ((Tim) element).getNazivTima();

		} else if (element instanceof String) {

			return (String) element;
		}

		return "";

	}
}

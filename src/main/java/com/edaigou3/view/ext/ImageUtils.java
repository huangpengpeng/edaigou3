package com.edaigou3.view.ext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

public class ImageUtils {

	public static String imgToBase64String(org.eclipse.swt.graphics.Image image) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageData imageData = image.getImageData();
			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[1];
			imageLoader.data[0] = imageData;
			imageLoader.save(bos, SWT.IMAGE_JPEG);
			byte[] imageBytes = bos.toByteArray();
			imageString = Base64.encodeBase64String(imageBytes);
			bos.close();
			return imageString;
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	/**
	 * base 64 string to image
	 * 
	 * @param base64String
	 * @return
	 * @throws IOException
	 */
	public static Image base64StringToImg(final String base64String)
			throws IOException {
		InputStream stream = new ByteArrayInputStream(
				Base64.decodeBase64(base64String));
		try {
			Display display = Display.getCurrent();
			ImageData data = new ImageData(stream);
			if (data.transparentPixel > 0) {
				return new Image(display, data, data.getTransparencyMask());
			}
			return new Image(display, data);
		} finally {
			stream.close();
		}
	}

}

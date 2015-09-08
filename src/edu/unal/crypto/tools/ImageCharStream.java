package edu.unal.crypto.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageCharStream {
	
	public static char[] read(String imgPath) {
		
		File imgFile = new File(imgPath);
		if (!imgFile.exists()) {
			throw new IllegalArgumentException("Image File doesn't exists");
		}
		BufferedImage image = null;
		try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (image.getType() !=BufferedImage.TYPE_BYTE_GRAY) {
			throw new IllegalArgumentException("Only GrayScale images are supported");
		}
		int w = image.getWidth();
		int h = image.getHeight();
		char[] output = new char[w*h+8];
		
		for (int i = 0; i < 4; i++) {
			output[i] += (w>>(8*i))&0xFF;
		}
		for (int i = 0; i < 4; i++) {
			output[i+4] += (h>>(8*i))&0xFF;
		}
		byte[] buff = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < buff.length; i++) {
			output[i+8] += buff[i];
		}
		return output;
	}
	
	public static Image write(char[] data) {
		
		int w = 0;
		int h = 0;
		for (int i = 3; i >= 0; i--) {
			w = (w<<8)|(data[i]&0xFF);
		}
		for (int i = 7; i >= 4; i--) {
			h = (h<<8)|(data[i]&0xFF);
		}
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				raster.setSample(j, i, 0, data[i*w+j]);
			}
		}
		return image;
	}
	
	public static void main(String args[]) {
		
		char[] code = ImageCharStream.read("/Users/eduarc/gray.bmp");
		String s = new String(code);
		
		JFrame f = new JFrame("Test Image");
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(ImageCharStream.write(code)));
		f.add(l);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

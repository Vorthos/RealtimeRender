package net.nabaal.majiir.realtimerender.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Set;

import net.nabaal.majiir.realtimerender.Coordinate;


public abstract class ImageProvider {
	
	public Color getPixelColor(Coordinate pixel, int offset) {
		Coordinate tile = pixel.zoomOut(offset);
		BufferedImage image = getImage(tile);
		if (image == null) {
			return new Color(0, 0, 0);
		}
		pixel = pixel.subCoordinate(offset);
		return new Color(image.getRGB(pixel.getX(), pixel.getY()));
	}
	
	public void setPixelColor(Coordinate pixel, Color color, int offset) {
		BufferedImage image = getImage(pixel.zoomOut(offset));
		if (image == null) {
			image = createImage(offset);
		}
		pixel = pixel.subOrigin(offset);
		Graphics2D g = image.createGraphics();
		g.setColor(color);
		g.fillRect(pixel.getX(), pixel.getY(), 1, 1);
		setImage(pixel, image);
	}
	
	public static final BufferedImage createImage(int size) {
		return createImage(1 << size, 1 << size);
	}
	
	public static final BufferedImage createImage(int width, int height) {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public abstract BufferedImage getImage(Coordinate tile);
	
	public abstract void setImage(Coordinate tile, BufferedImage image);
	
	public abstract void removeImage(Coordinate tile);
	
	public abstract Set<Coordinate> getTiles();
	
}

package edu.virginia.engine.display;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.AlphaComposite;

import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	/* Visible: True iff the display object is meant to be drawn */
	private Boolean visible;
	private int count;

	/* Alpha: defines how transparent to draw the display object */
	private Float alpha;

	/* oldAlpha: alpha value from previous frame */
	private Float oldAlpha;

	/* scaleX: double which scales the image up or down (1.0 is original size) */
	private Double scaleX;

	/* scaleY: double which scales the image up or down (1.0 is original size) */
	private Double scaleY;

	private Point position;

	private Point pivotPoint;

	private int rotation;

	private int frameCount;

	private DisplayObject parent;

	private Boolean hasPhysics;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */

	public DisplayObject() {}

	public DisplayObject(String id) {
		this.setId(id);
		this.setPosition(new Point (0, 0));
		this.setPivotPoint(new Point (0, 0));
		this.setRotation(0);
		this.setVisible(true);
		this.setAlpha(1.0f);
		this.setOldAlpha(0.0f);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.setCount(30);
		this.setHasPhysics(false);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.setPosition(new Point (0, 0));
		this.setPivotPoint(new Point (0, 0));
		this.setRotation(0);
		this.setVisible(true);
		this.setAlpha(1.0f);
		this.setOldAlpha(0.0f);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.setCount(30);
		this.setHasPhysics(false);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	/**
	 * Returns the scaled width and height of this display object
	 **/
	public int getScaledWidth(double scale) {
		if(displayImage == null) return 0;
		double width = (displayImage.getWidth() * scale);
		return (int) width;
	}

	public int getScaledHeight(double scale) {
		if(displayImage == null) return 0;
		double height = (displayImage.getHeight() * scale);
		return (int) height;
	}



	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}

	/* Setters and Getters */
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point pos) {
		this.position = pos;
	}

	public Point getPivotPoint() {
		return pivotPoint;
	}

	public void setPivotPoint(Point piv) {
		this.pivotPoint = piv;
	}

	public int getRotation() {
		return this.rotation;
	}

	public void setRotation(int rot) {
		this.rotation = rot;
	}

	public void setVisible(Boolean v) { this.visible = v; }

	public Boolean getVisible() { return visible; }

	public void setCount(int c) {this.count = c;}

	public int getCount() {return count;}

	public void setAlpha(Float a) { this.alpha = a; }

	public Float getAlpha() { return alpha; }

	public void setOldAlpha(Float a) { this.oldAlpha = a; }

	public Float getOldAlpha() { return oldAlpha; }

	public void setScaleX(Double s) { this.scaleX = s; }

	public Double getScaleX() { return scaleX; }

	public void setScaleY(Double s) { this.scaleY = s; }

	public Double getScaleY() { return scaleY; }

	public void setFrameCount(int fc) {this.frameCount = fc;}

	public int getFrameCount() {return this.frameCount;}

	public DisplayObject getParent() {return this.parent;}

	public void setParent(DisplayObject obj) {this.parent = obj;}

	public Boolean getHasPhysics() {return this.hasPhysics;}

	public void setHasPhysics(Boolean b){this.hasPhysics = b;}

	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}

	
	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			if(this.getVisible()) {
				g2d.drawImage(displayImage, 0,0, (int) (getUnscaledWidth()),
						(int) (getUnscaledHeight()),null);
			}
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {

		g2d.translate(this.position.x, this.position.y);
		g2d.rotate(Math.toRadians(this.getRotation()), this.globalToLocal(this.getPivotPoint()).x,
				this.globalToLocal(this.getPivotPoint()).y);
		g2d.scale(this.scaleX, this.scaleY);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)
				g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha * this.alpha));
	}


	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {

		g2d.setComposite(AlphaComposite.getInstance(3, this.oldAlpha));
		g2d.scale(1/this.scaleX, 1/this.scaleY);
		g2d.rotate(Math.toRadians(-this.getRotation()), this.globalToLocal(this.getPivotPoint()).x,
				this.globalToLocal(this.getPivotPoint()).y);
		g2d.translate(-this.position.x, -this.position.y);


	}



	/* Convert given point from global to Display Object's coordinates or vice versa */
	public Point localToGlobal(Point p){
		if (parent == null) {
			return p;
		}
		else {
			return new Point(this.getParent().getPosition().x + this.getParent().localToGlobal(p).x,
					this.getParent().getPosition().y + this.getParent().localToGlobal(p).y);
		}
	}

	public Point globalToLocal(Point p){
		if (parent == null)
			return p;
		else
			return new Point(this.getParent().getPosition().x - this.getParent().globalToLocal(p).x,
					this.getParent().getPosition().y - this.getParent().globalToLocal(p).y);

	}

}

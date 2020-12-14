package com.NASAforFEVO.Model;

public class Photo{
    public int id;
    public int sol;
    public Camera camera;
    public String img_src;
    public String earth_date;
	public Rover rover;
    public int getId() {
		return id;
	}
	public int getSol() {
		return sol;
	}
	public Camera getCamera() {
		return camera;
	}
	public String getImg_src() {
		return img_src;
	}
	public String getEarth_date() {
		return earth_date;
	}
	public Rover getRover() {
		return rover;
	}
	public Photo() {
	}
}

package fr.voxelwars.editor.maths;

public class Vector1f extends Vector{

	private float x;
	
	public Vector1f() {
		this(0.0f);
	}
	
	public Vector1f(float x) {
		this.x = x;
	}
	
	public Vector1f(Vector4f vec){
		this(vec.getX());
	}
	
	public Vector1f(Vector3f vec){
		this(vec.getX());
	}
	
	public Vector1f(Vector2f vec){
		this(vec.getX());
	}
	
	public Vector1f(Vector1f vec){
		this(vec.getX());
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void addX(float x){
		this.x += x;
	}
	
	public void removeX(float x){
		this.x -= x;
	}
	
	public void mulX(float x){
		this.x *= x;
	}
	
	public void divX(float x){
		this.x /= x;
	}
	
	public float length(){
		return (float) Math.sqrt(getX()*getX());
	}

	public Vector1f normalize(){
		return new Vector1f(getX()/length());
	}
	
	@Deprecated
	public Vector1f rotation(int angle) {
		return new Vector1f(-x);
	}

	public float dot(Vector1f v){
		return x * v.x;
	}
}

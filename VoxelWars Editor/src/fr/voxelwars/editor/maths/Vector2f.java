package fr.voxelwars.editor.maths;

public class Vector2f extends Vector1f{

	private float y;
	
	public Vector2f() {
		this(0.0f, 0.0f);
	}
	
	public Vector2f(float x, float y) {
		super(x);
		this.y = y;
	}
	
	public Vector2f(Vector4f vec){
		this(vec.getX(), vec.getY());
	}
	
	public Vector2f(Vector3f vec){
		this(vec.getX(), vec.getY());
	}
	
	public Vector2f(Vector2f vec){
		this(vec.getX(), vec.getY());
	}
	
	public Vector2f(Vector1f vec){
		this(vec.getX(), 0.0f);
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void addY(float y) {
		this.y += y;
	}
	
	public void removeY(float y) {
		this.y -= y;
	}
	
	public void mulY(float y){
		this.y *= y;
	}
	
	public void divY(float y){
		this.y /= y;
	}

	public float length(){
		return (float) Math.sqrt(getX()*getX()+getY()*getY());
	}
	
	public Vector2f normalize(){
		float l = length();
		return new Vector2f(getX()/l, getY()/l);
	}
	
	public Vector2f rotation(int angle){
		double r = Math.toRadians(angle);
		double cos = Math.cos(r);
		double sin = Math.sin(r);
		
		return new Vector2f((float)((getX() * cos)-(getY() * sin)), (float)((getX() * sin)+(getY() * cos)));
	}
	
	public float dot(Vector2f v){
		return (getX() * v.getX()) + (getY() * v.getY());
	}
}

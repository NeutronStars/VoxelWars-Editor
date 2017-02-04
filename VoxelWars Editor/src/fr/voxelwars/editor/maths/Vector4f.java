package fr.voxelwars.editor.maths;

public class Vector4f extends Vector3f{

	private float w;
	
	public Vector4f() {
		this(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public Vector4f(float x, float y, float z, float w) {
		super(x, y, z);
		this.w = w;
	}
	
	public Vector4f(Vector4f vec){
		this(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	
	public Vector4f(Vector3f vec){
		this(vec.getX(), vec.getY(), vec.getZ(), 0.0f);
	}
	
	public Vector4f(Vector2f vec){
		this(vec.getX(), vec.getY(), 0.0f, 0.0f);
	}
	
	public Vector4f(Vector1f vec){
		this(vec.getX(), 0.0f, 0.0f, 0.0f);
	}
	
	public float getW() {
		return w;
	}
	
	public void setW(float w) {
		this.w = w;
	}
	
	public void addW(float w) {
		this.w += w;
	}
	
	public void removeW(float w) {
		this.w -= w;
	}

	public void mulW(float w){
		this.w *= w;
	}
	
	public void divW(float w){
		this.w /= w;
	}

	public float length(){
		return (float) Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ()+getW()*getW());
	}
	
	public Vector4f normalize(){
		float l = length();
		return new Vector4f(getX()/l, getY()/l, getZ()/l, getW()/l);
	}
	
	public float dot(Vector4f v){
		return (getX() * v.getX()) + (getY() * v.getY()) + (getZ() * v.getZ()) + (getW() * v.getW());
	}
}

package fr.voxelwars.editor.maths;

public class Vector3f extends Vector2f {

	private float z;
	
	public Vector3f() {
		this(0.0f, 0.0f, 0.0f);
	}
	
	public Vector3f(float x, float y, float z) {
		super(x, y);
		this.z = z;
	}
	
	public Vector3f(Vector4f vec){
		this(vec.getX(), vec.getY(), vec.getZ());
	}
	
	public Vector3f(Vector3f vec){
		this(vec.getX(), vec.getY(), vec.getZ());
	}
	
	public Vector3f(Vector2f vec){
		this(vec.getX(), vec.getY(), 0.0f);
	}
	
	public Vector3f(Vector1f vec){
		this(vec.getX(), 0.0f, 0.0f);
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public void addZ(float z) {
		this.z += z;
	}
	
	public void removeZ(float z) {
		this.z -= z;
	}

	public void mulZ(float z){
		this.z *= z;
	}
	
	public void divZ(float z){
		this.z /= z;
	}
	
	public void mul(float m){
		mulX(m);
		mulY(m);
		mulZ(m);
	}
	
	public float length(){
		return (float) Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ());
	}
	
	public Vector3f normalize(){
		float l = length();
		return new Vector3f(getX()/l, getY()/l, getZ()/l);
	}
	
	public float dot(Vector3f v){
		return (getX() * v.getX()) + (getY() * v.getY()) + (getZ() * v.getZ());
	}
	
	public Vector3f cross(Vector3f v){
		float x = (getY() * v.getZ()) - (getZ() * v.getY());
		float y = (getZ() * v.getX()) - (getX() * v.getZ());
		float z = (getX() * v.getY()) - (getY() * v.getX());
		
		return new Vector3f(x, y, z);
	}
}

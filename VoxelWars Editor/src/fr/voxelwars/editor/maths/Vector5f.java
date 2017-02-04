package fr.voxelwars.editor.maths;

public class Vector5f extends Vector4f {

	private float p;
	
	public Vector5f() {
		this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public Vector5f(float x, float y, float z, float w, float p) {
		super(x, y, z, w);
		this.p = p;
	}
	
	public Vector5f(Vector5f vec){
		this(vec.getX(), vec.getY(), vec.getZ(), vec.getW(), vec.getP());
	}
	
	public Vector5f(Vector4f vec){
		this(vec.getX(), vec.getY(), vec.getZ(), vec.getW(), 0.0f);
	}
	
	public Vector5f(Vector3f vec){
		this(vec.getX(), vec.getY(), vec.getZ(), 0.0f, 0.0f);
	}
	
	public Vector5f(Vector2f vec){
		this(vec.getX(), vec.getY(), 0.0f, 0.0f, 0.0f);
	}
	
	public Vector5f(Vector1f vec){
		this(vec.getX(), 0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public float getP() {
		return p;
	}
	
	public void setP(float p) {
		this.p = p;
	}
	
	public void addP(float p) {
		this.p += p;
	}
	
	public void removeP(float p) {
		this.p -= p;
	}

	public void mulP(float p){
		this.p *= p;
	}
	
	public void divP(float p){
		this.p /= p;
	}

	public float length(){
		return (float) Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ()+getW()*getW()+getP()*getP());
	}
	
	public Vector5f normalize(){
		float l = length();
		return new Vector5f(getX()/l, getY()/l, getZ()/l, getW()/l, getP()/l);
	}
	
	public float dot(Vector5f v){
		return (getX() * v.getX()) + (getY() * v.getY()) + (getZ() * v.getZ()) + (getW() * v.getW()) + (getP() * v.getP());
	}
}

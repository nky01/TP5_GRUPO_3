package entidades;

public class Cliente {
	
	private int id;
	private int dni;
	private long cuil;
	private String nombre;
	private String apellido;
	
	public Cliente() {
		this.id= 0;
		this.dni = 12345678;
		this.cuil = 20333444555L;
		this.nombre = "nombre";
		this.apellido = "apellido";
	}
	
	public Cliente(int dni, long cuil, String nombre, String apellido) {
		this.id= dni;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getDni() {return dni;}
	public void setDni(int dni) {this.dni = dni;}
	public long getCuil() {return cuil;}
	public void setCuil(long cuil) {this.cuil = cuil;}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getApellido() {return apellido;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	
	
}

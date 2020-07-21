/**
 * Empleado
 */
public class Empleado {

	private Integer id;
	private String nombre;
	private String apellido;
	private Double salario;

	public Empleado(Integer id, String nombre, String apellido, Double salario) {
		this.setId(id);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setSalario(salario);
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
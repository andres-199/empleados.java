import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private Map<Integer, String> menu = new HashMap<Integer, String>();
	private Map<Integer, Empleado> empleados = new HashMap<Integer, Empleado>();
	private Scanner scanner = new Scanner(System.in);

	public Menu() {
		this.createMenu();
	}

	public void start() {
		this.showMenu();
		this.listenOptions();
	}

	private void createMenu() {
		this.menu.put(1, "Agregar empleado");
		this.menu.put(2, "Eliminar empleado por Id");
		this.menu.put(3, "Actualizar empleado");
		this.menu.put(4, "Mostrar todos los empleados");
		this.menu.put(5, "salir");
	}

	private void showMenu() {
		final Iterator iterator = this.menu.keySet().iterator();

		System.out.println();
		System.out.println("Menu");
		while (iterator.hasNext()) {
			final Integer option = (Integer) iterator.next();
			System.out.println(option + ". " + this.menu.get(option));
		}
		System.out.println();
	}

	private void listenOptions() {
		System.out.print("Digite el número de la opción deseada: ");
		final Integer optionSelected = this.scanner.nextInt();
		this.handleOption(optionSelected);
	}

	private void handleOption(final Integer optionSelected) {
		switch (optionSelected) {
			case 1:
				this.addEmpleado();
				break;

			case 2:
				this.deleteEmpleado();
				break;

			case 3:
				this.updateEmpleado();
				break;

			case 4:
				this.showEmpleados();
				break;

			default:
				this.exit();
				break;
		}
	}

	private void addEmpleado() {

		System.out.println("Digite el id: ");
		final Integer id = this.scanner.nextInt();

		if (this.empleados.containsKey(id)) {
			System.out.println("El id \"" + id + "\" ya existe.");
			this.addEmpleado();
			return;
		}

		System.out.println("Digite el nombre: ");
		final String nombre = this.scanner.next();

		System.out.println("Digite el apellido: ");
		final String apellido = this.scanner.next();

		System.out.println("Digite el salario: ");
		final Double salario = this.scanner.nextDouble();

		final Empleado empleado = new Empleado(id, nombre, apellido, salario);

		this.empleados.put(id, empleado);

		System.out.println("Empleado agregado correctamente.");
		System.out.println();

		this.start();

	}

	private void deleteEmpleado() {
		System.out.println("Digite el id del empleado a eliminar: ");
		final Integer id = this.scanner.nextInt();

		if (this.empleados.containsKey(id)) {
			this.empleados.remove(id);
			System.out.println("Empleado eliminado.");
			System.out.println();

		} else {
			System.out.println("No existe Empleado con el id \"" + id + "\"");
			this.deleteEmpleado();
			return;
		}

		System.out.println();

		this.start();
	}

	private void updateEmpleado() {
		System.out.println("Digite el id del empleado a actualizar: ");
		final Integer id = this.scanner.nextInt();

		if(this.empleados.containsKey(id)){
			final Empleado empleado = this.empleados.get(id);

			System.out.println("Nombre: ");
			final String nombre = this.scanner.next();
			empleado.setNombre(nombre);

			System.out.println("Apellido: ");
			final String apellido = this.scanner.next();
			empleado.setApellido(apellido);

			System.out.println("Salario");
			final Double salario = this.scanner.nextDouble();
			empleado.setSalario(salario);

			System.out.println("Empleado actualizado.");

		}else{
			System.out.println("No existe Empleado con el id \"" + id + "\"");
			this.updateEmpleado();
			return;
		}

		System.out.println();
		this.start();
	}

	private void showEmpleados() {
		System.out.println("Lista de Empleados");
		System.out.println(this.empleados);
		System.out.println();
		this.start();
	}

	private void exit() {
		System.out.println("Bye!");
		this.scanner.close();
	}
}
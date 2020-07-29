import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Menu {

	private Map<Integer, String> menu = new HashMap<Integer, String>();
	private Map<Integer, Empleado> empleados = new HashMap<Integer, Empleado>();
	private Scanner scanner = new Scanner(System.in);

	public Menu() {

		this.empleados.put(1, new Empleado(1, "ana", "anzola", 900000.00));
		this.empleados.put(2, new Empleado(2, "briant", "bustos", 200000.00));
		this.empleados.put(3, new Empleado(3, "camila", "Angola", 300000.00));
		this.empleados.put(4, new Empleado(4, "diana", "deaza", 710000.00));
		this.empleados.put(5, new Empleado(5, "eugenio", "orejuela", 800000.00));
		this.empleados.put(6, new Empleado(6, "senon", "anzola", 600000.00));
		this.empleados.put(7, new Empleado(7, "aldemar", "vargas", 780000.00));
		this.empleados.put(8, new Empleado(8, "carlos", "amezquita", 3000000.00));
		this.empleados.put(9, new Empleado(9, "diomedez", "de la paz", 710000.00));
		this.empleados.put(10, new Empleado(10, "enit", "arioeste", 650000.00));

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
		this.menu.put(5, "Mostrar empleado con mayor salario");
		this.menu.put(6, "Mostrar empleado con menor salario");
		this.menu.put(7, "Mostrar todos los empleados ordenados por nombre");
		this.menu.put(8, "Mostrar la suma de los salarios de todos los empleados cuyo salario es mayor a 700.000");
		this.menu.put(9, "Mostrar número total de empleados cuyo apellido comienza por la letra ‘A’ o ‘a’");
		this.menu.put(10, "Mostrar 5 primeros empleados con el mayor salario");

		this.menu.put(11, "salir");
	}

	private void showMenu() {
		final Iterator iterator = this.menu.keySet().iterator();

		System.out.println();
		System.out.println("********");
		System.out.println("* Menu *");
		System.out.println("********");
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

			case 5:
				this.showMaxSalary();
				break;

			case 6:
				this.showMinSalary();
				break;

			case 7:
				this.showEmpleadosSortedByName();
				break;

			case 8:
				this.showSalarySum();
				break;

			case 9:
				this.countEmpleados();
				break;

			case 10:
				getEmpleadosHighestSalary();
				break;

			default:
				this.exit();
				break;
		}
	}

	private void getEmpleadosHighestSalary() {
		Comparator<Empleado> salaryComparator = Comparator.comparing(Empleado::getSalario).reversed();
		Long limit = new Long(5);
		List<Empleado> empleados = Filter.sortAndLimit(this.empleados, salaryComparator, limit);
		System.out.println("******************************************************");
		System.out.println("* Lista de 5 primeros empleados con el mayor salario *");
		System.out.println("******************************************************");
		System.out.println(empleados);
		System.out.println();
		this.start();
	}

	private void countEmpleados() {
		Predicate<Empleado> firstLetterPredicate = empleado -> empleado.getApellido().toUpperCase().charAt(0) == 'A';
		Number countEmpleados = Filter.countElements(this.empleados, firstLetterPredicate);
		System.out.println();
		System.out
				.println("El número total de empleados cuyo apellido comienza por la letra ‘A’ o ‘a’ es: " + countEmpleados);
		System.out.println();
		this.start();
	}

	private void showSalarySum() {
		Predicate<Empleado> salaryPredicate = (Empleado empleado) -> empleado.getSalario() > 700000.00;
		BinaryOperator<Double> salaryReducer = (last, next) -> last + next;
		Double salarySum = Filter.propertySummary(this.empleados, salaryPredicate, Empleado::getSalario, new Double(0),
				salaryReducer);
		System.out
				.println("La suma de los salarios de todos los empleados cuyo salario es mayor a 700000 es: " + salarySum);
		System.out.println();
		this.start();
	}

	private void showEmpleadosSortedByName() {
		Comparator<Empleado> nameComparator = Comparator.comparing(Empleado::getNombre);
		List<Empleado> empleados = Filter.sort(this.empleados, nameComparator);
		System.out.println("******************************************");
		System.out.println("* Lista de empleados ordenada por nombre *");
		System.out.println("******************************************");
		System.out.println(empleados);
		System.out.println();
		this.start();
	}

	private void showMinSalary() {
		Comparator<Empleado> salaryComparator = Comparator.comparing(Empleado::getSalario);
		Empleado empleado = Filter.min(this.empleados, salaryComparator);
		System.out.println("Empleado con menor salario:\n" + empleado);
		System.out.println();
		this.start();

	}

	private void showMaxSalary() {
		Comparator<Empleado> salaryComparator = Comparator.comparing(Empleado::getSalario);
		Empleado empleado = Filter.max(this.empleados, salaryComparator);
		System.out.println("Empleado con mayor salario:\n" + empleado);
		System.out.println();
		this.start();
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

		if (this.empleados.containsKey(id)) {
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

		} else {
			System.out.println("No existe Empleado con el id \"" + id + "\"");
			this.updateEmpleado();
			return;
		}

		System.out.println();
		this.start();
	}

	private void showEmpleados() {
		System.out.println("**********************");
		System.out.println("* Lista de empleados *");
		System.out.println("**********************");
		System.out.println(this.empleados);
		System.out.println();
		this.start();
	}

	private void exit() {
		System.out.println("Bye!");
		this.scanner.close();
	}
}
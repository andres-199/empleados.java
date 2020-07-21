import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	private Map<Integer, String> menu = new HashMap<Integer, String>();
	private Map<Integer, Empleado> empleados = new HashMap<Integer, Empleado>();

	public void start() {
		this.createMenu();
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

		System.out.println("Menu");
		while (iterator.hasNext()) {
			final Integer option = (Integer) iterator.next();
			System.out.println(option + ". " + this.menu.get(option));
		}
	}

	private void listenOptions() {
		final Scanner listener = new Scanner(System.in);
		System.out.print("Digite el número de la opción deseada: ");
		final Integer optionSelected = listener.nextInt();
		listener.close();
		this.handleOption(optionSelected);
	}

	private void handleOption(final Integer optionSelected) {
		switch (optionSelected) {
			case 1:
				this.empleados.put(1, new Empleado(1, "nombre", "apellido", 2000.00));
				this.start();
				break;

			case 2:
				this.empleados.remove(1);
				this.start();
				break;

			case 3:
			this.start();
				break;

			case 4:
				System.out.println(this.empleados);
				this.start();
				break;

			default:
				System.out.println("Bye!");
				break;
		}
	}

}
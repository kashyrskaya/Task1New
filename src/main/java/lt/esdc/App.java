package lt.esdc;

import lt.esdc.action.impl.TetrahedronCalculatorImpl;
import lt.esdc.entity.Point;
import lt.esdc.entity.Tetrahedron;
import lt.esdc.entity.repository.TetrahedronRepository;
import lt.esdc.entity.specification.TetrahedronSpecification;
import lt.esdc.entity.specification.impl.AreaRangeTetrahedronSpecification;
import lt.esdc.entity.specification.impl.IdTetrahedronSpecification;
import lt.esdc.entity.specification.impl.PerimeterRangeTetrahedronSpecification;
import lt.esdc.entity.specification.impl.VolumeRangeTetrahedronSpecification;
import lt.esdc.exception.FileReadException;
import lt.esdc.exception.ShapeValidationException;
import lt.esdc.factory.TetrahedronFactory;
import lt.esdc.reader.ShapeCoordinateReader;
import lt.esdc.validator.TetrahedronValidatorImpl;
import lt.esdc.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class demonstrating the tetrahedron management system.
 * This class provides a simple command-line interface to interact with the system.
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static TetrahedronRepository repository;
    private static TetrahedronCalculatorImpl calculator;
    private static ShapeCoordinateReader reader;
    private static TetrahedronFactory factory;
    private static Warehouse warehouse;

    public static void main(String[] args) {
        init();
        showMenu();
    }

    /**
     * Initialize all necessary components of the application
     */
    private static void init() {
        logger.info("Initializing application components");
        TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();
        calculator = new TetrahedronCalculatorImpl(validator);
        repository = new TetrahedronRepository();
        reader = new ShapeCoordinateReader();
        factory = new TetrahedronFactory();
        warehouse = Warehouse.getInstance();

        // Register the calculator in the warehouse
        warehouse.registerCalculator(Tetrahedron.class, calculator);

        logger.info("Application components initialized successfully");
    }

    /**
     * Display the main menu and handle user input
     */
    private static void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Tetrahedron Management System ---");
            System.out.println("1. Load tetrahedrons from file");
            System.out.println("2. Add a tetrahedron manually");
            System.out.println("3. Display all tetrahedrons");
            System.out.println("4. Calculate area, perimeter and volume");
            System.out.println("5. Search tetrahedrons");
            System.out.println("6. Sort tetrahedrons");
            System.out.println("7. Display warehouse information");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1 -> loadFromFile();
                case 2 -> addTetrahedron();
                case 3 -> displayAllTetrahedrons();
                case 4 -> calculateShapeProperties();
                case 5 -> searchTetrahedrons();
                case 6 -> sortTetrahedrons();
                case 7 -> displayWarehouseInfo();
                case 8 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        logger.info("Application terminated");
    }

    /**
     * Load tetrahedrons from a file
     */
    private static void loadFromFile() {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try {
            List<Tetrahedron> tetrahedrons = reader.readShapesFromFile(filePath, factory)
                    .stream()
                    .map(shape -> (Tetrahedron) shape)
                    .toList();

            for (Tetrahedron tetrahedron : tetrahedrons) {
                repository.add(tetrahedron);
            }

            System.out.println("Successfully loaded " + tetrahedrons.size() + " tetrahedrons");
            logger.info("Loaded {} tetrahedrons from file: {}", tetrahedrons.size(), filePath);
        } catch (FileReadException e) {
            System.out.println("Error reading file: " + e.getMessage());
            logger.error("Error reading file: {}", e.getMessage());
        }
    }

    /**
     * Add a tetrahedron manually
     */
    private static void addTetrahedron() {
        try {
            System.out.println("Enter coordinates for point A (x y z):");
            Point pointA = readPoint();

            System.out.println("Enter coordinates for point B (x y z):");
            Point pointB = readPoint();

            System.out.println("Enter coordinates for point C (x y z):");
            Point pointC = readPoint();

            System.out.println("Enter coordinates for point D (x y z):");
            Point pointD = readPoint();

            System.out.println("Enter ID for the tetrahedron:");
            String id = scanner.nextLine();

            Tetrahedron tetrahedron = new Tetrahedron(id, pointA, pointB, pointC, pointD);

            // Validate the tetrahedron
            TetrahedronValidatorImpl validator = new TetrahedronValidatorImpl();
            if (!validator.isValid(tetrahedron)) {
                System.out.println("Invalid tetrahedron. The points may be coplanar or overlapping.");
                return;
            }

            repository.add(tetrahedron);
            System.out.println("Tetrahedron added successfully with ID: " + id);
            logger.info("Manually added tetrahedron with ID: {}", id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter numeric values.");
            logger.error("Invalid input format for manual tetrahedron creation: {}", e.getMessage());
        }
    }

    /**
     * Read a point from user input
     */
    private static Point readPoint() {
        String[] coordinates = scanner.nextLine().split("\\s+");
        if (coordinates.length != 3) {
            throw new NumberFormatException("Expected 3 coordinates");
        }

        double x = Double.parseDouble(coordinates[0]);
        double y = Double.parseDouble(coordinates[1]);
        double z = Double.parseDouble(coordinates[2]);

        return new Point(x, y, z);
    }

    /**
     * Display all tetrahedrons in the repository
     */
    private static void displayAllTetrahedrons() {
        List<Tetrahedron> tetrahedrons = repository.getAllTetrahedrons();

        if (tetrahedrons.isEmpty()) {
            System.out.println("No tetrahedrons in the repository.");
            return;
        }

        System.out.println("\n--- All Tetrahedrons ---");
        for (Tetrahedron tetrahedron : tetrahedrons) {
            System.out.println(tetrahedron);
        }
        System.out.println("Total: " + tetrahedrons.size() + " tetrahedrons");
    }

    /**
     * Calculate and display area, perimeter, and volume for a tetrahedron
     */
    private static void calculateShapeProperties() {
        System.out.println("Enter tetrahedron ID:");
        String id = scanner.nextLine();

        TetrahedronSpecification specification = new IdTetrahedronSpecification(id);
        List<Tetrahedron> results = repository.query(specification);

        if (results.isEmpty()) {
            System.out.println("No tetrahedron found with ID: " + id);
            return;
        }

        Tetrahedron tetrahedron = results.get(0);
        try {
            double area = calculator.computeArea(tetrahedron);
            double perimeter = calculator.computePerimeter(tetrahedron);
            double volume = calculator.computeVolume(tetrahedron);

            System.out.println("\n--- Tetrahedron Properties ---");
            System.out.println("ID: " + tetrahedron.getId());
            System.out.println("Area: " + area);
            System.out.println("Perimeter: " + perimeter);
            System.out.println("Volume: " + volume);

        } catch (ShapeValidationException e) {
            System.out.println("Error calculating properties: " + e.getMessage());
            logger.error("Error calculating properties for tetrahedron {}: {}", id, e.getMessage());
        }
    }

    /**
     * Search tetrahedrons based on criteria
     */
    private static void searchTetrahedrons() {
        System.out.println("\n--- Search Tetrahedrons ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by area range");
        System.out.println("3. Search by perimeter range");
        System.out.println("4. Search by volume range");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();
        TetrahedronSpecification specification;

        switch (choice) {
            case 1 -> {
                System.out.println("Enter ID:");
                String id = scanner.nextLine();
                specification = new IdTetrahedronSpecification(id);
            }
            case 2 -> {
                System.out.println("Enter minimum area:");
                double minArea = getDoubleInput();
                System.out.println("Enter maximum area:");
                double maxArea = getDoubleInput();
                specification = new AreaRangeTetrahedronSpecification(calculator, maxArea, minArea);
            }
            case 3 -> {
                System.out.println("Enter minimum perimeter:");
                double minPerimeter = getDoubleInput();
                System.out.println("Enter maximum perimeter:");
                double maxPerimeter = getDoubleInput();
                specification = new PerimeterRangeTetrahedronSpecification(calculator, minPerimeter, maxPerimeter);
            }
            case 4 -> {
                System.out.println("Enter minimum volume:");
                double minVolume = getDoubleInput();
                System.out.println("Enter maximum volume:");
                double maxVolume = getDoubleInput();
                specification = new VolumeRangeTetrahedronSpecification(calculator, minVolume, maxVolume);
            }
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        List<Tetrahedron> results = repository.query(specification);

        if (results.isEmpty()) {
            System.out.println("No tetrahedrons match the criteria.");
            return;
        }

        System.out.println("\n--- Search Results ---");
        for (Tetrahedron tetrahedron : results) {
            System.out.println(tetrahedron);
        }
        System.out.println("Total: " + results.size() + " tetrahedrons found");
    }

    /**
     * Sort tetrahedrons by different criteria
     */
    private static void sortTetrahedrons() {
        System.out.println("\n--- Sort Tetrahedrons ---");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by area");
        System.out.println("3. Sort by perimeter");
        System.out.println("4. Sort by volume");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1 -> repository.sortByID();
            case 2 -> repository.sortByArea(calculator);
            case 3 -> repository.sortByPerimeter(calculator);
            case 4 -> repository.sortByVolume(calculator);
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        System.out.println("Tetrahedrons sorted successfully.");
        displayAllTetrahedrons();
    }

    /**
     * Display warehouse information for a tetrahedron
     */
    private static void displayWarehouseInfo() {
        System.out.println("Enter tetrahedron ID:");
        String id = scanner.nextLine();

        if (!warehouse.containsShape(id)) {
            System.out.println("No information found in warehouse for ID: " + id);
            return;
        }

        Warehouse.ShapeParameters parameters = warehouse.getParameters(id);

        System.out.println("\n--- Warehouse Information ---");
        System.out.println("ID: " + id);
        System.out.println("Area: " + parameters.getArea());
        System.out.println("Perimeter: " + parameters.getPerimeter());
        System.out.println("Volume: " + parameters.getVolume());
    }

    /**
     * Get integer input from the user
     */
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Get double input from the user
     */
    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
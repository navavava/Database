import db.*;
import db.exception.*;
import db.todo.entity.*;
import db.todo.validator.*;
import java.util.Scanner;
import static db.todo.service.StepService.*;
import static db.todo.service.TaskService.*;


public class Main {
    public static void main(String[] args) throws InvalidEntityException {

        Database.registerValidator(Task.TASK_ENTITY_CODE, new TaskValidator());
        Database.registerValidator(Step.STEP_ENTITY_CODE, new StepValidator());
        Scanner scn = new Scanner(System.in);

        while (true){
            System.out.println("Enter a command: ");
            String command = scn.next();

            switch (command.toLowerCase()) {  // Convert to lowercase for case-insensitive matching
                case "add":
                    add();
                    break;

                case "add step":
                    addStep();
                    break;

                case "delete":
                    delete();
                    break;

                case "update":
                    update();
                    break;

                case "update step":
                    updateStep();
                    break;

                case "get task-by-id":
                    getTaskById();
                    break;

                case "get all-tasks":
                    getAllTasks();
                    break;

                case "get incomplete-tasks":
                    getIncompleteTasks();
                    break;

                case "exit":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
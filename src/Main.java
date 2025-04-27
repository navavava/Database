import db.*;
import db.exception.*;
import db.todo.entity.*;
import db.todo.validator.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static db.todo.service.StepService.*;
import static db.todo.service.TaskService.*;


public class Main {
    public static void main(String[] args) throws InvalidEntityException {

        Database.registerValidator(Task.TASK_ENTITY_CODE, new TaskValidator());
        Database.registerValidator(Step.STEP_ENTITY_CODE, new StepValidator());
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command: ");
            String command = scn.nextLine();

            switch (command.toLowerCase()) {
                case "add task":
                    add();
                    break;

                case "add step":
                    addStep();
                    break;

                case "delete":
                    try {
                        delete();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input.");
                    }
                    break;

                case "update task":
                    try {
                        update();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input.");
                    }
                    break;

                case "update step":
                    try {
                        updateStep();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input.");
                    }
                    break;

                case "get task-by-id":
                    try {
                        getTaskById();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input.");
                    }
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
package db.todo.service;

import db.Database;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import db.todo.entity.Step;

import java.util.Scanner;

public class StepService {

    public static void saveStep(Step step) throws InvalidEntityException {
        Database.add(step);
    }

    public static void addStep() throws InvalidEntityException {
        Scanner scn = new Scanner(System.in);
        String title;
        int taskRef;
        System.out.println("title:");
        title = scn.nextLine();
        System.out.println("Task ID:");
        taskRef = scn.nextInt();
        Step newStep = new Step(title, taskRef, Step.Status.NotStarted);
        try {
            Database.add(newStep);
            System.out.println("Successfully added step");
            System.out.println("ID: " + newStep.id);
        } catch (InvalidEntityException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setAsCompleted(int stepId) throws InvalidEntityException {
        Step step = (Step) Database.get(stepId);
        step.status = Step.Status.Completed;
        Database.update(step);
    }


    public static void updateStep() throws InvalidEntityException {

        Scanner scn = new Scanner(System.in);
        System.out.println("ID: ");
        int ID = scn.nextInt();
        System.out.println("Field: ");
        String nextField = scn.nextLine();
        System.out.println("New Value: ");
        String newValue = scn.nextLine();
        Step step = (Step) Database.get(ID);
        if (nextField.equalsIgnoreCase("Title")) {
            step.title = newValue;
        } else if (nextField.equalsIgnoreCase("Task ID")) {
            step.taskRef = Integer.parseInt(newValue);
        } else if (nextField.equalsIgnoreCase("Status")) {
            if (newValue.equalsIgnoreCase("Completed")) {
                StepService.setAsCompleted(ID);
            } else {
                System.out.println("Invalid status.");
            }
        }
        try {
            Database.update(step);
        } catch (InvalidEntityException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the ID:");
        int ID = scn.nextInt();
        try {
            Database.delete(ID);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Successfully deleted step.");
    }
}
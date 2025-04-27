package db.todo.service;

import db.exception.EntityNotFoundException;
import db.todo.entity.*;
import db.Database;
import db.Entity;
import db.exception.InvalidEntityException;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class TaskService {

    public static void add() {
        Scanner scn = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        String title;
        String description;
        String dateStr;
        Date dueDate;
        System.out.println("Title:");
        title = scn.nextLine();
        System.out.println("Description:");
        description = scn.nextLine();
        System.out.println("Date:");
        dateStr = scn.nextLine();

        try {
            dueDate = dateFormat.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Date format is wrong.");
            return;
        }

        Task newTask = new Task(title, description, dueDate, Task.Status.NotStarted);
        try {
            Database.add(newTask);
            System.out.println("Task saved successfully.");
            System.out.println("Task ID: " + newTask.id);
        } catch (InvalidEntityException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setAsCompleted(int taskId) {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.Completed;
        try {
            Database.update(task);
        } catch (InvalidEntityException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setAsInProgress(int taskId) {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.InProgress;
        try {
            Database.update(task);
        } catch (InvalidEntityException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void update() {

        Scanner scn = new Scanner(System.in);
        System.out.println("ID: ");
        int ID = scn.nextInt();
        System.out.println("Field: ");
        scn.nextLine();
        String nextField = scn.nextLine();
        System.out.println("New Value: ");
        String newValue = scn.nextLine();
        System.out.println(nextField);
        try {
            Task task = (Task) Database.get(ID);
            if (nextField.equalsIgnoreCase("Title")) {
                task.title = newValue;
            } else if (nextField.equalsIgnoreCase("Description")) {
                task.description = newValue;
            } else if (nextField.equalsIgnoreCase("Status")) {
                if (newValue.equalsIgnoreCase("Completed")) {
                    task.status = Task.Status.Completed;
                    ArrayList<Entity> entities = Database.getAll(12);
                    for (Entity entity : entities) {
                        if (((Step) entity).taskRef == ID) {
                            Step step = (Step) entity;
                            step.status = Step.Status.Completed;
                            try {
                                Database.update(step);
                            } catch (InvalidEntityException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                } else if (newValue.equalsIgnoreCase("InProgress")) {
                    task.status = Task.Status.InProgress;
                }
            }
            try {
                Database.update(task);
                System.out.println("Successfully updated the task.");
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Cannot update entity with ID = " + ID);
            System.out.println(e.getMessage());
        }
    }

    public static void getAllTasks() {

        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        ArrayList<Entity> entities = Database.getAll(Step.STEP_ENTITY_CODE);

        for (Entity entity : tasks) {
            Task task = (Task) entity;
            System.out.println("ID: " + task.id + "\nTitle: " + task.title + "\nStatus: " + task.status + "\nDue date: " + task.dueDate);

            System.out.println("Steps:");
            int numberOfSteps = 1;
            for (Entity entity1 : entities) {
                if (((Step) entity1).taskRef == task.id) {
                    Step step = (Step) entity1;
                    System.out.println("Step " + numberOfSteps);
                    System.out.println("Title: " + step.title);
                    System.out.println("ID: " + step.id);
                    System.out.println("Status: " + step.status);
                }
                numberOfSteps++;
            }
            if (numberOfSteps == 1) {
                System.out.println("No Steps found.");
            }
            System.out.println();
        }
    }


    public static void getIncompleteTasks() {
        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        ArrayList<Entity> entities = Database.getAll(Step.STEP_ENTITY_CODE);

        for (Entity entity : tasks) {
            Task task = (Task) entity;
            if (task.status != Task.Status.Completed) {
                System.out.println("ID: " + task.id + "\nTitle: " + task.title + "\nStatus: " + task.status + "\nDue date: " + task.dueDate);

                System.out.println("Steps:");
                int numberOfSteps = 1;
                for (Entity entity1 : entities) {
                    if (((Step) entity1).taskRef == task.id) {
                        Step step = (Step) entity1;
                        System.out.println("Step " + numberOfSteps);
                        System.out.println("Title: " + step.title);
                        System.out.println("ID: " + step.id);
                        System.out.println("Status: " + step.status);
                    }
                    numberOfSteps++;
                }
                if (numberOfSteps == 1) {
                    System.out.println("No Steps found.");
                }
                System.out.println();
            }
        }
    }

    public static void getTaskById() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the ID you want: ");
        int id = scn.nextInt();
        try {
            Task task = (Task) Database.get(id);
            System.out.println("Details: ");
            System.out.println("Title: " + task.title);
            System.out.println("Description: " + task.description);
            System.out.println("Status: " + task.status);
            System.out.println("Due date: " + task.dueDate);
        } catch (Exception e) {
            System.out.println("Something went wrong, this ID might not belong to a task.");
        }
    }


}
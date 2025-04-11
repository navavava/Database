package db.todo.service;

import db.todo.entity.*;
import db.Database;
import db.Entity;
import db.exception.InvalidEntityException;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class TaskService {


    public static void setAsCompleted(int taskId) throws InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.Completed;
        Database.update(task);
    }

    public static void setAsInProgress(int taskId) throws InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.status = Task.Status.InProgress;
        Database.update(task);
    }

    public static void add() throws InvalidEntityException {
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
        Database.add(newTask);
        System.out.println("Task saved successfully.");
        System.out.println("Task ID: " + newTask.id);
    }


    public static void update() throws InvalidEntityException {

        Scanner scn = new Scanner(System.in);
        System.out.println("ID: ");
        int ID = scn.nextInt();
        System.out.println("Field: ");
        scn.nextLine();
        String nextField = scn.nextLine();
        System.out.println("New Value: ");
        String newValue = scn.nextLine();
        System.out.println(nextField);
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
                        Database.update(step);
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
    }

    public static void getAllTasks() {

        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        ArrayList<Entity> entities = Database.getAll(Step.STEP_ENTITY_CODE);
        int numberOfTasks = 1;

        for (Entity entity : tasks) {
            Task task = (Task) entity;
            System.out.println(numberOfTasks + "Title: " + task.title + " ,ID: " + task.id + " ,Status:" + task.status);
            numberOfTasks++;

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
                System.out.println("No Steps");
            }
        }
    }


    public static void getIncompleteTasks() {
        ArrayList<Entity> tasks = Database.getAll(Task.TASK_ENTITY_CODE);
        int numberOfTasks = 1;
        for (Entity entity : tasks) {
            Task task = (Task) entity;
            if (task.status != Task.Status.Completed) {
                System.out.println(numberOfTasks + "Title: " + task.title + " ,ID: " + task.id);
                numberOfTasks++;
            }
        }
    }

    public static void getTaskById() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the ID you want: ");
        int id = scn.nextInt();
        Task gettedTask = (Task) Database.get(id);
        System.out.println("Details: ");
        System.out.println("Title: "+gettedTask.title);
        System.out.println("Description: "+gettedTask.description);
        System.out.println("Status: "+gettedTask.status);
        System.out.println("Due date: "+gettedTask.dueDate);
    }


}
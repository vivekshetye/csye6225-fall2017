package com.csye6225.demo.controllers;


import com.csye6225.demo.model.FileUpload;
import com.csye6225.demo.model.Task;
import com.csye6225.demo.model.User;
import com.csye6225.demo.repository.FileUploadRepository;
import com.csye6225.demo.repository.TaskRepository;
import com.csye6225.demo.service.TaskService;
import com.csye6225.demo.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class TaskController {

@Autowired
TaskRepository taskRepository;

@Autowired
TaskService taskService;

@Autowired
FileUploadRepository fileUploadRepository;

    @Autowired
    private UserService userService;

    //@PostMapping("/user/task")
    @RequestMapping(value="/user/task", method= RequestMethod.POST, produces= "application/json")
    public String createNote(@Valid @RequestBody Task task/*@RequestParam("description") String description*/, HttpServletRequest request,/* @RequestParam("files") MultipartFile[] files,*/ HttpServletResponse response) {


        JsonObject jsonObject = new JsonObject();
        String header = request.getHeader("Authorization");
        if (header != null && header.contains("Basic")) {
            String[] credentialValues= decode(header);

            User userExists = userService.findUserByEmail(credentialValues[0]);
            if (userExists != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
                 {


                        if (task.getDescription().length() > 10) {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            jsonObject.addProperty("message", "description max length exceeded");
                        } else {


                            task.setUser(userExists);
                            Task newTask = taskRepository.save(task);
                            userExists.getTasks().add(newTask);


                            if (newTask != null) {
                                jsonObject.addProperty("message", "task created sucessfully");
                                response.setStatus(HttpServletResponse.SC_CREATED);
                            } else
                                jsonObject.addProperty("404", "Not found");
                        }

                }
                else
                    jsonObject.addProperty("message", "Incorrect credentials");
            } else {
                jsonObject.addProperty("message", "Incorrect credentials");
            }
        } else {
            jsonObject.addProperty("message", "You are not logged in !!");
        }
        return jsonObject.toString();


    }




/*
Delete task by task id

 */

    @DeleteMapping("/user/task/{id}")
    public String deleteTaskById(@PathVariable(value = "id") String taskId, HttpServletRequest request, HttpServletResponse response) throws IOException {


        JsonObject jsonObject = new JsonObject();
        String header = request.getHeader("Authorization");
        if (header != null && header.contains("Basic")) {
            String[] credentialValues= decode(header);

            User userExists = userService.findUserByEmail(credentialValues[0]);
            if (userExists != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
                {
                    Task task = taskRepository.findOne(taskId);

                    if(task.getUser().getId() == userExists.getId()) {
                        if(task != null) {
                            taskRepository.delete(task);
                            jsonObject.addProperty("messgae", "task deleted successfully");
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);


                        } else {
                            jsonObject.addProperty("message", "No task found with the given id");
                        }
                    } else {
                        jsonObject.addProperty("message","unauthorized user accessing the task");
                    }



                }
                else
                    jsonObject.addProperty("message", "Incorrect credentials");
            } else {
                jsonObject.addProperty("message", "Incorrect credentials");
            }
        } else {
            jsonObject.addProperty("message", "You are not logged in !!");
        }
        return jsonObject.toString();




    }



/*
Update task description
 */

    @PutMapping("/user/task/{id}")
    public String getTaskByUserId(@RequestBody Task task,@PathVariable(value = "id") String taskId, HttpServletRequest request, HttpServletResponse response) {



        JsonObject jsonObject = new JsonObject();
        String header = request.getHeader("Authorization");
        if (header != null && header.contains("Basic")) {
            String[] credentialValues= decode(header);

            User userExists = userService.findUserByEmail(credentialValues[0]);
            if (userExists != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
                {
                    Task resTask = taskRepository.findOne(taskId);

                    if(resTask.getUser().getId() == userExists.getId()) {
                        if(resTask != null) {
                            jsonObject.addProperty("message", "task updated successfully");

                            resTask.setDescription(task.getDescription());
                            taskRepository.save(resTask);
                            response.setStatus(HttpServletResponse.SC_OK);
                        } else {
                            jsonObject.addProperty("message", "No task found with the given id");
                        }
                    } else {
                        jsonObject.addProperty("message","unauthorized user accessing the task");
                    }

                }
                else
                    jsonObject.addProperty("message", "Incorrect credentials");
            } else {
                jsonObject.addProperty("message", "Incorrect credentials");
            }
        } else {
            jsonObject.addProperty("message", "You are not logged in !!");
        }
        return jsonObject.toString();




    }


    /*
    Get attachments associated with a task
     */

    @GetMapping("/user/task/{id}/attachments")
    public String getAttachments(@PathVariable(value = "id") String taskId, HttpServletRequest request, HttpServletResponse response) {


        JsonObject jsonObject = new JsonObject();
        String header = request.getHeader("Authorization");
        if (header != null && header.contains("Basic")) {
            String[] credentialValues= decode(header);

            User userExists = userService.findUserByEmail(credentialValues[0]);
            if (userExists != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
                {
                    Task task = taskRepository.findOne(taskId);

                    if(task.getUser().getId() == userExists.getId() ) {
                        if(task != null) {

                            List<FileUpload> attachments = task.getFiles();

                            if(attachments.size() != 0) {

                                int counter=1;
                                for(FileUpload file : attachments) {
                                    File tempFile = new File(file.getFilePath());

                                    jsonObject.addProperty("File "+counter, tempFile.getName());
                                    counter++;
                                }

                                response.setStatus(HttpServletResponse.SC_OK);
                            } else {
                                jsonObject.addProperty("message","No attachments found on this task");
                            }


                        } else {
                            jsonObject.addProperty("message", "No task found with the given task id");
                        }
                    } else {
                        jsonObject.addProperty("message","unauthorized user accessing the task");
                    }

                }
                else
                    jsonObject.addProperty("message", "Incorrect credentials");
            } else {
                jsonObject.addProperty("message", "Incorrect credentials");
            }
        } else {
            jsonObject.addProperty("message", "You are not logged in !!");
        }
        return jsonObject.toString();

    }


    /*
    Add attachments to a task
     */

    @PostMapping("/user/task/{id}/attachments")
    public String attachNewFileToTask(@RequestParam(value = "files") MultipartFile[] files, @PathVariable(value = "id") String taskId, HttpServletRequest request, HttpServletResponse response) {

        JsonObject jsonObject = new JsonObject();
        String header = request.getHeader("Authorization");
        if (header != null && header.contains("Basic")) {
            String[] credentialValues= decode(header);

            User userExists = userService.findUserByEmail(credentialValues[0]);
            if (userExists != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
                {
                    Task task = taskRepository.findOne(taskId);

                    if(task.getUser().getId() == userExists.getId() ) {
                        if(task != null) {

                            if (files.length != 0) {
                                try {
                                    String uploadsDir = "/uploads/";
                                    String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                                    if (!new File(realPathtoUploads).exists()) {
                                        new File(realPathtoUploads).mkdir();
                                    }



                                    for(MultipartFile file : files) {
                                        String orgName = file.getOriginalFilename();
                                        String filePath = realPathtoUploads + orgName;
                                        FileUpload fileUpload = new FileUpload();
                                        fileUpload.setFilePath(filePath);
                                        fileUpload.setTask(task);
                                        fileUploadRepository.save(fileUpload);
                                        //task.getFiles().add(fileUpload);
                                        File dest = new File(filePath);
                                        file.transferTo(dest);

                                    }

                                    if (files.length >1) {
                                        jsonObject.addProperty("message", "Files added successfully");
                                    } else {
                                        jsonObject.addProperty("message", "File added successfully");
                                    }

                                    response.setStatus(HttpServletResponse.SC_CREATED);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                            } else System.out.println("******************File empty***************");


                        } else {
                            jsonObject.addProperty("message", "No task found with the given task id");
                        }
                    } else {
                        jsonObject.addProperty("message","unauthorized user accessing the task");
                    }

                }
                else
                    jsonObject.addProperty("message", "Incorrect credentials");
            } else {
                jsonObject.addProperty("message", "Incorrect credentials");
            }
        } else {
            jsonObject.addProperty("message", "You are not logged in !!");
        }
        return jsonObject.toString();





    }


    /*
    Delete an attachment associated to a task
     */

@DeleteMapping("/user/task/{id}/attachments/{idAttachments}")
public String deleteFileById(@PathVariable(value = "id") String taskId, @PathVariable(value = "idAttachments") String idAttachment, HttpServletRequest request, HttpServletResponse response) throws IOException {




    JsonObject jsonObject = new JsonObject();
    String header = request.getHeader("Authorization");
    if (header != null && header.contains("Basic")) {
        String[] credentialValues= decode(header);

        User userExists = userService.findUserByEmail(credentialValues[0]);
        if (userExists != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(credentialValues[1], userExists.getPassword()) || credentialValues[1].equals(userExists.getPassword()))
            {
                Task task = taskRepository.findOne(taskId);

                if(task.getUser().getId() == userExists.getId() ) {
                    if(task != null) {

                        List<FileUpload> attachments = task.getFiles();

                        FileUpload deleteFileUpload = new FileUpload();

                        for(FileUpload fu: attachments) {

                            if(fu.getFileId() == Long.parseLong(idAttachment))
                                deleteFileUpload = fu;

                        }

                        if(deleteFileUpload != null) {

                            File deleteFile = new File(deleteFileUpload.getFilePath());
                            deleteFile.delete();

                            FileUpload deleteFileUploadDb = fileUploadRepository.findOne(deleteFileUpload.getFileId());

                            fileUploadRepository.delete(deleteFileUploadDb);

                            jsonObject.addProperty("message", "File deleted successfully");
                            response.setStatus(HttpServletResponse.SC_NO_CONTENT);


                        } else {
                            jsonObject.addProperty("message", "No attachment found with the given attachment id");
                        }

                    } else {
                        jsonObject.addProperty("message", "No task found with the given task id");
                    }
                } else {
                    jsonObject.addProperty("message","unauthorized user accessing the task");
                }



            }
            else
                jsonObject.addProperty("message", "Incorrect credentials");
        } else {
            jsonObject.addProperty("message", "Incorrect credentials");
        }
    } else {
        jsonObject.addProperty("message", "You are not logged in !!");
    }
    return jsonObject.toString();




}


    public String[] decode(String header){
        assert header.substring(0, 6).equals("Basic");
        String basicAuthEncoded = header.substring(6);
        String basicAuthAsString = new String(Base64.getDecoder().decode(basicAuthEncoded.getBytes()));
        final String[] credentialValues = basicAuthAsString.split(":", 2);
        return  credentialValues;
    }
}

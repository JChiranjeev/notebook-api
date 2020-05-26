package dev.jainchiranjeev.Controllers;

import dev.jainchiranjeev.DBController.INotesRepo;
import dev.jainchiranjeev.DBController.IUsersRepo;
import dev.jainchiranjeev.Model.Notes;
import dev.jainchiranjeev.Model.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotebookController {

    @Autowired
    IUsersRepo usersRepo;
    @Autowired
    INotesRepo notesRepo;

    @PostMapping("userLogin")
    @ResponseBody
    public ResponseEntity<String> userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<Users> usersList = usersRepo.findByUsernameEqualsAndPasswordEquals(username, password);
        boolean userVerified = usersList.size() > 0 ? true : false;
        HashMap<String, Boolean> outputMap = new HashMap<>();
        outputMap.put("authenticated", userVerified);
        String outputJson = "";
        try {
            outputJson = objectWriter.writeValueAsString(outputMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        outputJson = outputJson.toString();
        ResponseEntity<String> response = new ResponseEntity<String>(outputJson, HttpStatus.OK);
        return response;
    }

    @PostMapping("/userSignup")
    @ResponseBody
    public ResponseEntity<Boolean> userSignup(@RequestParam("username") String username, @RequestParam("password") String password) {
        List<Users> usersList = usersRepo.findByUsernameEquals(username);
        Boolean boolResponse = false;
        if(usersList.size() > 0) {
            boolResponse = false;
        } else {
            usersRepo.save(new Users(username, password));
            boolResponse = true;
        }
        ResponseEntity<Boolean> response = new ResponseEntity<>(boolResponse, HttpStatus.OK);
        return response;
    }

    @PostMapping("/notes")
    @ResponseBody
    public ResponseEntity<String> getNotesForUser(@RequestParam("username") String username) {
        List<Notes> notes = notesRepo.findByUsernameEquals(username);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String outputJson = "";
        try {
            outputJson = objectWriter.writeValueAsString(notes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        outputJson = outputJson.toString();
        ResponseEntity<String> response = new ResponseEntity<>(outputJson, HttpStatus.OK);
        return response;
    }

    @PostMapping("/addNote")
    @ResponseBody
    public ResponseEntity<String> addNote(@RequestParam("id") String id, @RequestParam("username") String username, @RequestParam("noteTitle") String noteTitle, @RequestParam("noteContent") String noteContent) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String outputJson = "";
        Notes note = null;
        if(usersRepo.findByUsernameEquals(username).size() > 0) {
            if(id.equals("null")) {
                note = notesRepo.save(new Notes(username, noteTitle, noteContent));
            } else {
                note = notesRepo.findOne(Integer.parseInt(id));
                note.setNoteTitle(noteTitle);
                note.setNoteContent(noteContent);
                notesRepo.save(note);
            }
        } else {
            note = null;
        }
        try {
            outputJson = objectWriter.writeValueAsString(note);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        outputJson = outputJson.toString();
        ResponseEntity<String> response = new ResponseEntity<>(outputJson, HttpStatus.OK);
        return response;
    }
    
}